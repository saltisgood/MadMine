package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.IOnAnimationEnd;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.content.MainScreen;
import com.nickstephen.madmine.content.RootContent;
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
            ((Game) sInstance).setContext(context);
        }
        return (Game)sInstance;
    }

    public static Game getInstanceUnsafe() {
        return (Game)sInstance;
    }

    private boolean mStarted = false;
    private RootContainer mSwapView;

    protected Game(@NotNull Context context) {
        super(context);
    }

    protected void setContext(@NotNull Context context) {
        super.setContext(context);
    }

    public boolean hasStarted() {
        return mStarted;
    }

    public void setup(int width, int height) {
        super.setup(width, height);

        mActiveView = new RootContent(mContext, getSurface(), width, height);

        /* if (!mStarted) {
            mActiveView = new TitleScreen(mContext, getSurface(), width, height);
        } else {
            mActiveView = new MainScreen(mContext, getSurface(), width, height);
        } */
    }

    public @Nullable RootContainer getSwapView() {
        return mSwapView;
    }

    public void start() {
        Twig.debug("Game", "Start Game!");
        mStarted = true;


    }
}
