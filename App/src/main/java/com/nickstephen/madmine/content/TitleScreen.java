package com.nickstephen.madmine.content;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.nickstephen.gamelib.anim.AlphaAnimation;
import com.nickstephen.gamelib.anim.Animation;
import com.nickstephen.gamelib.anim.RotationAnimation;
import com.nickstephen.gamelib.anim.TranslationAnimation;
import com.nickstephen.gamelib.opengl.AnimatedSprite;
import com.nickstephen.gamelib.opengl.Shape;
import com.nickstephen.gamelib.opengl.layout.Container;
import com.nickstephen.gamelib.opengl.layout.RootContainer;
import com.nickstephen.gamelib.opengl.text.Text;
import com.nickstephen.gamelib.opengl.widget.Circle;
import com.nickstephen.gamelib.opengl.gestures.IOnClickL;
import com.nickstephen.gamelib.opengl.widget.Triangle;
import com.nickstephen.lib.Twig;
import com.nickstephen.madmine.util.Constants;

import org.jetbrains.annotations.NotNull;

/**
 * An example implementation of RootContainer
 * @author Nick Stephen
 */
public class TitleScreen extends RootContainer {
    public TitleScreen(@NotNull final Context context, @NotNull GLSurfaceView surface, float width, float height) {
        super(context, surface, width, height, Constants.ROBOTO_FONT);

        Text titleText = new Text(context, this, Constants.ROBOTO_FONT, "Mine Games");
        titleText.moveTo(0, height / 4.0f);
        new AlphaAnimation(titleText, 1.0f, 0.0f).infiniteLoop().start();
        this.mChildren.add(titleText);

        Shape start = new Circle(context, this, 75, new float[] { 0.0f, 1.0f, 0.0f, 1.0f} );
        start.setSurface(surface);
        start.moveTo(0, -100.0f);
        start.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                // do nothing
                Twig.debug("TitleScreen", "Circle clicked!");
            }
        });
        start.setOnLongClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                Twig.debug("TitleScreen", "Circle long clicked!");
                //StatMethods.hotBread(context, "Circle Long Clicked!", Toast.LENGTH_SHORT);
            }
        });
        new AlphaAnimation(start, 1.0f, 0.0f).infiniteLoop().start();
        start.setFixed(false);
        this.mChildren.add(start);

        start = new Triangle(context, this, 0, -100.0f, 50.0f, 35.0f, new float[] { 0.0f, 0.0f, 1.0f, 1.0f });
        new AlphaAnimation(start, 1.0f, 0.0f).infiniteLoop().start();
        RotationAnimation rot = new RotationAnimation(start, 0, 360);
        rot.setLoopDuration(5000).setLoop(true).infiniteLoop().start(2000);
        start.setOnClickListener(new IOnClickL() {
            @Override
            public void onClick(Shape shape) {
                Twig.debug("TitleScreen", "Triangle clicked!");
            }
        });
        new TranslationAnimation(start, 0, -100.0f, 100.0f, 0.0f).setLoopDuration(5000).infiniteLoop()
                .setLoop(true).setLoopingStyle(Animation.LoopStyle.REVERSE).start(2000);
        this.mChildren.add(start);

        AnimatedSprite sprite = new AnimatedSprite(context, this, Constants.Textures.TEST, 200, 200, 2, 2);
        sprite.setupAnimation();
        new AlphaAnimation(sprite, 1.0f, 0.0f).infiniteLoop().setLoopingStyle(Animation.LoopStyle.REVERSE).start();

        this.mChildren.add(sprite);

        Container content = new MainContent(context, this, 200.0f, 200.0f, -270.0f, 100.0f);
        this.mChildContainers.add(content);
    }
}
