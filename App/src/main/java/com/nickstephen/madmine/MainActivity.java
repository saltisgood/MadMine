package com.nickstephen.madmine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.nickstephen.madmine.eg.OpenGLSurfaceView;
import com.nickstephen.madmine.texteg.FPSTest;
import com.nickstephen.madmine.texteg.TextRenderer;

public class MainActivity extends FragmentActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new OpenGLSurfaceView(this);
        //mGLView = new CustomGLSurfaceView(this);
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class CustomGLSurfaceView extends GLSurfaceView {
        public CustomGLSurfaceView(Context context) {
            super(context);

            this.setEGLContextClientVersion(2);

            //this.setRenderer(new TextRenderer(context));
            this.setRenderer(new FPSTest(context));
        }
    }
}
