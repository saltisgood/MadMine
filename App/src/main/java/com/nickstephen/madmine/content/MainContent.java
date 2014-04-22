package com.nickstephen.madmine.content;

import android.content.Context;

import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.widget.Square;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Generic testing class
 * @author Nick Stephen
 */
public class MainContent extends Container {
    public MainContent(@NotNull final Context context, @Nullable Container parent, float width, float height, float parentOffsetX, float parentOffsetY) {
        super(context, parent, width, height, parentOffsetX, parentOffsetY);
        //this.setBoundsSize(width + 50.0f, height + 50.0f);
        this.setUnlimitedBounds(true);
        this.setScrollable(true);


        Shape square = new Square(context, this, 25, 25, 60);
        this.mChildren.add(square);
    }


}
