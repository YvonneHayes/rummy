package yvonne.rummy;

/**
 * Created by yvonnehayes on 1/17/18.
 */

public interface Game {

    boolean hasWon(Player player);

    void takeTurn(Player player);
}

// future proofing ;) Just in case I wanted to create other games apart from yvonne.rummy.Rummy at a future date