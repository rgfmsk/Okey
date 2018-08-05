package com.digitoy.emre.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private boolean extraTile = false;
    private boolean winner = false;
    private Hand hand = new Hand();
//    private List<Tile> tileList = new ArrayList<>();

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExtraTile() {
        return extraTile;
    }

    public void setExtraTile(boolean extraTile) {
        this.extraTile = extraTile;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
    
    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    //    public List<Tile> getTileList() {
//        return tileList;
//    }
//
//    public void setTileList(List<Tile> tileList) {
//        this.tileList = tileList;
//    }
}
