package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.Animation;
import com.nickstephen.gamelib.opengl.shapes.Polygon;
import com.nickstephen.gamelib.opengl.shapes.Shape;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.madmine.Game;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Nick Stephen on 13/03/14.
 */
public class StartScreen extends RootContainer {
    public StartScreen(@NotNull Context context, @NotNull GLSurfaceView surface, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        /* Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Mine Games");
        titleText.moveTo(0, height / 4.0f);
        this.mChildren.add(titleText); */

        Shape shape = Polygon.createCircle(context, this, 100);
        shape.moveTo(0, height / -4.0f);
        shape.setOnClickListener(shape1 -> Game.getInstanceUnsafe().start());
        this.mChildren.add(shape);

        shape = Polygon.createTriangle(context, this, 80.f);
        shape.moveTo(0, height / -200.0f);
        shape.setColour(0.0f, 1.0f, 0.0f, 1.0f);
        new AlphaAnimation(shape, 1.0f, 0.5f).infiniteLoop().setLoopingStyle(Animation.LoopStyle.REVERSE)
                .start();
        this.mChildren.add(shape);

        //Shape alph = new SpriteBatch(context, this, Constants.Textures.Alphabet1, 400, 400);

        Text alph = Text.FontManager.getText(mContext, getParent(),
                new Text.Font("TestAlpha", new String[]{Constants.Textures.Alphabet1}));
        alph.setColour(1.0f, 1.0f, 0.0f, 1.0f);
        alph.moveTo(0.f, height / -4.f);
        alph.setText("Hullo!");
        this.mChildren.add(alph);
    }
}
