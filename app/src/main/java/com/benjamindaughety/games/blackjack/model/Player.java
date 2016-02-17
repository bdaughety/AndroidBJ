package com.benjamindaughety.games.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamindaughety on 2/13/16.
 */
public class Player {
    private List<Hand> hands;
    private Double bank;

    public Player() {
        hands = new ArrayList<>();
        bank = 10000.0;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public Double getBank() {
        return bank;
    }

    public void setBank(Double bank) {
        this.bank = bank;
    }
}
