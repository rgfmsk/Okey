package com.digitoy.emre;

import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.HandUtil;
import com.digitoy.emre.util.PlayerUtil;
import com.digitoy.emre.util.PrinterUtil;
import com.digitoy.emre.util.TileUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Okey {

    static Random rand = new Random();

    public static void main(String[] args) {

        final List<Player> playerList = new ArrayList<>();
        final LinkedList<Tile> tileList = new LinkedList<>();

        PlayerUtil playerUtil = new PlayerUtil(); //player utilization class
        TileUtil tileUtil = new TileUtil(); // tile utilization class
        HandUtil handUtil = new HandUtil();

        playerUtil.init(playerList); // create 4 player
        playerUtil.decideExtraTile(playerList); //decide which one got the extra tile

        tileUtil.init(tileList); // create 106 tiles
        tileUtil.shuffle(tileList); // shuffle the tile list

        tileUtil.pickIndicatorTile(tileList); // pick indicator tiles
        handUtil.setIndicators(tileList.stream().filter(Tile::isIndicator).collect(Collectors.toList())); // set Indicators list

        tileUtil.pickJoker(tileList); // pick joker tile according to indicator tile
        handUtil.setJokers(tileList.stream().filter(Tile::isJoker).collect(Collectors.toList())); // set Jokers list

        tileUtil.fillFakeJokerValues(tileList); // set fake joker values same as joker

        playerUtil.distributeTiles(playerList, tileList); // distribute tiles to players
        playerUtil.sortTiles(playerList);

        PrinterUtil pu = new PrinterUtil();
        pu.printPlayers(playerList);

        handUtil.initHands(playerList);
        
        System.out.println("Best hand is at : " + playerList.stream().filter(x->x.isWinner()).collect(Collectors.toList()).get(0).getName());
        pu.printPlayers(playerList.stream().filter(x->x.isWinner()).collect(Collectors.toList()));

    }


}
