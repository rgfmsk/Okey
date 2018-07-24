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
        findRuns(hand);
    }

    private Tile nextTileInARun(Tile current, List<Tile> list, List<Tile> subList, List<Tile> jokerList) {
        int cnt = 0;
        if (subList.get(subList.size() - 1).isJoker()) { //check if the last Tile is joker in this run
            cnt = 2; // if it is joker, get the previous element
        } else {
            cnt = 1;
        }

        Tile lastInLine = subList.get(subList.size() - cnt);
        Tile previous = null;
        if (lastInLine.getNumber() == 13) {// if the last Tile in this run is 13, check if there's a Tile number 1 with same color
            if (subList.size() > 1) {//if there's at least 2 Tiles in this run
                previous = subList.get(subList.size() - 2);
            }
            if (previous != null) {
                List<Tile> collect = list.stream().filter(x -> x.getColor().equals(lastInLine.getColor()) && x.getNumber() == 1).collect(Collectors.toList()); // check if there's number 1
                if (!collect.isEmpty()) {
                    return collect.get(0);
                }
            }
        } else { // if the last Tile is not 13, check if theres plus 1 Number
            List<Tile> collect = list.stream().filter(x -> x.getColor().equals(lastInLine.getColor()) && x.getNumber() == lastInLine.getNumber() + 1).collect(Collectors.toList());
            if (!collect.isEmpty())
                return collect.get(0);
        }

        if (jokerList.size() > 0 && subList.stream().filter(Tile::isJoker).count() < jokerList.size()) { // if not retured before, check if theres any jokers left
            return jokerList.get((int) subList.stream().filter(Tile::isJoker).count());
        }

        return null;

    }

    private void findRuns(final Hand hand) {
        List<List<Tile>> tempRunsList = new ArrayList<>();

        for (Tile.Color color : Tile.Color.values()) { // for each color
            List<Tile> collect = hand.getTileList().stream().filter(x -> x.getColor() == color).distinct().collect(Collectors.toList());// find the same colored Tiles
            collect.sort(Comparator.comparing(Tile::getNumber)); //sort the collection

            List<Tile> tempRun = new ArrayList<>();

            for (Tile t : collect) { // for each tile

                if (tempRun.isEmpty()) {// if the run list is empty, add the first Tile as first
                    tempRun.add(t);
                    continue;
                }

                Tile tile = nextTileInARun(t, collect, tempRun, new ArrayList<>()); //if there's a Tile which is plus 1 of the last Tile in the run without joker
                if (tile == null) { // if not
                    tile = nextTileInARun(t, collect, tempRun, hand.getJokerList()); //check if theres a joker left
                    if (tile != null) { // if there is a joker
                        tempRun.add(tile); // add to the run
                        tile = nextTileInARun(t, collect, tempRun, new ArrayList<>()); // check if the run would get one more Tile
                        if (tile != null) { //
                            if (tempRun.size() >= 3) {
                                tempRunsList.add(tempRun);
                                tempRun.remove(tile);
                            }
                            continue;
                        } else {
                            if (tempRun.size() >= 3) {
                                tempRunsList.add(tempRun);
                            }
                            tempRun = new ArrayList<>();
                            tempRun.add(t);
                        }
                    } else {
                        if (tempRun.size() >= 3) {
                            tempRunsList.add(tempRun);
                            tempRun = new ArrayList<>();
                        }
                    }
                } else {
                    tempRun.add(tile);
                }

                if (t.getNumber() == 13) {
                    tile = nextTileInARun(t, collect, tempRun, new ArrayList<>());
                    if (tile != null) {
                        tempRun.add(tile);
                    }
                }
            }

            if (tempRun.size() >= 3) {
                tempRunsList.add(tempRun);
                tempRun = new ArrayList<>();
            }
        }

        hand.setRunsList(tempRunsList);

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
