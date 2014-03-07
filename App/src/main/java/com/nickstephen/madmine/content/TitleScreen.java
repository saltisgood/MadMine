package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.widget.Toast;

import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.widget.Circle;
import com.nickstephen.gamelib.opengl.widget.IOnClickL;
import com.nickstephen.gamelib.opengl.widget.Text;
import com.nickstephen.gamelib.opengl.widget.Triangle;
import com.nickstephen.lib.misc.StatMethods;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 6/03/14.
 */
public class TitleScreen extends Container {
    public TitleScreen(@NotNull GLSurfaceView surface, @NotNull final Context context, float width, float height) {
        super(context, null, width, height, 0, 0);
        this.setSurface(surface);

        Text titleText = new Text(context, this, "Mine Games", Constants.ROBOTO_FONT);
        titleText.setSurface(surface);
        titleText.moveTo(0, height / 4.0f);
        this.mChildren.add(titleText);

        Shape start = new Circle(context, this, 75, new float[] { 0.0f, 1.0f, 0.0f, 1.0f} );
        start.setSurface(surface);
        start.moveTo(0, -100.0f);
        start.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Circle Pressed!", Toast.LENGTH_SHORT);
                Log.d("TitleScreen", "Circle pressed!");
            }
        });
        start.setOnLongClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Circle Long Clicked!", Toast.LENGTH_SHORT);
            }
        });
        this.mChildren.add(start);

        start = new Triangle(context, this, 0, -100.0f, 50.0f, 0.0f, new float[] { 0.0f, 0.0f, 1.0f, 1.0f });
        start.setSurface(surface);
        this.mChildren.add(start);

        Container content = new MainContent(context, this, 200.0f, 200.0f, -270.0f, 100.0f);
        content.setSurface(surface);
        //content.setParentOffset(-270.0f, 100.0f);
        this.mChildContainers.add(content);
    }


}
