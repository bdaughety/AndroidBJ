package com.benjamindaughety.games.blackjack.model;

/**
 * Created by benjamindaughety on 2/13/16.
 */
public class Card {
    private String suit;
    private Integer position;
    private int drawableId;
    private Integer score;
    private String name;

    public Card(final String suit, final Integer position, final Integer score) {
        this.suit = suit;
        this.position = position;
        this.score = score;
        this.name = CardEnum.values()[position].label();
        this.drawableId = CardEnum.values()[position].drawableId();
    }

    public String getSuit() {
        return suit;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public Integer getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Integer getPosition() {
        return position;
    }

}
