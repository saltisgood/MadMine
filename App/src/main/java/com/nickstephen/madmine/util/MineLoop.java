package com.nickstephen.madmine.util;

import android.os.Handler;

import com.nickstephen.gamelib.run.GameLoop;

/**
 * <p>Class for implementing the game specific logic. This is potentially the most important class in
 * the whole project as this is the game's brain.</p>
 *
 * <p><strong>Note: This class is a looper and will completely block the thread, so run it on a new
 * thread! It should be used as
 * follows:</strong></p>
 * <code>new Thread(GameLoop.init()).start();</code>
 * @author Nick Stephen
 */
public class MineLoop extends GameLoop {
    /**
     * Initialise the loop singleton. At the moment it just initialises a new MineLoop, so I might
     * fix this up to a proper singleton later.
     * @return A new instance of the GameLoop
     */
    public static GameLoop init() {
        return sInstance = new MineLoop();
    }



    /**
     * Protected instance of the constructor so that it can't be called elsewhere.
     */
    protected MineLoop() {
        super(GameLoop.HZ_60); // Update the game at 60 ticks/second
    }

    @Override
    protected void updateGameLogic() {
        checkGameStatus();
        runNPCs();

        // Put game logic here!!!
    }

    protected void checkGameStatus() {
        // Active,
        // Paused, or
        // Game over.
    }

    protected void runNPCs(){
        // For each NPC:
            // If passive:
                // Execute next action.
    }


}
