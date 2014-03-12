package com.nickstephen.madmine.util;

import com.nickstephen.gamelib.run.GameLoop;

/**
 * Created by Nick Stephen on 12/03/14.
 */
public class MineLoop extends GameLoop {
    public static GameLoop init() {
        return sInstance = new MineLoop();
    }

    protected MineLoop() {
        super(GameLoop.HZ_60);
    }

    @Override
    protected void updateGameLogic() {
        // Put game logic here!!!
    }
}
