package com.digitoy.emre;

import com.digitoy.emre.model.Hand;
import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.HandUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class HandUtilTest {

    @Test
    public void Test() {
        Hand hand = new Hand();

        LinkedList<Tile> tileList = new LinkedList<>();
        Tile yellow1 = new Tile(0, 1, "yellow");
        tileList.add(yellow1);
        tileList.add(yellow1);

        Tile red2 = new Tile(1, 2, "red");
        tileList.add(red2);
        tileList.add(red2);

        Tile black3 = new Tile(2, 11, "black");
        Tile black4 = new Tile(3, 12, "black");
        Tile black5 = new Tile(4, 13, "black");
        tileList.add(black3);
        tileList.add(black4);
        tileList.add(black5);

        Tile black10 = new Tile(5, 10, "black");
        Tile blue10 = new Tile(6, 10, "blue");
        Tile red10 = new Tile(7, 10, "red");
        tileList.add(black10);
        tileList.add(blue10);
        tileList.add(red10);

        Tile blue11 = new Tile(8, 11, "blue");
        Tile blue12 = new Tile(9, 12, "blue");
        Tile blue13 = new Tile(10, 13, "blue");
        Tile blue1 = new Tile(11, 1, "blue");
        tileList.add(blue11);
        tileList.add(blue12);
        tileList.add(blue13);
        tileList.add(blue1);

        Tile red1 = new Tile(12, 1, "red");
        red1.setJoker(true);
        tileList.add(red1);


        hand.setTileList(tileList);
        Player p = new Player(0, "Player Test");
        p.setHand(hand);

        HandUtil handUtil = new HandUtil();
        handUtil.initHand(hand);


        Set<Tile> collect1 = new HashSet<>(hand.getTileList());

        Assert.assertEquals(hand.getJokerList().size(), 1); //red1
        Assert.assertEquals(hand.getPairsList().size(), 2); //yellow1,red2
        Assert.assertEquals(hand.getSetsList().size(), 5); // (1,1,1) , (10,10,10) , (11,11,J) , (12,12,J) , (13,13,J)
        Assert.assertEquals(hand.getRunsList().size(), 2); // (3,4,5) , (11,12,13,1)


    }
}
