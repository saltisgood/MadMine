package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.IOnAnimationEnd;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.content.MainScreen;
import com.nickstephen.madmine.content.TitleScreen;
import com.nickstephen.madmine.util.MineLoop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick Stephen on 13/03/14.
 */
public class Game extends com.nickstephen.gamelib.run.Game {
    public static Game init(@NotNull Context context) {
        if (sInstance == null) {
            sInstance = new Game(context);
        } else {
            ((Game)sInstance).setContext(context);
        }
        return (Game)sInstance;
    }

    public static Game getInstanceUnsafe() {
        return (Game)sInstance;
    }

    private boolean mStarted = false;
    private Context mContext;
    private RootContainer mSwapView;

    protected Game(@NotNull Context context) {
        mContext = context;
    }

    private void setContext(@NotNull Context context) {
        mContext = context;
    }

    public void releaseContext() {
        mContext = null;
    }

    public boolean hasStarted() {
        return mStarted;
    }

    public void setup(int width, int height) {
        super.setup(width, height);
		
        if (!mStarted) {
            mActiveView = new TitleScreen(mContext, getSurface(), width, height);
        } else {
            mActiveView = new MainScreen(mContext, getSurface(), width, height);
        }
    }

    public @Nullable RootContainer getSwapView() {
        return mSwapView;
    }

    public void start() {
        Twig.debug("Game", "Start Game!");
        mStarted = true;

        MineLoop.getInstanceUnsafe().cancelAnimations(true);

        new AlphaAnimation(mActiveView, 1.0f, 0.0f).setAnimationEndListener(new IOnAnimationEnd() {
            @Override
            public void onAnimationEnd(Shape shape) {
                synchronized (this) {
                    List<Shape> shapes = mActiveView.getChildren();
                    int len = shapes.size();
                    for (int i = 0; i < len; i++) {
                        MineLoop.getInstanceUnsafe().removeAnimationsOfShape(shapes.get(i));
                    }

                    final Shape view = mActiveView;
                    getSurface().queueEvent(new Runnable() {
                        @Override
                        public void run() {
                            view.destroy();
                        }
                    });
                    //mActiveView.destroy();
                    mActiveView = null;
                }

                addGLThreadAction(new Runnable() {
                    @Override
                    public void run() {
                        mActiveView = new MainScreen(mContext, getSurface(), getWidth(), getHeight());
                    }
                });
            }
        }).start();
    }
}
