package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.nickstephen.gamelib.opengl.Renderer;
import com.nickstephen.gamelib.opengl.layout.RootContainer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Nick Stephen on 7/03/14.
 */
public class MainRenderer extends Renderer {
    public MainRenderer(Context context, GLSurfaceView surface) {
        super(context, surface);

        Game.getInstanceUnsafe().setGLSurface(surface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);

        Game.getInstanceUnsafe().setup(width, height);
        //mContentContainer = new TitleScreen(mSurface, mContext, width, height);
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

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        RootContainer root = Game.getInstanceUnsafe().getActiveView();
        if (root != null) {
            return root.onTouchEvent(e);
        }

        return false;
    }

    @Override
    public void onDestroy() {
        Game.getInstanceUnsafe().onDestroy();
    }
}
