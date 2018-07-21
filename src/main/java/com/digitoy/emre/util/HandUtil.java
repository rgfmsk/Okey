package com.digitoy.emre.util;

import com.digitoy.emre.model.Hand;
import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class HandUtil {

    private List<Tile> jokers = new ArrayList<>();
    private List<Tile> indicators = new ArrayList<>();

    public void initHands(final List<Player> players) {
        for (Player player : players) {
            initHand(player.getHand());
        }
    }

    public void initHand(final Hand hand) {
        findJokers(hand);
        process(hand);
    }

    private void process(final Hand hand) {
        findPairs(hand);
        findSets(hand);
    }

    private void findJokers(final Hand hand) {
        List<Tile> tempList = hand.getTileList();
        Iterator<Tile> iterator = tempList.iterator();
        for (; iterator.hasNext(); ) {
            Tile t = iterator.next();
            if (t.isJoker()) {
                iterator.remove();
                hand.getJokerList().add(t);
            }
        }
    }

    private void findPairs(final Hand hand) {
        Set<Tile> collect1 = new HashSet<>(hand.getTileList());
        for (Tile tile : collect1) {
            List<Tile> collect = hand.getTileList().stream().filter(x -> x.getId() == tile.getId()).collect(Collectors.toList());
            if (collect.size() > 1) {
                hand.getPairsList().add(collect);
            }
        }
    }


    private void findSets(final Hand hand) {
        for (int i = 1; i < hand.getTileList().size(); i++) {
            final int num = i;
            Set<Tile> tileSet = hand.getTileList().stream().filter(x -> x.getNumber() == num).collect(Collectors.toSet());
            if (tileSet.size() > 2) {
                hand.getSetsList().add(tileSet);
            } else if (tileSet.size() == 2 && hand.getJokerList().size() > 0) {
                tileSet.add(hand.getJokerList().get(0));
                hand.getSetsList().add(tileSet);
            }
        }
    }

    public List<Tile> getJokers() {
        return jokers;
    }

    public void setJokers(List<Tile> jokers) {
        this.jokers = jokers;
    }

    public List<Tile> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Tile> indicators) {
        this.indicators = indicators;
    }
}
