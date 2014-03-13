package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.Animation;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.gamelib.opengl.widget.Circle;
import com.nickstephen.gamelib.opengl.widget.IOnClickL;
import com.nickstephen.gamelib.opengl.widget.Triangle;
import com.nickstephen.madmine.Game;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 13/03/14.
 */
public class StartScreen extends RootContainer {
    public StartScreen(@NotNull Context context, @NotNull GLSurfaceView surface, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Mine Games");
        titleText.moveTo(0, height / 4.0f);
        this.mChildren.add(titleText);

        Shape shape = new Circle(context, this, 100);
        shape.moveTo(0, height / -4.0f);
        shape.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                Game.getInstanceUnsafe().start();
            }
        });
        //new AlphaAnimation(shape, 1.0f, 0.5f).infiniteLoop().setLoopingStyle(Animation.LoopStyle.REVERSE)
        //        .start();
        this.mChildren.add(shape);

        shape = new Triangle(context, this, 0, height / -4.0f, 80);
        shape.setColour(0.0f, 1.0f, 0.0f, 1.0f);
        new AlphaAnimation(shape, 1.0f, 0.5f).infiniteLoop().setLoopingStyle(Animation.LoopStyle.REVERSE)
                .start();
        this.mChildren.add(shape);
    }
}
