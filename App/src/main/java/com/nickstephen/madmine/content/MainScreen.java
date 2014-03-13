package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 13/03/14.
 */
public class MainScreen extends RootContainer {
    public MainScreen(@NotNull Context context, @NotNull GLSurfaceView surface, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        Text text = new Text(context, this, Constants.ROBOTO_FONT, "Started Game!");
        this.mChildren.add(text);
    }
}
