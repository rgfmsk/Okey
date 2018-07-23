package com.digitoy.emre;

import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.TileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestUtil {

    @Test
    public void Test() {

        SortedSet<Tile> tempSet = new TreeSet(Comparator.comparing(Tile::getNumber).thenComparing(Tile::getId));

        TileUtil tileUtil = new TileUtil();

        Tile t4 = tileUtil.createTileFromId(4);
        Tile t3 = tileUtil.createTileFromId(3);
        Tile t2 = tileUtil.createTileFromId(2);
        Tile t1 = tileUtil.createTileFromId(1);

        tempSet.add(t4);
        tempSet.add(t3);
        tempSet.add(t2);
        tempSet.add(t1);


        Assert.assertEquals(1, tempSet.stream().collect(Collectors.toList()).get(0).getId());
        Assert.assertEquals(2, tempSet.stream().collect(Collectors.toList()).get(1).getId());
        Assert.assertEquals(3, tempSet.stream().collect(Collectors.toList()).get(2).getId());
        Assert.assertEquals(4, tempSet.stream().collect(Collectors.toList()).get(3).getId());
    }

}
