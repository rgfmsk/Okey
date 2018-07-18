package com.digitoy.emre;

import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.PlayerUtil;
import com.digitoy.emre.util.PrinterUtil;
import com.digitoy.emre.util.TileUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Okey {

    static Random rand = new Random();

    public static void main(String[] args) {

        final List<Player> playerList = new ArrayList<>();
        final LinkedList<Tile> tileList = new LinkedList<>();

        PlayerUtil playerUtil = new PlayerUtil(); //player utilization class
        TileUtil tileUtil = new TileUtil(); // tile utilization class

        playerUtil.init(playerList); // create 4 player
        playerUtil.decideExtraTile(playerList); //decide which one got the extra tile

        tileUtil.init(tileList); // create 106 tiles
        tileUtil.shuffle(tileList); // shuffle the tile list
        tileUtil.pickFaceUpTile(tileList); // pick face-up tiles
        tileUtil.pickJoker(tileList); // pick joker tile according to face-up tile
        tileUtil.fillFakeJokerValues(tileList); // sahte okeyin değerini okey ile aynı yap

        playerUtil.distributeTiles(playerList, tileList); // taşları oyunculara dağıt


        tileList.stream().filter(y -> y.isFakeJoker() || y.isJoker() || y.isFaceUp()).forEach(x -> System.out.println(" id: " + x.getId() +
                " - " + x.getColor() + " - " + x.getNumber() +
                " index : " + tileList.indexOf(x) +
                " - " + x.isFaceUp() + " - " + x.isJoker() + " - " + x.isFakeJoker()));

        PrinterUtil pu = new PrinterUtil();
        pu.printPlayers(playerList);

    }


}
