package com.nickstephen.madmine.util;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/**
 * Created by Ben on 21/04/2014.
 */
public class GestureFilter extends GestureDetector.SimpleOnGestureListener{

    public final static int SWIPE_UP = 1;
    public final static int SWIPE_DOWN = 2;
    public final static int SWIPE_LEFT = 3;
    public final static int SWIPE_RIGHT = 4;

    public final static int MODE_TRANSPARENT = 0;
    public final static int MODE_SOLID = 1;
    public final static int MODE_DYNAMIC = 2;

    private final static int ACTION_FAKE = -13; // Just an unlikely number.  Come back to check on this one.
    private int swipe_Min_Distance = 100;
    private int swipe_Max_Distance = 350;
    private int swipe_Min_Velocity = 100;

    private int mode = MODE_DYNAMIC;
    private boolean running = true;
    private boolean tapIndicator = false;

    private Activity context;
    private GestureDetector detector;
    private GestureDetector.SimpleOnGestureListener listener;

    public GestureFilter(Activity context, GestureDetector.SimpleOnGestureListener sgl) {
        this.context = context;
        this.detector = new GestureDetector(context, this);
        this.listener = sgl;
    }

    public void onTouchEvent(MotionEvent event){

        if(!this.running)
            return;

        boolean result = this.detector.onTouchEvent(event);

        if(this.mode == MODE_SOLID)
            event.setAction(MotionEvent.ACTION_CANCEL);
        else if (this.mode == MODE_DYNAMIC){
            if(event.getAction() == ACTION_FAKE)
                event.setAction(MotionEvent.ACTION_UP);
            if(result)
                event.setAction(MotionEvent.ACTION_CANCEL);
            else if (this.tapIndicator){
                event.setAction(MotionEvent.ACTION_DOWN);
                this.tapIndicator = false;
            }
        }
        // 'Else do nothing, it's transparent.'
        // wtf does that mean
    }

    // Sets the current mode (Transparent/Solid/Dynamic) to the argument.
    public void setMode(int m){
        this.mode = m;
    }

    // Returns the current mode (Transparent/Solid/Dynamic).
    public int getMode(){
        return this.mode;
    }

    // Turns it on or off
    public void setEnabled(boolean status){
        this.running = status;
    }

    // Sets the maximum distance for a movement such that it is still considered to be a swipe and not a drag or something.
    public void setSwipeMaxDistance(int distance){
        this.swipe_Max_Distance = distance;
    }

    // Sets the minimum distance for a movement such that it is still considered a swipe and not a tap or something.
    public void setSwipeMinDistance(int distance){
        this.swipe_Min_Distance = distance;
    }

    // Sets the minimum velocity for a movement such that it is still considered to be a swipe and not a really slow drag or something.
    public void setSwipeMinVelocity(int velocity){
        this.swipe_Min_Velocity = velocity;
    }

    // Returns the maximum distance for a movement to be considered a swipe.
    public int getSwipeMaxDistance(){
        return this.swipe_Max_Distance;
    }

    // Returns the minimum distance for a movement to be considered a swipe.
    public int getSwipeMinDistance(){
        return this.swipe_Min_Distance;
    }

    // Returns the minimum velocity for a movement to be considered a swipe.
    public int getSwipeMinVelocity(){
        return this.swipe_Min_Velocity;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        final float xDistance = Math.abs(e1.getX() - e2.getX());
        final float yDistance = Math.abs(e1.getY() - e2.getY());

        if((xDistance > this.swipe_Max_Distance) || (yDistance > swipe_Max_Distance))
            return false;

        velocityX = Math.abs(velocityX);
        velocityY = Math.abs(velocityY);

        if((velocityX > this.swipe_Min_Velocity) && (xDistance > this.swipe_Min_Distance)){
            if(e1.getX() > e2.getX())
                this.listener.onSwipe(SWIPE_LEFT);
            else
                this.listener.onSwipe(SWIPE_RIGHT);
            return true;
        }
        else if((velocityY > this.swipe_Min_Velocity) && (yDistance > this.swipe_Min_Distance)){
            if(e1.getY() > e2.getY())
                this.listener.onSwipe(SWIPE_UP);
            else
                this.listener.onSwipe(SWIPE_DOWN);
            return true;
        }
    }

    // Triggers some sort of tap indicator if it's a tap.
    @Override
    public boolean onSingleTapUp(MotionEvent e){
        this.tapIndicator = true;
        return false;
    }


    // NFI what this does.
    @Override
    public boolean onDoubleTap(MotionEvent arg){
        this.listener.onDoubleTap();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent arg){
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent arg){

        if(this.mode == MODE_DYNAMIC){
            arg.setAction(ACTION_FAKE);
            this.context.dispatchTouchEvent(arg);
        }
        return false;
    }

    static interface SimpleGestureListener{
        void onSwipe(int direction);
        void onDoubleTap();
    }


}
