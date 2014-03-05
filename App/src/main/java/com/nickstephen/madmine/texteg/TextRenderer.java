package com.nickstephen.madmine.texteg;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.nickstephen.gamelib.opengl.GLText;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Nick Stephen on 6/03/14.
 */
public class TextRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = "TextRenderer";

    private GLText mGLText;                             // A GLText Instance
    private final Context mContext;                           // Context (from Activity)

    private int width = 100;                           // Updated to the Current Width + Height in onSurfaceChanged()
    private int height = 100;
    private float[] mProjMatrix = new float[16];
    private float[] mVMatrix = new float[16];
    private float[] mVPMatrix = new float[16];
    private long mLastTick = 0;

    public TextRenderer(Context context)  {
        mContext = context;                         // Save Specified Context
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor( 0.5f, 0.5f, 0.5f, 1.0f );

        // Create the GLText
        mGLText = new GLText(mContext.getAssets());

        // Load the font from file (set size + padding), creates the texture
        // NOTE: after a successful call to this the font is ready for rendering!
        mGLText.load("Roboto-Regular.ttf", 14, 2, 2);  // Create Font (Height: 14 Pixels / X+Y Padding 2 Pixels)

        // enable texture + alpha blending
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        int clearMask = GLES20.GL_COLOR_BUFFER_BIT;

        GLES20.glClear(clearMask);

        Matrix.multiplyMM(mVPMatrix, 0, mProjMatrix, 0, mVMatrix, 0);

        // TEST: render the entire font texture
        //mGLText.drawTexture( width/2, height/2, mVPMatrix);            // Draw the Entire Texture

        // TEST: render some strings with the font
        mGLText.begin(1.0f, 1.0f, 1.0f, 1.0f, mVPMatrix);         // Begin Text Rendering (Set Color WHITE)

        long currentTime = System.currentTimeMillis();
        if (mLastTick != 0) {
            mGLText.draw("FPS: " + (1000.0f / (currentTime - mLastTick)), 0, 0);
        }
        mLastTick = currentTime;
        mGLText.end();

        //mGLText.drawC("Test String 3D!", 0f, 0f, 0f, 0, -30, 0);
//		mGLText.drawC( "Test String :)", 0, 0, 0 );          // Draw Test String
        //mGLText.draw( "Diagonal 1", 40, 40, 40);                // Draw Test String
        //mGLText.draw( "Column 1", 100, 100, 90);              // Draw Test String
        //mGLText.end();                                   // End Text Rendering

        //mGLText.begin( 0.0f, 0.0f, 1.0f, 1.0f, mVPMatrix );         // Begin Text Rendering (Set Color BLUE)
        //mGLText.draw( "More Lines...", 50, 200 );        // Draw Test String
        //mGLText.draw( "The End.", 50, 200 + mGLText.getCharHeight(), 180);  // Draw Test String
        //mGLText.end();                                   // End Text Rendering
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) { //		gl.glViewport( 0, 0, width, height );
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
        this.width = width;                             // Save Current Width
        this.height = height;                           // Save Current Height

        int useForOrtho = Math.min(width, height);

        //TODO: Is this wrong?
        Matrix.orthoM(mVMatrix, 0,
                -useForOrtho/2,
                useForOrtho/2,
                -useForOrtho/2,
                useForOrtho/2, 0.1f, 100f);
    }
}

