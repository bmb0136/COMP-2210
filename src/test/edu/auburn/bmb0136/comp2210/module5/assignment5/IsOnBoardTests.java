package edu.auburn.bmb0136.comp2210.module5.assignment5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.auburn.bmb0136.comp2210.module5.assignment5.A5TestUtils.createWithLexicon;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public final class IsOnBoardTests {

    private static void testFork(String word) {
        WordSearchGame sut = createWithLexicon("medium.txt");
        String[] board = new String[word.length() * word.length()];
        Arrays.fill(board, "X");
        for (int i = 0; i < word.length(); i++) {
            board[i] = String.valueOf(word.charAt(i));
            board[i * word.length()] = String.valueOf(word.charAt(i));
        }
        sut.setBoard(board);

        List<Integer> res = sut.isOnBoard(word);
        String path = String.format("[%s]", res.stream()
            .map(x -> x == null ? "null" : Integer.toString(x))
            .collect(Collectors.joining(", ")));

        assertIterableEquals(Stream.iterate(0, x -> x + 1)
            .limit(word.length())
            .collect(Collectors.toList()), res, path);
    }

    @Test
    public void forking_oneMoreLetter() {
        testFork("CAT");
    }

    @Test
    public void forking_nextIsEnd() {
        testFork("BE");
    }

    @Test
    public void forking_doubleLetters() {
        testFork("COOK");
    }
}
