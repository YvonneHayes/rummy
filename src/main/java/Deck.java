
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by yvonnehayes on 1/18/18.
 */

public class Deck {

    private static Deck ourInstance = new Deck();  // Singleton because only want one instance of this

    public static Deck getInstance() {
        return ourInstance;
    }

    // the deck of cards
    private final Stack<Card> deck = new Stack<>(); // making those stacks for simplicity. That way the top card can be "popped" off
    // the discard pile
    private final Stack<Card> discard = new Stack<>(); // especially handy here since the discard pile is now 'tracked'. Only the last card added to the pile can be taken

    // creating the initial deck of cards (will automatically be 52 since there's only once instance of every card (rank/suit combo)
    private Deck() {
        for (Card.Suit suit : Card.Suit.values()) {      // for-each loop to go through all the suits
            for (Card.Rank rank : Card.Rank.values()) {  //  then go through all the ranks
                Card card = new Card(suit, rank);        // make a card with suit and rank
                deck.push(card);                         // put card into deck
            }
        }
    }

    // drawing a card from the deck or discard pile
    public void drawCard(Player player, boolean fromDeck) {  // boolean to differentiate where to draw from
        if (fromDeck) {
            if (!deck.empty()) {          // defensive programming. Just making sure deck isn't empty
                player.add(deck.pop());  // take top card from deck
            }
        } else {
            if (!discard.empty()) {             // again just making sure discard pile isn't empty
                player.add(discard.pop());      // take the top (last discarded) card from discard pile
            }
        }
    }

    // card gets removed from player and added to discard pile
    public void discardCard(Player player, Card card) {
        if (player.has(card)) {
            discard.add(card);
            player.remove(card);
        }
    }

    // shuffle the deck
    public void shuffle() {
        Collections.shuffle(deck);
    }

    // deal cards to players
    public void deal(Collection<Player> players, int handSize) {  // gives option to  choose how many cards are dealt
        for (int i = 0; i < handSize; i++) {
            for (Player player : players) {
                drawCard(player, true); // drawing from deck not discard pile until handSize is reached
            }
        }
    }
}
