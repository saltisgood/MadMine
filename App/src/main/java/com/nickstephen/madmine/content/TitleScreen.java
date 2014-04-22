package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.Animation;
import com.nickstephen.gamelib.anim.RotationAnimation;
import com.nickstephen.gamelib.anim.TranslationAnimation;
import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.Sprite;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.gamelib.opengl.widget.Circle;
import com.nickstephen.gamelib.opengl.widget.IOnClickL;
import com.nickstephen.gamelib.opengl.widget.Triangle;
import com.nickstephen.lib.misc.StatMethods;
import com.nickstephen.madmine.util.Constants;
import com.nickstephen.madmine.util.MineLoop;

import org.jetbrains.annotations.NotNull;

/**
 * An example implementation of RootContainer
 * @author Nick Stephen
 */
public class TitleScreen extends RootContainer {
    public TitleScreen(@NotNull GLSurfaceView surface, @NotNull final Context context, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        // Title text
        Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Mine Games");
        titleText.moveTo(0, height / 4.0f);
        new AlphaAnimation(titleText, 1.0f, 0.0f).infiniteLoop().start();
        this.mChildren.add(titleText);

        // Add the circle shape and behaviour on long click.
        Shape start = new Circle(context, this, 75, new float[] { 0.0f, 1.0f, 0.0f, 1.0f} );
        start.setSurface(surface);
        start.moveTo(0, -100.0f);
        start.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Circle tapped!", Toast.LENGTH_SHORT);
            }
        });
        start.setOnLongClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                StatMethods.hotBread(context, "Circle Long Clicked!", Toast.LENGTH_SHORT);
            }
        });
        new AlphaAnimation(start, 1.0f, 0.0f).infiniteLoop().start();
        this.mChildren.add(start);

        // Add the triangle shape
        start = new Triangle(context, this, 0, -100.0f, 50.0f, 35.0f, new float[] { 0.0f, 0.0f, 1.0f, 1.0f });
        new AlphaAnimation(start, 1.0f, 0.0f).infiniteLoop().start();
        RotationAnimation rot = new RotationAnimation(start, 0, 360);
        rot.setLoopDuration(5000).setLoop(true).infiniteLoop().start(2000);

        new TranslationAnimation(start, 0, -100.0f, 100.0f, 0.0f).setLoopDuration(5000).infiniteLoop()
                .setLoop(true).setLoopingStyle(Animation.LoopStyle.REVERSE).start(2000);
        this.mChildren.add(start);

        // Add the flashing numbers and shit
        AnimatedSprite sprite = new AnimatedSprite(context, this, Constants.Textures.TEST, 200, 200, 2, 2);
        sprite.setupAnimation();
        new AlphaAnimation(sprite, 1.0f, 0.0f).infiniteLoop().setLoopingStyle(Animation.LoopStyle.REVERSE).start();

        this.mChildren.add(sprite);

        // Add little man sprite
        Sprite guysprite = new AnimatedSprite(context, this, Constants.Textures.IMG, 128, 128, 5, 5);
        guysprite.moveTo(50, 50);
        this.mChildren.add(guysprite);

        // Creates a new container MainContent which contains the blue diamond.
        Container content = new MainContent(context, this, 200.0f, 200.0f, -270.0f, 100.0f);
        this.mChildContainers.add(content);
    }
}
