
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yvonnehayes on 1/18/18.
 */

public class Card {
    public enum Suit {  // enums because we have a fixed set of constants
        HEARTS,
        SPADES,
        CLUBS,
        DIAMONDS
    }

    public enum Rank {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING
    }

    // putting Rank into an Array as well for easier use in Rummy.java
    public static final List<Rank> rankOrder = new ArrayList<>(Rank.values());

    // instantiating suit and rank
    private Suit suit;
    private Rank rank;

    // creating Card to have both a suit and a rank
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // getters & setters

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
