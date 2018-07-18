package com.digitoy.emre.util;

import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayerUtil {

    private Random rand = new Random();

    public void init(final List<Player> playerList) {

        for (int i = 0; i < 4; i++) {
            Player player = new Player(i, "Player " + (i + 1));
            playerList.add(player);
        }

    }

    public void decideExtraTile(final List<Player> playerListesi) {
        playerListesi.get(rand.nextInt(4)).setExtraTile(true);
    }

    public void distributeTiles(final List<Player> playerList, final LinkedList<Tile> tileList) {
        for (int i = 0; i < 14; i++) {
            playerList.forEach(x -> {
                x.getTileList().add(tileList.remove(rand.nextInt(tileList.size() - 1)));
                if (x.isExtraTile() && x.getTileList().size() == 14) {
                    x.getTileList().add(tileList.remove(rand.nextInt(tileList.size() - 1)));
                }
            });
        }
    }
}
