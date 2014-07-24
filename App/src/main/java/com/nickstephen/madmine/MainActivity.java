package com.nickstephen.madmine;

import android.opengl.GLSurfaceView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.nickstephen.gamelib.GeneralUtil;
import com.nickstephen.gamelib.opengl.OpenGLSurfaceView;
import com.nickstephen.gamelib.opengl.Renderer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.MineLoop;

/**
 * The launch activity of the app. Hosts all other visible components. Created as a FragmentActivity
 * just in case I want fragments later.
 */
public class MainActivity extends FragmentActivity {

    private OpenGLSurfaceView mGLView;
    private MineLoop mGameLoop;

    /**
     * Callback method called on initial creation of the activity.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     *                           down then this Bundle contains the data it most recently supplied in
     *                           {@link #onSaveInstanceState(android.os.Bundle)}
     *                           <strong>Note: Otherwise it is null.</strong>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GeneralUtil.setupVibrator(this);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Game.init(this);

        mGameLoop = (MineLoop) MineLoop.init();
        Thread thread = new Thread(mGameLoop, "Game Thread");
        thread.start();
    }

    /**
     * Callback method called when the activity is no longer user visible or something is overlaying
     * it.
     */
    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.


        mGLView.onPause();
        mGLView = null;

        mGameLoop.pause();
    }

    /**
     * Callback method called when the activity is either returning to visible after pausing, or is
     * becoming visible for the first time. It's the last callback before giving control to the user.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        //mGLView.onResume();

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = new OpenGLSurfaceView(this);
        Renderer renderer = new MainRenderer(this, mGLView);
        mGLView.init(renderer);

        Text.FontManager.setDefaultFont(new Text.Font("TestAlpha", new String[]{Constants.Textures.Alphabet1}));

        setContentView(mGLView);
        mGLView.onResume();

        mGameLoop.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mGameLoop.stop();
        Game.getInstanceUnsafe().releaseContext();
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
}
