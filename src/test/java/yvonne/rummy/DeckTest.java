package yvonne.rummy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

    @Test
    public void has52() {
        assertEquals(52, Deck.getInstance().cards().size());
    }

}