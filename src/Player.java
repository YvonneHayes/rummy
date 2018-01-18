
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by yvonnehayes on 1/18/18.
 */

public class Player {

    // creating a hand
    private final Collection<Card> hand = new LinkedList<>(); // Collection because it does not allow duplicates; LinkedList better than ArrayList for iterating over items and inserting                                                                                 and removing them

    // need to be able to add a card to a player (when draws a new card)
    public void add(Card card) {
        hand.add(card);
    }

    // need to be able to remove a card from a player (when discards a card)
    public void remove(Card card) {
        if (has(card)) {  // defensive programming. Checking if card exists
            hand.remove(card); //this simply removes card without adding it to discard pile (discardCard method does both)
        }
    }

    // need to check if player has a card / the hand contains a card
    public boolean has(Card card) {
        return hand.contains(card);
    }

    // need to be able to deal a hand
    public Collection<Card> getHand() {
        return hand;
    }
}
