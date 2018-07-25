package com.digitoy.emre.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Hand {
    private List<Tile> tileList = new ArrayList<>();
    private List<Tile> jokerList = new ArrayList<>();
    private List<List<Tile>> runsList = new ArrayList<>();
    private List<Set<Tile>> setsList = new ArrayList<>();
    private List<List<Tile>> pairsList = new ArrayList<>();
    private List<List<Tile>> allList = new ArrayList<>();
    private List<List<Tile>> finalList = new ArrayList<>();

    public Hand() {
    }

    public Hand(List<Tile> tileList, List<Tile> jokerList, List<List<Tile>> runsList, List<Set<Tile>> setsList, List<List<Tile>> pairsList, List<List<Tile>> allList, List<List<Tile>> finalList) {
        this.tileList = tileList;
        this.jokerList = jokerList;
        this.runsList = runsList;
        this.setsList = setsList;
        this.pairsList = pairsList;
        this.allList = allList;
        this.finalList = finalList;
    }

    public List<Tile> getTileList() {
        return tileList;
    }

    public void setTileList(List<Tile> tileList) {
        this.tileList = tileList;
    }

    public List<Tile> getJokerList() {
        return jokerList;
    }

    public void setJokerList(List<Tile> jokerList) {
        this.jokerList = jokerList;
    }

    public List<List<Tile>> getRunsList() {
        return runsList;
    }

    public void setRunsList(List<List<Tile>> runsList) {
        this.runsList = runsList;
    }

    public List<Set<Tile>> getSetsList() {
        return setsList;
    }

    public void setSetsList(List<Set<Tile>> setsList) {
        this.setsList = setsList;
    }

    public List<List<Tile>> getPairsList() {
        return pairsList;
    }

    public void setPairsList(List<List<Tile>> pairsList) {
        this.pairsList = pairsList;
    }

    public List<List<Tile>> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<List<Tile>> unusedTileList) {
        this.finalList = finalList;
    }

    public List<List<Tile>> getAllList() {
        return allList;
    }

    public void setAllList(List<List<Tile>> allList) {
        this.allList = allList;
    }
}
