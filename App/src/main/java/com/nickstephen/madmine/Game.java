package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.IOnAnimationEnd;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.content.MainScreen;
import com.nickstephen.madmine.content.StartScreen;
import com.nickstephen.madmine.content.SwipableScreen;
import com.nickstephen.madmine.content.TitleScreen;
import com.nickstephen.madmine.util.MineLoop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nick Stephen on 13/03/14.
 */
public class Game {
    private static Game sInstance;

    public static Game init(@NotNull Context context) {
        if (sInstance == null) {
            sInstance = new Game(context);
        } else {
            sInstance.setContext(context);
        }
        return sInstance;
    }

    public static Game getInstanceUnsafe() {
        return sInstance;
    }

    private boolean mStarted = false;
    private Context mContext;
    private GLSurfaceView mSurface;
    private RootContainer mActiveView;
    private RootContainer mSwapView;
    private int mWidth, mHeight;
    private List<Runnable> mActions;

    protected Game(@NotNull Context context) {
        mContext = context;

        mActions = new LinkedList<Runnable>();
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

    public void setGLSurface(@NotNull GLSurfaceView surface) {
        mSurface = surface;
    }

    public void setup(int width, int height) {
        if (!mStarted) {
            // mActiveView = new StartScreen(mContext, mSurface, width, height);
            // mActiveView = new TitleScreen(mSurface, mContext, width, height);
            mActiveView = new SwipableScreen(mSurface, mContext, width, height);

        } else {
            mActiveView = new MainScreen(mContext, mSurface, width, height);
        }

        mWidth = width;
        mHeight = height;
    }

    public RootContainer getActiveView() {
        return mActiveView;
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
                    mSurface.queueEvent(new Runnable() {
                        @Override
                        public void run() {
                            view.destroy();
                        }
                    });
                    //mActiveView.destroy();
                    mActiveView = null;
                }

                mActions.add(new Runnable() {
                    @Override
                    public void run() {
                        mActiveView = new MainScreen(mContext, mSurface, mWidth, mHeight);
                    }
                });
            }
        }).start();
    }

    public Runnable getGLThreadAction() {
        if (mActions.size() > 0) {
            Runnable ret = mActions.remove(0);
            return ret;
        }
        return null;
    }

    public void onDestroy() {
        if (mActiveView != null) {
            synchronized (this) {
                List<Shape> shapes = mActiveView.getChildren();
                int len = shapes.size();
                for (int i = 0; i < len; i++) {
                    MineLoop.getInstanceUnsafe().removeAnimationsOfShape(shapes.get(i));
                }

                final Shape shape = mActiveView;
                mSurface.queueEvent(new Runnable() {
                    @Override
                    public void run() {
                        shape.destroy();
                    }
                });
                //mActiveView.destroy();
                mActiveView = null;
            }
        }
    }
}
