package com.digitoy.emre;

import com.digitoy.emre.model.Tile;
import com.digitoy.emre.util.TileUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;


public class TileUtilTest {

    @Test
    public void Test() {
        final LinkedList<Tile> tileList = new LinkedList<>();

        TileUtil tileUtil = new TileUtil();
        tileUtil.init(tileList);

        Assert.assertEquals(tileList.size(), 106);
        Assert.assertEquals(tileList.getLast().getId(), 52);
        Assert.assertEquals(tileList.getFirst().getId(), 0);
        Assert.assertEquals(tileList.stream().filter(x -> x.getId() == 50).count(), 2);
    }

}
