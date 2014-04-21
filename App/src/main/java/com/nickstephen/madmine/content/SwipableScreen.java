package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Ben on 21/04/2014.
 */
public class SwipableScreen extends RootContainer {
    public SwipableScreen(@NotNull GLSurfaceView surface, @NotNull final Context context, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        // Add a title
        Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Swipe Away!!!");
        titleText.moveTo(0, 3 * height / 8.0f);
        this.mChildren.add(titleText);


    }
}
