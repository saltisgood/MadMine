package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.nickstephen.gamelib.opengl.OpenGLSurfaceView;
import com.nickstephen.gamelib.opengl.Renderer;
import com.nickstephen.gamelib.opengl.layout.RootContainer;

import org.jetbrains.annotations.NotNull;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Nick Stephen on 7/03/14.
 */
public class MainRenderer extends Renderer {
    public MainRenderer(@NotNull Context context, @NotNull OpenGLSurfaceView surface) {
        super(context, surface);

        //Game.getInstanceUnsafe().setSurface(surface);
    }

    @Override
    public void onDraw(float[] projMatrix, float[] viewMatrix) {
        Runnable action;
        if ((action = Game.getInstanceUnsafe().getGLThreadAction()) != null) {
            action.run();
        }

        synchronized (Game.getInstanceUnsafe()) {
            RootContainer root = Game.getInstanceUnsafe().getActiveView();
            if (root != null) {
                root.draw(projMatrix, viewMatrix);
            }

            root = Game.getInstanceUnsafe().getSwapView();
            if (root != null) {
                root.draw(projMatrix, viewMatrix);
            }
        }
    }
}
