package com.nickstephen.madmine.map;

import android.content.Context;

import com.nickstephen.lib.Twig;
import com.nickstephen.lib.misc.BitConverter;
import com.nickstephen.madmine.entities.GenericEntity;
import com.nickstephen.madmine.entities.PlayerChar;
import com.nickstephen.madmine.util.BufferedStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Used for generating {@link com.nickstephen.madmine.map.Map} objects from file.
 */
public class MapLoader {
    private static final int SIZEOF_INTEGER = 4;

    public static Map fromFile(Context context, String fileName) throws IOException {
        BufferedStream fileStream;

        try {
            fileStream = new BufferedStream(context.getAssets().open(fileName));
        } catch (IOException e) {
            Twig.error("MapLoader", "Error opening " + fileName + " in assets!");
            throw e;
        }

        Map map = null;
        try {
            int magic = fileStream.readInt();
            if (magic != Map.MAP_MAGIC_NO) {
                Twig.debug("MapLoader", "Map contains invalid magic number: " + magic);
                return null;
            }

            char version = fileStream.readChar(); // Could be shortened to char //
            // Use in the future to determine whether the user needs to upgrade before loading map (dlc/user made maps???) //

            char width = fileStream.readChar();
            if (width < 0) { // Sanity check //
                Twig.debug("MapLoader", "Invalid map width");
                return null;
            }

            char height = fileStream.readChar();
            if (height < 0) { // Sanity check //
                Twig.debug("MapLoader", "Invalid map height");
                return null;
            }

            char scoreFinish = fileStream.readChar();

            char scoreTrophy = fileStream.readChar();

            map = new Map(width, height, scoreFinish, scoreTrophy);

            char optional = fileStream.readChar(); // Give some space for future optional headers //

            // Not used at the moment so just skip //
            fileStream.skip(optional * SIZEOF_INTEGER);

            List<GenericEntity> entities = new ArrayList<GenericEntity>();
            PlayerChar player = null;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    short entity = fileStream.readShort();
                    short subtype = fileStream.readShort();

                    if (entity != 0) { // Not empty space. //
                        GenericEntity entityObj;

                        if (GenericEntity.isPlayer(entity)) {
                            if (player != null) { // There can only be 1 player!!! //
                                Twig.debug("MapLoader", "Multiple players in map file!");
                                return null;
                            }

                            player = GenericEntity.createPlayer(x, y, subtype);
                            entityObj = player;
                        } else { // Any more necessary type checks? //
                            entityObj = GenericEntity.create(x, y, entity, subtype);
                        }

                        map.setEntityAtPosition(entityObj, x, y);
                        entities.add(entityObj);
                    }
                }
            }

            map.mMapVersionNo = version;

            if (player == null) { // Map must contain a player! //
                Twig.debug("MapLoader", "No players in map file!");
                return null;
            }
            map.mPlayer = player;

            map.mEntities = entities;

            // TODO: Any additional processing? Footers, user information? //
        } catch (IOException e) {
            Twig.error("MapLoader", "Error reading " + fileName + " in assets!");
            throw e;
        } finally {
            fileStream.close();
        }

        return map;
    }
}
