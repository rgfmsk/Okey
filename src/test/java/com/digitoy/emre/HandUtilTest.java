package com.digitoy.emre;

import com.digitoy.emre.model.Hand;
import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.HandUtil;
import com.digitoy.emre.util.TileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class HandUtilTest {

    @Test
    public void Test() {
        Hand hand = new Hand();

        TileUtil tileUtil = new TileUtil();

        LinkedList<Tile> tileList = new LinkedList<>();

        Tile red2 = tileUtil.createTileFromId(40); //red2
        Tile red3 = tileUtil.createTileFromId(41); //red3
        Tile red5 = tileUtil.createTileFromId(43); //red5
        tileList.add(red2);
        tileList.add(red2);
        tileList.add(red3);
        tileList.add(red5);

        Tile black11 = tileUtil.createTileFromId(36); //black11
        Tile black12 = tileUtil.createTileFromId(37); //black12
        Tile black13 = tileUtil.createTileFromId(38); //black13
        tileList.add(black11);
        tileList.add(black12);
        tileList.add(black13);

        Tile black10 = tileUtil.createTileFromId(35); //black10
        Tile blue10 = tileUtil.createTileFromId(22); //blue10
        Tile red10 = tileUtil.createTileFromId(48); //red10
        tileList.add(black10);
        tileList.add(blue10);
        tileList.add(red10);

        Tile blue11 = tileUtil.createTileFromId(23); //blue11
        Tile blue12 = tileUtil.createTileFromId(24); //blue12
        Tile blue13 = tileUtil.createTileFromId(25); //blue13
        Tile blue1 = tileUtil.createTileFromId(13); //blue1
        tileList.add(blue11);
        tileList.add(blue12);
        tileList.add(blue13);
        tileList.add(blue1);

        Tile red1 = tileUtil.createTileFromId(39); //red1
        red1.setJoker(true);
        tileList.add(red1);

        tileList.sort(Comparator.comparing(Tile::getNumber).thenComparing(Tile::getId));

        hand.setTileList(tileList);
        Player p = new Player(0, "Player Test");
        p.setHand(hand);

        HandUtil handUtil = new HandUtil();
        handUtil.initHand(hand);


        Assert.assertEquals(1, hand.getJokerList().size()); //red1
        Assert.assertEquals(1, hand.getPairsList().size()); //red2
        Assert.assertEquals(4, hand.getSetsList().size()); // (10,10,10) , (11,11,J) , (12,12,J) , (13,13,J)
        Assert.assertEquals(4, hand.getRunsList().size()); // (2,3,J), (2,3,J,5), (10,11,12,13) , (10,11,12,13,1)


    }
}
