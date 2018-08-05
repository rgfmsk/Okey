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
        compareHands(players);
    }

    public void initHand(final Hand hand) {
        findJokers(hand);
        process(hand);
    }

    private void process(final Hand hand) {
        findPairs(hand);
        findSets(hand);
        findRuns(hand);
        collectAll(hand);
    }

    private void collectAll(final Hand hand) {
        hand.getAllList().addAll(hand.getPairsList());
        hand.getAllList().addAll(new ArrayList(hand.getSetsList()));
        hand.getAllList().addAll(hand.getRunsList());
    }

    private void compareHands(final List<Player> players) { // set the winner who has greatest possible number of tile lists
        int maxList = 0;

        for (Player player : players) {
            int playerSize = player.getHand().getAllList().size();
            if (maxList < playerSize) {
                maxList = playerSize;
                player.setWinner(true);
                players.stream().filter(x -> x.getName() != player.getName()).forEach(x -> x.setWinner(false));
            }
        }
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
            final int ccc = cnt;
            List<Tile> collect = list.stream().filter(x -> x.getColor().equals(lastInLine.getColor()) && x.getNumber() == lastInLine.getNumber() + ccc).collect(Collectors.toList());
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
                        if (tile != null) { //if it gets
                            if (tempRun.size() >= 3) {
                                tempRunsList.add(tempRun); //add the run to list
                                tempRun = new ArrayList<>(tempRun); //copy the run
                                tempRun.add(tile); //add the next tile to copied run (eg: 1,2,J and 1,2,J,4)
                            }
                        } else {//if it doesn't
                            if (tempRun.size() >= 3) {
                                tempRunsList.add(tempRun); // add the run to list
                            }
                            tempRun = new ArrayList<>(); //clear the run
                            tempRun.add(t); //add the current Tile to list
                        }
                    } else { // if no joker
                        if (tempRun.size() >= 3) { //check if the run is long enough
                            tempRunsList.add(tempRun);
                            tempRun = new ArrayList<>();
                        }
                    }
                } else { // if there's a tile with plus 1
                    tempRun.add(tile); // add to the run
                }

                if (t.getNumber() == 13) { // if the current Tile is 13
                    tile = nextTileInARun(t, collect, tempRun, new ArrayList<>()); //search for a 1, ( 12,13,1 )
                    if (tile != null) {
                        tempRun.add(tile);
                    }
                }
            }

            if (tempRun.size() >= 3) { // if list is long enough at the end of the loop
                tempRunsList.add(tempRun); // add the run to list
                tempRun = new ArrayList<>();
            }
        }

        hand.setRunsList(tempRunsList); //set the runsList

    }

    private void findJokers(final Hand hand) {
        List<Tile> tempList = hand.getTileList();
        Iterator<Tile> iterator = tempList.iterator();
        for (; iterator.hasNext(); ) {
            Tile t = iterator.next();
            if (t.isJoker()) { // find jokers
                iterator.remove(); // remove from Tile list
                hand.getJokerList().add(t); // add to joker List
            }
        }
    }

    private void findPairs(final Hand hand) {
        Set<Tile> collect1 = new HashSet<>(hand.getTileList());
        for (Tile tile : collect1) {
            List<Tile> collect = hand.getTileList().stream().filter(x -> x.getId() == tile.getId()).collect(Collectors.toList());
            if (collect.size() > 1) { // if there are pairs
                hand.getPairsList().add(collect); // add to pairs list
            }
        }
    }


    private void findSets(final Hand hand) {
        for (int i = 1; i < 14; i++) { // for each number 1 to 13
            final int num = i;
            Set<Tile> tileSet = hand.getTileList().stream().filter(x -> x.getNumber() == num).collect(Collectors.toSet()); //collect the same numbers distinctly
            if (tileSet.size() > 2) { // if the set is long enough
                hand.getSetsList().add(tileSet); // add to sets list
            } else if (tileSet.size() == 2 && hand.getJokerList().size() > 0) {// if its not long enough but there are jokers
                tileSet.add(hand.getJokerList().get(0)); // user the joker
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
