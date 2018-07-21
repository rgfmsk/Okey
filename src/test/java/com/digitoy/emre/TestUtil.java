package com.digitoy.emre;

import com.digitoy.emre.model.Player;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    private List<Player> players = new ArrayList<>();

    public TestUtil() {

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
