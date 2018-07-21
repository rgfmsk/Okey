package com.digitoy.emre.util;

import com.digitoy.emre.model.Tile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class TileUtil {

    private final static String FAKE_JOKER = "fake-joker";
    private final static String YELLOW = "yellow";
    private final static String RED = "red";
    private final static String BLACK = "black";
    private final static String BLUE = "blue";
    private final static int TOTAL_TILE_COUNT = 106;

    public void init(final LinkedList<Tile> tileList) {

        for (int t = 0; t < TOTAL_TILE_COUNT/2; t++) {
            Tile tileFromId = createTileFromId(t);
            tileList.add(tileFromId);
            tileList.add(tileFromId);
        }

    }

    public void shuffle(final LinkedList<Tile> tileList) {
        Collections.shuffle(tileList); // shuffle the linked list
    }

    public void fillFakeJokerValues(final LinkedList<Tile> tileList) {
        tileList.stream()
                .filter(Tile::isFakeJoker) // find the falke jokers
                .forEach(y -> {
                    Tile tile = tileList.stream() // store the joker tile object temporarily
                            .filter(Tile::isJoker)
                            .findFirst()
                            .get();
                    y.setNumber(tile.getNumber()); // set the number value of fakeJoker same as Joker
                    y.setColor(tile.getColor()); // set the color value of fakeJoker same as Joker
                });
    }

    public void pickJoker(final LinkedList<Tile> tileList) {
        tileList.stream()
                .filter(Tile::isIndicator).collect(Collectors.toList())//find each indicator Tiles
                .forEach(y -> {
                    if (y.getNumber() != 13) { // if the indicator value is not 13
                        tileList.stream()
                                .filter(z -> z.getId() == y.getId() + 1) // find the next Tile by adding 1
                                .findFirst()
                                .get()
                                .setJoker(true); //set the joker value to true
                    } else { // if the indicator value is 13, then joker must be number 1 with same color
                        tileList.stream()
                                .filter(z -> z.getId() == y.getId() - 13)// find the first Tile by subtracting 13
                                .findFirst()
                                .get()
                                .setJoker(true);
                    }
                });
    }

    public void pickIndicatorTile(final LinkedList<Tile> tileList) {

        Random rand = new Random();
        int random = rand.nextInt(tileList.size() - 1); //pick a random index value from the tileList
        final Tile t = tileList.get(random); // get the Tile object with that index

        if (t.isFakeJoker()) { // check if it's fake joker tile, if so repeat the process
            pickIndicatorTile(tileList);
        } else { // if not
            t.setIndicator(true); // set the value of indicator to true
            tileList.stream() // set the value of indicator on the matching Tile also.
                    .filter(x -> x.getId() == createTileFromId(t.getId()).getId())
                    .forEach(y -> y.setIndicator(true));
        }


    }


    private Tile createTileFromId(int id) { // create tile according to id
        Tile tile = new Tile(id, // id
                (id % 13) + 1, // number value of the tile (1,2,3,...13)
                getColor(id)); // color value of the tile
        if (id > 51) { // id greater then 103 means the last 2 tiles, and they are the fakeJokers
            tile.setFakeJoker(true);
        }
        return tile;
    }

    private String getColor(int sayi) { // return color according to number
        if (sayi < 13)
            return YELLOW;
        else if (sayi < 26)
            return BLUE;
        else if (sayi < 39)
            return BLACK;
        else if (sayi < 52)
            return RED;
        else
            return FAKE_JOKER;
    }
}
