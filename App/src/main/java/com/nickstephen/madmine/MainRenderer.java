package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.opengl.Renderer;
import com.nickstephen.madmine.content.TitleScreen;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Nick Stephen on 7/03/14.
 */
public class MainRenderer extends Renderer {
    public MainRenderer(Context context, GLSurfaceView surface) {
        super(context, surface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);

        mContentContainer = new TitleScreen(mSurface, mContext, width, height);
    }
}
