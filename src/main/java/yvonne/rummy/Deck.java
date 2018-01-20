package yvonne.rummy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

/**
 * Created by yvonnehayes on 1/18/18.
 */

public class Deck {

    // jack - made final
    // Good call on making this a singleton!
    private final static Deck ourInstance = new Deck();  // Singleton because only want one instance of this

    public static Deck getInstance() {
        return ourInstance;
    }

    // keep a copy of the initial deck to allow for easy refresh
    private final ArrayList<Card> base = new ArrayList<>(52);

    // the deck of cards
    private final Stack<Card> deck = new Stack<>(); // making those stacks for simplicity. That way the top card can be "popped" off
    // the discard pile
    private final Stack<Card> discard = new Stack<>(); // especially handy here since the discard pile is now 'tracked'. Only the last card added to the pile can be taken

    // creating the initial deck of cards (will automatically be 52 since there's only once instance of every card (rank/suit combo)
    private Deck() {
        base.addAll(buildDeck());
        deck.addAll(base);
    }

    public Deck refresh() {
        deck.clear();
        discard.clear();
        deck.addAll(base);
        return this; // make fluent
    }

    public Collection<Card> cards() {
        return Collections.unmodifiableCollection(deck);
    }

    // drawing a card from the deck or discard pile
    public Deck drawCard(Player player, boolean fromDeck) {  // boolean to differentiate where to draw from
        if (fromDeck) {
            if (!deck.empty()) {          // defensive programming. Just making sure deck isn't empty
                player.add(deck.pop());  // take top card from deck
            }
        } else {
            if (!discard.empty()) {             // again just making sure discard pile isn't empty
                player.add(discard.pop());      // take the top (last discarded) card from discard pile
            }
        }
        return this; // make fluent
    }

    // card gets removed from player and added to discard pile
    public Deck discardCard(Player player, Card card) {
        if (player.has(card)) {
            discard.add(card);
            player.remove(card);
        }
        return this; // make fluent
    }

    // shuffle the deck
    public Deck shuffle() {
        Collections.shuffle(deck);
        return this; // make fluent
    }

    // deal cards to players
    public Deck deal(Collection<Player> players, int handSize) {  // gives option to  choose how many cards are dealt
        // java 8 streams syntax. A more functional style
        IntStream.range(0, handSize)
                .forEach(i -> players.forEach(player -> drawCard(player, true)));

//        for (int i = 0; i < handSize; i++) {
//            for (Player player : players) {
//                drawCard(player, true); // drawing from deck not discard pile until handSize is reached
//            }
//        }
        return this; //make fluent
    }

    private List<Card> buildDeck() {

        // java 1.8 - streams style. A more functional approach
        return stream(Card.Suit.values())
                .flatMap(suit -> stream(Card.Rank.values()).map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());



//        for (Card.Suit suit : Card.Suit.values()) {      // for-each loop to go through all the suits
//            for (Card.Rank rank : Card.Rank.values()) {  //  then go through all the ranks
//                Card card = new Card(suit, rank);        // make a card with suit and rank
//                base.add(card);                         // put card into deck
//            }
//        }
    }
}
