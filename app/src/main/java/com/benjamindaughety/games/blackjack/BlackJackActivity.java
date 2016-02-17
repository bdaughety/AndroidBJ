package com.benjamindaughety.games.blackjack;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benjamindaughety.games.blackjack.model.Card;
import com.benjamindaughety.games.blackjack.model.DeckBuilder;
import com.benjamindaughety.games.blackjack.model.Hand;
import com.benjamindaughety.games.blackjack.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamindaughety on 2/9/16.
 */
public class BlackJackActivity extends AppCompatActivity {

    private static final Integer NEXT_CARD_SPACING = 32;
    private static final Integer TWENTY_ONE_WITH_ACE = 31;
    private static final Integer BLACK_JACK = 21;
    private static final Integer MINIMUM_DECK_SIZE = 26;
    private static final Integer MINIMUM_BET = 10;
    private static final String PUSH = "Push";
    private static final String WIN = "Win";
    private static final String LOSE = "Lose";
    private static final String EMPTY_TEXT = "";

    private ImageView playerFirstCard;
    private ImageView playerSecondCard;
    private ImageView dealerFirstCard;
    private ImageView dealerSecondCard;
    private Button dealButton;
    private Button hitButton;
    private Button standButton;
    private Button doubleDownButton;
    private Button splitButton;
    private TextView balanceTextField;
    private TextView dealerScoreLabel;
    private TextView dealerOutcomeLabel;
    private TextView playerScoreLabel;
    private TextView playerOutcomeLabel;
    private EditText betTextField;
    private RelativeLayout gameTableView;

    private List<Card> deck = new ArrayList<>();
    private Integer playerNextCardPosition = 0;
    private Integer dealerNextCardPosition = 0;
    private Player player = new Player();
    private Hand dealerHand = new Hand();
    private Integer currentPlayerHandIndex = 0;
    private List<ImageView> allCardImages = new ArrayList<>();
    private ImageView backOfCardImage;
    private DeckBuilder deckBuilder;
    private Integer deckIndex = -1;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blackjack);

        gameTableView = (RelativeLayout) findViewById(R.id.gameTableView);

        playerFirstCard = (ImageView) findViewById(R.id.playerFirstCardImage);
        dealerFirstCard = (ImageView) findViewById(R.id.dealerFirstCardImage);
        playerSecondCard = (ImageView) findViewById(R.id.playerSecondCardImage);
        dealerSecondCard = (ImageView) findViewById(R.id.dealerSecondCardImage);

        dealButton = (Button) findViewById(R.id.dealButton);
        hitButton = (Button) findViewById(R.id.hitButton);
        standButton = (Button) findViewById(R.id.standButton);
        doubleDownButton = (Button) findViewById(R.id.doubleDownButton);
        splitButton = (Button) findViewById(R.id.splitButton);

        dealerScoreLabel = (TextView) findViewById(R.id.dealerScoreLabel);
        dealerOutcomeLabel = (TextView) findViewById(R.id.dealerOutcomeLabel);
        playerScoreLabel = (TextView) findViewById(R.id.playerScoreLabel);
        playerOutcomeLabel = (TextView) findViewById(R.id.playerOutcomeLabel);
        balanceTextField = (TextView) findViewById(R.id.balanceTextField);
        betTextField = (EditText) findViewById(R.id.betTextField);

        dealButton.setEnabled(isBetValid());
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        doubleDownButton.setEnabled(false);
        splitButton.setEnabled(false);
        betTextField.setEnabled(false);
        balanceTextField.setEnabled(false);

        backOfCardImage = new ImageView(this.getApplicationContext());
        backOfCardImage.setImageResource(R.drawable.back_of_card);

        deckBuilder = new DeckBuilder(null); // default number of decks = 7
        deck = deckBuilder.getGameDeck();

        if (player.getBank() <= MINIMUM_BET) {
            player.setBank(1000.0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dealCards(View view) {
        clearCardImages();
        player.getHands().clear();
        dealerHand = new Hand();

        dealButton.setEnabled(false);
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        doubleDownButton.setEnabled(true);
        splitButton.setEnabled(true);

        currentPlayerHandIndex = 0;
        playerNextCardPosition = 0;
        dealerOutcomeLabel.setText(EMPTY_TEXT);
        playerOutcomeLabel.setText(EMPTY_TEXT);

        if (deck.size() < MINIMUM_DECK_SIZE) {
            deckBuilder = new DeckBuilder(null);
            deck = deckBuilder.getGameDeck();
        }

        Hand playerHand = new Hand();

        playerHand.setBet(Double.valueOf(betTextField.getText().toString()));
        player.setBank(player.getBank() - playerHand.getBet());
        betTextField.setEnabled(false);

        Card currentCard = popDeck();
        playerHand.hit(currentCard);
        playerFirstCard.setImageResource(currentCard.getDrawableId());
        playerScoreLabel.setText(playerHand.determineFinalScore().toString());

        currentCard = popDeck();
        dealerHand.hit(currentCard);
        dealerFirstCard.setImageResource(currentCard.getDrawableId());
        dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());

        currentCard = popDeck();
        playerHand.hit(currentCard);
        playerSecondCard.setImageResource(currentCard.getDrawableId());
        playerScoreLabel.setText(playerHand.determineFinalScore().toString());

        currentCard = popDeck();
        dealerHand.hit(currentCard);
        dealerSecondCard.setImageResource(R.drawable.back_of_card);

        player.getHands().add(playerHand);
        doubleDownButton.setEnabled(isDoubleDownEnabled());

        if (playerHand.hasBlackJack()) {
            dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());
            dealerSecondCard.setImageResource(dealerHand.getCards().get(1).getDrawableId());

            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            dealButton.setEnabled(true);
            doubleDownButton.setEnabled(false);
            splitButton.setEnabled(false);

            // TODO: add insurance
            if (dealerHand.hasBlackJack()) {
                dealerOutcomeLabel.setText(PUSH);
                playerOutcomeLabel.setText(PUSH);
                player.setBank(player.getBank() + playerHand.getBet());
            } else {
                dealerOutcomeLabel.setText(LOSE);
                playerOutcomeLabel.setText(WIN);
                player.setBank(player.getBank() + Math.round(playerHand.getBet() * 2.5));
            }
            betTextField.setEnabled(true);
        } else if (dealerHand.hasBlackJack()) {
            // TODO: add insurance
            dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());
            dealerSecondCard.setImageResource(dealerHand.getCards().get(1).getDrawableId());

            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            dealButton.setEnabled(true);
            doubleDownButton.setEnabled(false);
            splitButton.setEnabled(false);

            dealerOutcomeLabel.setText(WIN);
            playerOutcomeLabel.setText(LOSE);
            betTextField.setEnabled(true);
        }
        balanceTextField.setText("$" + player.getBank());
    }

    public void hitPlayer(View view) {
        updatePlayerNextCardPosition();
        ;
        hitPlayerAndUpdateUI(false);
    }

    public void standPlayer(View view) {
        player.getHands().get(currentPlayerHandIndex).stand();
        if (player.getHands().size() > 1) {
            // TODO: handle split
            currentPlayerHandIndex += 1;
        } else {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            dealButton.setEnabled(true);
            splitButton.setEnabled(false);

            processDealerTurn();
        }
    }

    public void doubleDown(View view) {
        updatePlayerNextCardPosition();
        hitPlayerAndUpdateUI(true);
        betTextField.setText(player.getHands().get(currentPlayerHandIndex).getBet().toString());
    }

    public void split(View view) {

    }

    private Card popDeck() {
        deckIndex++;
        Card nextCard = deck.get(deckIndex);
        deck.remove(deckIndex.intValue());
        return nextCard;
    }

    private void clearCardImages() {
        if (allCardImages == null || allCardImages.isEmpty()) {
            return;
        }

        allCardImages.add(playerFirstCard);
        allCardImages.add(dealerFirstCard);
        allCardImages.add(playerSecondCard);
        allCardImages.add(dealerSecondCard);

        for (ImageView imageView : allCardImages) {
            imageView.setVisibility(View.GONE);
        }
        allCardImages.clear();
    }

    private boolean isDoubleDownEnabled() {
        Double bet = player.getHands().get(currentPlayerHandIndex).getBet();
        if (bet != null && bet <= player.getBank()) {
            return true;
        }
        return false;
    }

    private void updatePlayerNextCardPosition() {
        playerNextCardPosition += playerNextCardPosition == 0 ? NEXT_CARD_SPACING + NEXT_CARD_SPACING : NEXT_CARD_SPACING;
    }

    private void hitPlayerAndUpdateUI(final boolean doubleDown) {
        Card hitCard = popDeck();
        ImageView hitCardImageView = new ImageView(getApplicationContext());
        hitCardImageView.setImageResource(hitCard.getDrawableId());
        Hand playerHand = player.getHands().get(currentPlayerHandIndex);

        if (doubleDown) {
            // TODO: change doubleDown() to be on the Player object that accepts two parameters: card, handIndex
            player.setBank(player.getBank() - playerHand.getBet());
            balanceTextField.setText("$" + (player.getBank()));
            playerHand.doubleDown(hitCard);
            betTextField.setText(playerHand.getBet().toString());
        } else {
            playerHand.hit(hitCard);
        }

        hitCardImageView.setImageResource(hitCard.getDrawableId());
        ViewGroup.LayoutParams params;
        int width = dealerFirstCard.getLayoutParams().width;
        int height = dealerFirstCard.getLayoutParams().height;
        params = dealerFirstCard.getLayoutParams();//new RelativeLayout.LayoutParams(width, height);
        // TODO: 2/14/16 set card position correctly

        hitCardImageView.setLayoutParams(params);
        hitCardImageView.setMaxWidth(dealerFirstCard.getMaxWidth());
        hitCardImageView.setMaxHeight(dealerFirstCard.getMaxHeight());
        hitCardImageView.setX(playerSecondCard.getX());
        hitCardImageView.setY(playerSecondCard.getY());
        allCardImages.add(hitCardImageView);

        gameTableView.addView(hitCardImageView);

        playerScoreLabel.setText(playerHand.determineFinalScore().toString());

        if (playerHand.getBusted() || playerHand.getStanding()) {
            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            dealButton.setEnabled(true);
            doubleDownButton.setEnabled(false);
            splitButton.setEnabled(false);

            dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());
            dealerSecondCard.setImageResource(dealerHand.getCards().get(1).getDrawableId());

            if (playerHand.getBusted()) {
                dealerOutcomeLabel.setText(WIN);
                playerOutcomeLabel.setText(LOSE);
            } else if (playerHand.determineFinalScore() == BLACK_JACK || doubleDown) {
                processDealerTurn();
            }
            betTextField.setEnabled(true);
        }
    }

    private boolean isBetValid() {
        return (Double.valueOf(betTextField.getText().toString()) >= MINIMUM_BET);
    }

    private void processDealerTurn() {
        dealerSecondCard.setImageResource(dealerHand.getCards().get(1).getDrawableId());
        dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());
        dealerNextCardPosition = 0;

        while (!isDealerStanding()) {
            dealerNextCardPosition += dealerNextCardPosition == 0 ?
                    NEXT_CARD_SPACING + NEXT_CARD_SPACING :
                    NEXT_CARD_SPACING;
            Card hitCard = popDeck();
            ImageView hitCardImageView = new ImageView(getApplicationContext());
            hitCardImageView.setImageResource(hitCard.getDrawableId());

            dealerHand.hit(hitCard);
            int width = dealerFirstCard.getLayoutParams().width;
            int height = dealerFirstCard.getLayoutParams().height;
            ViewGroup.LayoutParams params = dealerFirstCard.getLayoutParams();
            // TODO: 2/14/16 set card position correctly
            /**
             * <ImageView
             android:id="@+id/playerSecondCardImage"
             android:layout_width="57dp"
             android:layout_height="80dp"
             android:layout_alignLeft="@id/playerFirstCardImage"
             android:layout_alignTop="@+id/playerFirstCardImage"
             android:layout_gravity="bottom"
             android:layout_marginLeft="28.85dp"
             android:background="#00ffffff"
             android:contentDescription="Player second card"
             android:minHeight="80dp"
             android:minWidth="57dp" />
             */

            hitCardImageView.setLayoutParams(params);
            hitCardImageView.setMaxWidth(57 * 3);
            hitCardImageView.setMaxHeight(80 * 3);
            hitCardImageView.setX(dealerSecondCard.getX() + 28);
            hitCardImageView.setY(dealerSecondCard.getY());
            allCardImages.add(hitCardImageView);

            gameTableView.addView(hitCardImageView);

            dealerScoreLabel.setText(dealerHand.determineFinalScore().toString());
        }
        // TODO: add label for multiple player hands
        updateOutcomeLabelTextsPlayerNotBusted(player.getHands().get(currentPlayerHandIndex));

        // TODO: handle bank is empty
        if (player.getBank() <= MINIMUM_BET) {
            player.setBank(1000.0);
        }
        balanceTextField.setText("$" + player.getBank().toString());
    }

    private void updateOutcomeLabelTextsPlayerNotBusted(final Hand playerHand) {
        if (dealerHand.getBusted()) {
            dealerOutcomeLabel.setText(LOSE);
            playerOutcomeLabel.setText(WIN);
            player.setBank(player.getBank() + playerHand.getBet());
        } else {
            Integer dealerFinalScore = dealerHand.determineFinalScore();
            Integer playerFinalScore = playerHand.determineFinalScore();

            if (dealerFinalScore > playerFinalScore) {
                dealerOutcomeLabel.setText(WIN);
                playerOutcomeLabel.setText(LOSE);
            } else if (dealerFinalScore < playerFinalScore) {
                dealerOutcomeLabel.setText(LOSE);
                playerOutcomeLabel.setText(WIN);
                player.setBank(player.getBank() + Math.round(playerHand.getBet() * 2));
            } else {
                dealerOutcomeLabel.setText(PUSH);
                playerOutcomeLabel.setText(PUSH);
                player.setBank(player.getBank() + playerHand.getBet());
            }
        }
        betTextField.setEnabled(true);
    }

    private boolean isDealerStanding() {
        Integer dealerScore = dealerHand.determineFinalScore();
        if (dealerScore >= 17) {
            dealerHand.stand();
            return true;
        }
        return false;
    }
}
