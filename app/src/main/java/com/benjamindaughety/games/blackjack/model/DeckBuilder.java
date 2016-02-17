package com.benjamindaughety.games.blackjack.model;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by benjamindaughety on 2/13/16.
 */
public class DeckBuilder {

    private static final Integer DEFAULT_NUMBER_OF_DECKS = 7;
    private static final String SPADES = "Spades";
    private static final String CLUBS = "Clubs";
    private static final String DIAMONDS = "Diamonds";
    private static final String HEARTS = "Hearts";
    private List<Card> singleDeck;
    private List<Card> gameDeck;

    public DeckBuilder(final Integer numberOfDecks) {
        if (numberOfDecks != null) {
            gameDeck = createAndShuffleDeck(numberOfDecks);
        } else {
            gameDeck = createAndShuffleDeck(DEFAULT_NUMBER_OF_DECKS);
        }
    }

    public DeckBuilder(final Integer numberOfDecks, final FileNameMap map) {
        if (numberOfDecks != null) {
            gameDeck = createAndShuffleDeck(numberOfDecks);
        } else {
            gameDeck = createAndShuffleDeck(DEFAULT_NUMBER_OF_DECKS);
        }
    }

    public List<Card> getGameDeck() {
        return gameDeck;
    }

    private List<Card> createAndShuffleDeck(final Integer numberOfDecks) {
        int finalNumber = DEFAULT_NUMBER_OF_DECKS;

        if (numberOfDecks != null) {
            finalNumber = numberOfDecks;
        }

        List<Card> deck = new ArrayList<>();
        List<Card> tempDeck = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            if (i < 13) {
                tempDeck.add(new Card(SPADES, i, getScoreByIndex(i)));
            } else if (i < 26) {
                tempDeck.add(new Card(CLUBS, i, getScoreByIndex(i - 13)));
            } else if (i < 39) {
                tempDeck.add(new Card(DIAMONDS, i, getScoreByIndex(i - 26)));
            } else {
                tempDeck.add(new Card(HEARTS, i, getScoreByIndex(i - 39)));
            }
        }

        singleDeck = tempDeck;

        for (int i = 0; i < finalNumber; i++) {
            for (Card card : tempDeck) {
                deck.add(card);
            }
        }

        Collections.shuffle(deck);
        return deck;
    }

    private Integer getScoreByIndex(final Integer index) {
        Integer score;
        switch (index) {
            case 0:
                score = 11;
                break;
            case 10:
            case 11:
            case 12:
                score = 10;
                break;
            default:
                score = index + 1;
        }
        return score;
    }

    public Card findCardByPosition(final Integer position) {
        if (singleDeck.size() > 0) {
            for (Card card : singleDeck) {
                if (card.getPosition().equals(position)) {
                    return card;
                }
            }
        }
        return null;
    }

    public Card findCardByImageName(final String imageName) {
        if (singleDeck.size() > 0) {
            for (Card card : singleDeck) {
                if (card.getName().equals(imageName)) {
                    return card;
                }
            }
        }
        return null;
    }

}
