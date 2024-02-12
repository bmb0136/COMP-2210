package edu.auburn.bmb0136.comp2210.module5.assignment5;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static edu.auburn.bmb0136.comp2210.module5.assignment5.A5TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class StolenVocareumTests {

    @Test
    public void testGetAllScorableWords_3() {
        WordSearchGame sut = createWithLexicon("small.txt");

        sut.setBoard(new String[]{"A"});
        SortedSet<String> words = sut.getAllScorableWords(3);
        assertEquals(0, words.size(), "wrong # of scorable words returned");

        sut.setBoard(new String[]{
            "H", "E", "B",
            "E", "Z", "K",
            "T", "S", "T"
        });
        words = sut.getAllScorableWords(3);
    }

    @Test
    public void testGetAllScorableWords_4() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{"AA"});

        SortedSet<String> words = sut.getAllScorableWords(2);

        assertEquals(1, words.size(), "wrong # of scorable words returned");
        assertEquals("AA", words.first());
    }

    @Test
    public void testGetAllScorableWords_5() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(BIG_BOARD);

        SortedSet<String> words = sut.getAllScorableWords(10);

        assertNotEquals(0, words.size(), "wrong # of scorable words returned");
    }

    @Test
    public void testGetAllScorableWords_6() {
        WordSearchGame sut = createWithLexicon("cat.txt");
        sut.setBoard(new String[]{"C", "A", "X", "T"});

        SortedSet<String> words = sut.getAllScorableWords(3);

        assertEquals(1, words.size(), "wrong # of scorable words returned");
    }

    @Test
    public void testIsOnBoard_1() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{"C", "A", "X", "T"});

        List<Integer> res = sut.isOnBoard("COG");

        assertTrue(res.isEmpty(), "Expected empty list, got [" + res.stream()
            .map(x -> Integer.toString(x))
            .collect(Collectors.joining(", ")) + "]");
    }

    @Test
    public void testIsOnBoard_2() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{"C", "A", "X", "T"});

        List<Integer> res = sut.isOnBoard("CAT");

        assertIterableEquals(Arrays.asList(0, 1, 3), res, "Expected empty list, got [" + res.stream()
            .map(x -> Integer.toString(x))
            .collect(Collectors.joining(", ")) + "]");
    }

    @Test
    public void scorable_areOnBoard() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(BIG_BOARD);
        SortedSet<String> words = sut.getAllScorableWords(2);

        words.forEach(w -> assertFalse(sut.isOnBoard(w).isEmpty(), w + " is scorable but not on board"));
    }

    @Test
    public void scorable_multi() {
        WordSearchGame sut = createWithLexicon("medium.txt");

        sut.setBoard(new String[]{"TIGER"});
        SortedSet<String> words = sut.getAllScorableWords(7);
        assertIterableEquals(Collections.emptyList(), words, "No words expected");

        sut.setBoard(new String[]{"TIGER"});
        words = sut.getAllScorableWords(3);
        assertIterableEquals(Collections.singletonList("TIGER"), words, "Expected [TIGER]");

        sut.setBoard(new String[]{"CAT", "X", "FISH", "XXXX"});
        words = sut.getAllScorableWords(3);
        assertIterableEquals(Arrays.asList("CAT", "CATFISH", "FISH"), words, "Expected [CAT, CATFISH, FISH]");
    }


    @Test
    public void isOnBoard_multi() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{"TIGER"});

        List<Integer> path = sut.isOnBoard("TIGER");

        assertIterableEquals(Collections.singletonList(0), path);
    }

    @Test
    public void scorable() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{
            "U", "G", "I", "A",
            "O", "H", "S", "S",
            "T", "U", "E", "T",
            "Y", "N", "T", "W",
        });

        SortedSet<String> words = sut.getAllScorableWords(3);
    }

    @Test
    public void scorable_noBacktracking() {
        WordSearchGame sut = createWithLexicon("medium.txt");
        sut.setBoard(new String[]{
            "B", "E",
            "N", "T"
        });

        SortedSet<String> words = sut.getAllScorableWords(3);
        for (String w : words) {
            String board = sut.getBoard();
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                String s = board.replace(c, ' ');
                assertNotEquals(s, board, "Backtracked on char " + c + " in word " + w);
                board = s;
            }
        }
    }

}
