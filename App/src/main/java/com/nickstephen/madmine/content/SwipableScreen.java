package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.GestureFilter;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 21/04/2014.
 */
public class SwipableScreen extends RootContainer implements GestureDetector.SimpleOnGestureListener {
    private GestureFilter detector;

    public SwipableScreen(@NotNull GLSurfaceView surface, @NotNull final Context context, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        // Add a title
        Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Swipe Away!!!");
        titleText.moveTo(0, 3 * height / 8.0f);
        this.mChildren.add(titleText);


        // Detect touched area
        detector = new GestureFilter(this, this);
    }

        @Override
        public boolean dispatchTouchEvent(MotionEvent me){
            // Call onTouchEvent of GestureFilter class.
            this.detector.onTouchEvent(me);
            return super.dispatchTouchEvent(me);
        }

        @Override
        public void onSwipe(int direction){
            String str = "";
            switch(direction){
                case GestureFilter.SWIPE_RIGHT: str = "Swipe Right";
                    break;
                case GestureFilter.SWIPE_LEFT: str = "Swipe Left";
                    break;
                case GestureFilter.SWIPE_UP: str = "Swipe Up:";
                    break;
                case GestureFilter.SWIPE_DOWN: str = "Swipe Down";
                    break;
            }
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }

        @Override
                public void onDoubleTap(){
            Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
        }




    }