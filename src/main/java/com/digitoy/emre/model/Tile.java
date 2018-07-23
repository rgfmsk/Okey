package com.digitoy.emre.model;

public class Tile {
    private int id;
    private int number;
    private Color color;
    private boolean fakeJoker = false;
    private boolean indicator = false;
    private boolean joker = false;

    public Tile(int id, int number, Color color) {
        this.id = id;
        this.number = number;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFakeJoker() {
        return fakeJoker;
    }

    public void setFakeJoker(boolean fakeJoker) {
        this.fakeJoker = fakeJoker;
    }

    public boolean isIndicator() {
        return indicator;
    }

    public void setIndicator(boolean indicator) {
        this.indicator = indicator;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean joker) {
        this.joker = joker;
    }

    public enum Color {
        RED, BLACK, BLUE, YELLOW, FAKE_JOKER
    }
}
