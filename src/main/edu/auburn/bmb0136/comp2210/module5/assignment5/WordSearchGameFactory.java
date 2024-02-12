package edu.auburn.bmb0136.comp2210.module5.assignment5;

/**
 * Provides a factory method for creating word search games.
 */
public final class WordSearchGameFactory {

    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame() {
        return new Game();
    }

}
