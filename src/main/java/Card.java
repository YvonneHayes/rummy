import java.util.Arrays;
import java.util.Collections;
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
    // ---- Jack
    // Used Arrays.asList
    // Made an unmodifiable list so that values cannot later be added
    // Made private
    //
    // see - https://en.wikipedia.org/wiki/Flyweight_pattern
    public static final List<Rank> rankOrder = Collections.unmodifiableList(Arrays.asList(Rank.values()));

    // instantiating suit and rank
    // Jack - made final as there isn't a reason to ever mutate
    private final Suit suit;
    private final Rank rank;

    // creating Card to have both a suit and a rank
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // getters
    // Jack - removed the setters. This class is really a flyweight, and doesn't need to be modified beyond
    // initial object creation

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
