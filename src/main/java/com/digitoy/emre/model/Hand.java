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
    private List<Tile> unusedTileList = new ArrayList<>();

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

    public List<Tile> getUnusedTileList() {
        return unusedTileList;
    }

    public void setUnusedTileList(List<Tile> unusedTileList) {
        this.unusedTileList = unusedTileList;
    }
}
