package com.digitoy.emre.util;

import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PlayerUtil {

    private Random rand = new Random();

    public void init(final List<Player> players) {

        for (int i = 0; i < 4; i++) {
            Player player = new Player(i, "Player " + (i + 1));
            players.add(player);
        }

    }

    public void decideExtraTile(final List<Player> players) {
        players.get(rand.nextInt(4)).setExtraTile(true); // pick a user which would hold 15 tiles
    }

    public void distributeTiles(final List<Player> players, final LinkedList<Tile> tiles) { //distribute 14 tiles to each player,
        for (int i = 0; i < 14; i++) {
            players.forEach(x -> {
                x.getHand().getTileList().add(tiles.remove(rand.nextInt(tiles.size() - 1)));
                if (x.isExtraTile() && x.getHand().getTileList().size() == 14) { //control at the last loop, if the user would have 15 tiles
                    x.getHand().getTileList().add(tiles.remove(rand.nextInt(tiles.size() - 1)));
                }
            });
        }
    }

    public void sortTiles(final List<Player> players) {//sort Tiles by number and color
        players.forEach(x -> x.getHand().getTileList().sort(Comparator.comparing(Tile::isJoker).thenComparing(Tile::getNumber).thenComparing(Tile::getColor)));
    }

}
