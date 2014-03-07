package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.widget.IOnClickL;
import com.nickstephen.gamelib.opengl.widget.Square;
import com.nickstephen.lib.misc.StatMethods;

/**
 * Created by Nick Stephen on 7/03/14.
 */
public class MainContent extends Container {
    public MainContent(GLSurfaceView surface, final Context context, float width, float height) {
        super(context, width, height);
        this.setSurface(surface);
        //this.setBoundsSize(width + 50.0f, height + 50.0f);
        this.setUnlimitedBounds(true);
        this.setScrollable(true);

        Shape square = new Square(context, 25, 25, 60);
        square.setSurface(surface);
        /* square.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Square pressed", Toast.LENGTH_SHORT);
            }
        });
        square.setOnLongClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Square long pressed", Toast.LENGTH_SHORT);
            }
        }); */
        this.mChildren.add(square);
    }


}
