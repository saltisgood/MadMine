package com.nickstephen.madmine.texteg;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Looper;
import android.view.MotionEvent;

import com.nickstephen.gamelib.GeneralUtil;
import com.nickstephen.gamelib.opengl.Polygon;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.widget.FPSMeter;
import com.nickstephen.madmine.content.TitleScreen;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Nick Stephen on 6/03/14.
 */
public class FPSTest implements GLSurfaceView.Renderer {
    private final Context mContext;
    private FPSMeter mFPS;
    private Polygon mCircle;
    private Container mContainer;
    private int mWidth;
    private int mHeight;
    private float[] mProjMatrix = new float[16];
    private float[] mBaseViewMatrix = new float[16];
    private float[] mOffsetViewMatrix;
    private float[] mVPMatrix = new float[16];
    private float mPosX = 0.0f;
    private float mPosY = 0.0f;
    private GLSurfaceView mSurface;

    public FPSTest(Context context, GLSurfaceView surface) {
        mContext = context;
        mSurface = surface;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mFPS = new FPSMeter(mContext.getAssets());
        mFPS.load("Roboto-Regular.ttf", 16, 3, 3);

        //mCircle = new Polygon(mContext, 0, 0, 100f, 45.0f, 4);
        //mContainer = new Container(250.0f, 250.0f);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;

        // Take into account device orientation
        if (width > height) {
            Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
        }
        else {
            Matrix.frustumM(mProjMatrix, 0, -1, 1, -1/ratio, 1/ratio, 1, 10);
        }

        // Save width and height
        this.mWidth = width;                             // Save Current Width
        this.mHeight = height;                           // Save Current Height

        mContainer = new TitleScreen(mSurface, mContext, width, height);

        int useForOrtho = Math.min(width, height);

        //TODO: Is this wrong?
        Matrix.orthoM(mBaseViewMatrix, 0,
                -useForOrtho/2,
                useForOrtho/2,
                -useForOrtho/2,
                useForOrtho/2, 0.1f, 100f);

        if (Build.VERSION.SDK_INT >= 9) {
            mOffsetViewMatrix = Arrays.copyOf(mBaseViewMatrix, 16);
        } else {
            mOffsetViewMatrix = GeneralUtil.arrayCopy(mBaseViewMatrix);
        }

        mFPS.onSurfaceChanged(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Redraw background color
        int clearMask = GLES20.GL_COLOR_BUFFER_BIT;

        GLES20.glClear(clearMask);

        Matrix.multiplyMM(mVPMatrix, 0, mProjMatrix, 0, mBaseViewMatrix, 0);

        mFPS.onDrawFrame(mVPMatrix);

        mContainer.draw(mVPMatrix);

        Matrix.translateM(mOffsetViewMatrix, 0, mBaseViewMatrix, 0, mPosX, mPosY, 0);
        Matrix.multiplyMM(mVPMatrix, 0, mProjMatrix, 0, mOffsetViewMatrix, 0);

        //mCircle.draw(mVPMatrix);
    }

    public void move(float dx, float dy) {
        mPosX += dx;
        mPosY += dy;
    }

    public boolean onTouchEvent(MotionEvent e) {
        return mContainer.onTouchEvent(e);
    }
}
