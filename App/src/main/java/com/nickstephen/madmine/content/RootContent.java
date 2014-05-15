package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.opengl.gestures.GestureEvent;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.widget.Square;
import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.map.Map;
import com.nickstephen.madmine.map.MapLoader;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by Nick Stephen on 28/04/2014.
 */
public class RootContent extends RootContainer {
    protected Map mMap;

    public RootContent(@NotNull Context context, @NotNull GLSurfaceView surface, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        try {
            mMap = MapLoader.fromFile(context, this, Constants.Maps.TEST);
        } catch (IOException e) {
            Twig.printStackTrace(e);
        }
    }

    @Override
    public void draw(float[] projMatrix, float[] viewMatrix) {
        super.draw(projMatrix, viewMatrix);

        mMap.draw(projMatrix, viewMatrix);
    }

    @Override
    protected boolean onInterceptGestureEvent(@NotNull GestureEvent e) {
        if (mMap != null) {
            mMap.onGesture(this, e);
            return true;
        }
        return super.onInterceptGestureEvent(e);
    }
}
