package com.digitoy.emre.util;

import com.digitoy.emre.model.Player;
import com.digitoy.emre.model.Tile;

import java.util.List;

public class PrinterUtil {

    public void printPlayers(final List<Player> playerList) {
        playerList.forEach(x -> {
            System.out.println("player ==> " + x.getName());
            printTileList(x.getTileList());
        });
    }

    private void printHeader() {
        System.out.print(" _________  ");
    }

    private void printFooter(String s) {
        System.out.print("|" + fill_(9, s) + "| ");
    }

    private void print(String s) {
        System.out.print("|" + fill(9, s) + "| ");
    }

    public void printTile(Tile tile) {
        System.out.println(" _________ ");
        System.out.println("|         | ");
        System.out.println("|" + fill(9, tile.getColor()) + "| ");
        System.out.println("|" + fill(9, " ") + "|");
        System.out.println("|" + fill(9, tile.getNumber() + "") + "| ");
        System.out.println("|_________| ");
    }

    private void printTileList(List<Tile> x) {
        x.forEach(y -> printHeader());
        System.out.println();
        x.forEach(y -> print(y.isFakeJoker() ? "fakeJoker" : ""));
        System.out.println();
        x.forEach(y -> print(y.getColor()));
        System.out.println();
        x.forEach(y -> print(y.isFaceUp() ? "faceUp" : ""));
        System.out.println();
        x.forEach(y -> print(y.getNumber() + ""));
        System.out.println();
        x.forEach(y -> printFooter(y.isJoker() ? "Joker" : "_"));
        System.out.println();
        System.out.println();
    }

    private String fill(int len, String s) {
        StringBuilder builder = new StringBuilder();
        builder.append(s);
        for (int i = 0; i < len - s.length(); i++) builder.append(" ");
        return builder.toString();
    }

    private String fill_(int len, String s) {
        StringBuilder builder = new StringBuilder();
        builder.append(s);
        for (int i = 0; i < len - s.length(); i++) builder.append("_");
        return builder.toString();
    }
}
