package com.benjamindaughety.games.blackjack.model;

import com.benjamindaughety.games.blackjack.R;

/**
 * Created by benjamindaughety on 2/13/16.
 */
public enum CardEnum {

    ACE_OF_SPADES("Ace of Spades", R.drawable.ace_of_clubs),
    TWO_OF_SPADES("Two of Spades", R.drawable.two_of_spades),
    THREE_OF_SPADES("Three of Spades", R.drawable.three_of_spades),
    FOUR_OF_SPADES("Four of Spades", R.drawable.four_of_spades),
    FIVE_OF_SPADES("Five of Spades", R.drawable.five_of_spades),
    SIX_OF_SPADES("Six of Spades", R.drawable.six_of_spades),
    SEVEN_OF_SPADES("Seven of Spades", R.drawable.seven_of_spades),
    EIGHT_OF_SPADES("Eight of Spades", R.drawable.eight_of_spades),
    NINE_OF_SPADES("Nine of Spades", R.drawable.nine_of_spades),
    TEN_OF_SPADES("Ten of Spades", R.drawable.ten_of_spades),
    JACK_OF_SPADES("Jack of Spades", R.drawable.jack_of_spades),
    QUEEN_OF_SPADES("Queen of Spades", R.drawable.queen_of_spades),
    KING_OF_SPADES("King of Spades", R.drawable.king_of_spades),
    ACE_OF_CLUBS("Ace of Clubs", R.drawable.ace_of_clubs),
    TWO_OF_CLUBS("Two of Clubs", R.drawable.two_of_clubs),
    THREE_OF_CLUBS("Three of Clubs", R.drawable.three_of_clubs),
    FOUR_OF_CLUBS("Four of Clubs", R.drawable.four_of_clubs),
    FIVE_OF_CLUBS("Five of Clubs", R.drawable.five_of_clubs),
    SIX_OF_CLUBS("Six of Clubs", R.drawable.six_of_clubs),
    SEVEN_OF_CLUBS("Seven of Clubs", R.drawable.seven_of_clubs),
    EIGHT_OF_CLUBS("Eight of Clubs", R.drawable.eight_of_clubs),
    NINE_OF_CLUBS("Nine of Clubs", R.drawable.nine_of_clubs),
    TEN_OF_CLUBS("Ten of Clubs", R.drawable.ten_of_clubs),
    JACK_OF_CLUBS("Jack of Clubs", R.drawable.jack_of_clubs),
    QUEEN_OF_CLUBS("Queen of Clubs", R.drawable.queen_of_clubs),
    KING_OF_CLUBS("King of Clubs", R.drawable.king_of_clubs),
    ACE_OF_DIAMONDS("Ace of Diamonds", R.drawable.ace_of_diamonds),
    TWO_OF_DIAMONDS("Two of Diamonds", R.drawable.two_of_diamonds),
    THREE_OF_DIAMONDS("Three of Diamonds", R.drawable.three_of_diamonds),
    FOUR_OF_DIAMONDS("Four of Diamonds", R.drawable.four_of_diamonds),
    FIVE_OF_DIAMONDS("Five of Diamonds", R.drawable.five_of_diamonds),
    SIX_OF_DIAMONDS("Six of Diamonds", R.drawable.six_of_diamonds),
    SEVEN_OF_DIAMONDS("Seven of Diamonds", R.drawable.seven_of_diamonds),
    EIGHT_OF_DIAMONDS("Eight of Diamonds", R.drawable.eight_of_diamonds),
    NINE_OF_DIAMONDS("Nine of Diamonds", R.drawable.nine_of_diamonds),
    TEN_OF_DIAMONDS("Ten of Diamonds", R.drawable.ten_of_diamonds),
    JACK_OF_DIAMONDS("Jack of Diamonds", R.drawable.jack_of_diamonds),
    QUEEN_OF_DIAMONDS("Queen of Diamonds", R.drawable.queen_of_diamonds),
    KING_OF_DIAMONDS("King of Diamonds", R.drawable.king_of_diamonds),
    ACE_OF_HEARTS("Ace of Hearts", R.drawable.ace_of_hearts),
    TWO_OF_HEARTS("Two of Hearts", R.drawable.two_of_hearts),
    THREE_OF_HEARTS("Three of Hearts", R.drawable.three_of_hearts),
    FOUR_OF_HEARTS("Four of Hearts", R.drawable.four_of_hearts),
    FIVE_OF_HEARTS("Five of Hearts", R.drawable.five_of_hearts),
    SIX_OF_HEARTS("Six of Hearts", R.drawable.six_of_hearts),
    SEVEN_OF_HEARTS("Seven of Hearts", R.drawable.seven_of_hearts),
    EIGHT_OF_HEARTS("Eight of Hearts", R.drawable.eight_of_hearts),
    NINE_OF_HEARTS("Nine of Hearts", R.drawable.nine_of_hearts),
    TEN_OF_HEARTS("Ten of Hearts", R.drawable.ten_of_hearts),
    JACK_OF_HEARTS("Jack of Hearts", R.drawable.jack_of_hearts),
    QUEEN_OF_HEARTS("Queen of Hearts", R.drawable.queen_of_hearts),
    KING_OF_HEARTS("King of Hearts", R.drawable.king_of_hearts);

    private String label;
    private int drawableId;

    CardEnum(final String label, final int drawableId) {
        this.label = label;
        this.drawableId = drawableId;
    }

    public String label() {
        return this.label;
    }

    public int drawableId() {
        return this.drawableId;
    }

}
