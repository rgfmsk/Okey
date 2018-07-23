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

    private Tile nextTileInARun(List<Tile> list, List<Tile> subList, List<Tile> jokerList) {
        Tile current = subList.get(subList.size() - 1);
        Tile previous = null;
        if (current.getNumber() == 13) {
            if (subList.size() > 1) {
                previous = subList.get(subList.size() - 2);
            }
            if (previous != null) {
                List<Tile> collect = list.stream().filter(x -> x.getColor().equals(current.getColor()) && x.getNumber() == 1).collect(Collectors.toList());
                if (!collect.isEmpty()) {
                    return collect.get(0);
                }
            }
        } else {
            List<Tile> collect = list.stream().filter(x -> x.getColor().equals(current.getColor()) && x.getNumber() == current.getNumber() + 1).collect(Collectors.toList());
            if (!collect.isEmpty())
                return collect.get(0);
        }

        if (jokerList.size() > 0 && subList.stream().filter(Tile::isJoker).count() < jokerList.size()
                && (list.stream().filter(x -> x.getNumber() == current.getNumber() + 1).count() > 0 || jokerList.size() > 1)) {
            return jokerList.get(0);
        }

        return null;

    }

    private void findRuns(final Hand hand) {
        List<List<Tile>> tempRunsList = new ArrayList<>();

        for (Tile.Color color : Tile.Color.values()) {
            List<Tile> collect = hand.getTileList().stream().filter(x -> x.getColor() == color).distinct().collect(Collectors.toList());
            collect.sort(Comparator.comparing(Tile::getNumber));

            List<Tile> tempRun = new ArrayList<>();

            for (Tile t : collect) {

                if (tempRun.isEmpty()) {
                    tempRun.add(t);
                    continue;
                }

                Tile tile = nextTileInARun(collect, tempRun, hand.getJokerList());
                if (tile == null) {
                    if (tempRun.size() >= 3) {
                        tempRunsList.add(tempRun);
                        tempRun = new ArrayList<>();
                    }
                } else {
                    tempRun.add(tile);
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
