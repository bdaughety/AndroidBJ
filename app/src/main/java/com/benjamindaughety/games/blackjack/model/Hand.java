package com.benjamindaughety.games.blackjack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamindaughety on 2/13/16.
 */
public class Hand {
    private static final Integer BLACK_JACK = 21;
    private Boolean busted;
    private List<Card> cards;
    private Integer score;
    private Boolean standing;
    private Integer aceCount;
    private Double bet;

    public Hand() {
        this.busted = false;
        this.cards = new ArrayList<>();
        this.score = 0;
        this.standing = false;
        this.aceCount = 0;
        this.bet = 0.0;
    }

    public Boolean getBusted() {
        return busted;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Double getBet() {
        return bet;
    }

    public void setBet(Double bet) {
        this.bet = bet;
    }

    public Boolean getStanding() {
        return standing;
    }

    public List<Card> hit(final Card card) {
        if (card == null) {
            return null;
        }

        cards.add(card);
        score += card.getScore();
        if (card.getName().contains("Ace")) {
            aceCount++;
        }

        determineBusted();

        return cards;
    }

    private boolean determineBusted() {
        if (hasAce()) {
            if (score >= 31) {
                stand();
                if (score > 31) {
                    busted = true;
                }
            } else if (score == 21) {
                stand();
            }
        } else if (score >= 21) {
            stand();
            if (score > 21) {
                busted = true;
            }
        }
        return busted;
    }

    private boolean hasAce() {
        for (Card card : cards) {
            if (card.getName().contains("Ace")) {
                return true;
            }
        }
        return false;
    }

    public void stand() {
        standing = true;
    }

    public boolean hasBlackJack() {
        return cards.size() == 2 && score == 21;
    }

    public Integer determineFinalScore() {
        if (!hasAce() || hasBlackJack()) {
            return score;
        }

        Integer finalScore = score;

        for (int i = 0; i < aceCount; i++) {
            if (finalScore > BLACK_JACK) {
                finalScore -= 10;
            }
        }

        return finalScore;
    }

    public List<Card> doubleDown(final Card card) {
        if (cards.size() != 2) {
            throw new RuntimeException("Cannot double down. Card count is " + cards.size());
        }
        bet += bet;
        stand();
        hit(card);

        return cards;
    }

}
