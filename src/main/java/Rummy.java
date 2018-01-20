
import java.util.*;

/**
 * Created by yvonnehayes on 1/18/18.
 */

public class Rummy implements Game {

    @Override
    public void takeTurn(Player player) {
        // TODO
    }

    // check if winning conditions have been met
    @Override
    public boolean hasWon(Player player) {
        Set<Card> winningHand = new HashSet<>(); // winningSets and winningRuns will be added into this
        List<Card> cards = new LinkedList<>(player.getHand());
        cards.sort(Comparator.comparing(Card::getRank).reversed()); // reversing sort to have lowest rank first
        addSets(cards, winningHand); // adding only the winningSets for now (both those made up of 3 as well as 4 cards)
        if (winningHand.size() == 4) {
            Iterator<Card> winningHandIterator = winningHand.iterator(); // if it's a set of 4 go over the cards and remove the 3 remaining cards
            for (int i = 0; i < 3; i++) {
                cards.remove(winningHandIterator.next());
            }
            addRuns(cards, winningHand); // adding the winningRuns to the winningSets
        } else {
            cards.removeAll(winningHand); // remove the cards
            addRuns(cards, winningHand); // add the winningRuns to the winningHand
        }
        return winningHand.size() == 7; // winningHand can not be made up of more than 7 cards
    }

    // check if player has at least 3 cards of the same rank if so, add them to the winningHand
    private void addSets(Collection<Card> cards, Set<Card> winningHand) {
        if (cards != null && cards.size() > 0) {      // defensive programming. Just checking we have cards
            Set<Card> winningSet = new HashSet<>();    // winning combinations will be put in here
            Set<Card> attemptedSet = new HashSet<>();   // attempts at combinations go in here
            Iterator<Card> cardIterator = cards.iterator();  // iterating over cards in hand
            Card lastCardSeen = cardIterator.next();
            while (cardIterator.hasNext()) {                   // comparing current card to previous card
                Card currentCard = cardIterator.next();
                if (currentCard.getRank() == lastCardSeen.getRank()) {   // if the current card and previous card have same rank put them in attemptedSet
                    attemptedSet.add(lastCardSeen);
                    attemptedSet.add(currentCard);
                } else {
                    attemptedSet = new HashSet<>();
                }
                if (attemptedSet.size() >= 3) {     // if number of cards in attemptedSet are at least 3, put them into winningHand
                    winningSet.addAll(attemptedSet);
                }
            }
            winningHand.addAll(winningSet); // put cards from winningSet into winningHand
        }
    }

    // check if player has a run of at least 3 cards in the same suit
    private void addRuns(Collection<Card> cards, Set<Card> winningHand) {
        if (cards != null && cards.size() > 0) {     // defensive programming. Making sure we have cards.
            Set<Card> winningRun = new HashSet<>(); // winning combinations go here
            Set<Card> attemptedRun = new HashSet<>();  // attempts at winning combinations go here
            Iterator<Card> cardIterator = cards.iterator();  // iterating over cards in hand
            Card lastCardSeen = cardIterator.next();
            while (cardIterator.hasNext()) {            //comparing current card to previous card
                Card currentCard = cardIterator.next();
                if (Card.rankOrder.indexOf(currentCard.getRank()) == Card.rankOrder.indexOf(lastCardSeen.getRank()) + 1 &&     // if current card has a rank of 1 higher than previous card
                        currentCard.getSuit() == lastCardSeen.getSuit()) {                                                     // and they have a matching suit
                    attemptedRun.add(lastCardSeen);    // add both to attempted Run
                    attemptedRun.add(currentCard);
                } else {
                    attemptedRun = new HashSet<>();
                }
                if (attemptedRun.size() >= 3) {       // if number of cards in attemptedRun are at least 3 put them into winningRun
                    winningRun.addAll(attemptedRun);
                }
            }
            winningHand.addAll(winningRun);      // put cards from winningRun into winningHand
        }
    }
}
