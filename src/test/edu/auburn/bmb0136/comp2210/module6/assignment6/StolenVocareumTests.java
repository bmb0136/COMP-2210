package edu.auburn.bmb0136.comp2210.module6.assignment6;

import edu.auburn.bmb0136.comp2210.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static edu.auburn.bmb0136.comp2210.module6.assignment6.A6TestUtils.createWithLexicon;
import static org.junit.jupiter.api.Assertions.*;

public final class StolenVocareumTests {

    @Test
    public void invalidWords_notALadder() {
        List<String> list = Arrays.asList("xxxx", "xxxy", "xxxz");
        WordLadderGame game = createWithLexicon("tiny.txt");

        boolean res = game.isWordLadder(list);

        assertFalse(res);
    }

    @Test
    public void sameWord_ladderOfSelf() {
        final String WORD = "aa";
        WordLadderGame game = createWithLexicon("sowpods.txt");

        List<String> res = game.getMinLadder(WORD, WORD);

        assertIterableEquals(Collections.singletonList(WORD), res);
    }

    @Test
    public void isWord_works() {
        WordLadderGame game = createWithLexicon("sowpods.txt");

        assertTrue(game.isWord("aa"), "lower + lower");
        assertTrue(game.isWord("aA"), "lower + upper");
        assertTrue(game.isWord("Aa"), "upper + lower");
        assertTrue(game.isWord("AA"), "upper + upper");
    }

    @Test
    public void neighborsOfCat() {
        final String WORD = "cat";
        WordLadderGame game = createWithLexicon("tiny.txt");

        List<String> res = game.getNeighbors(WORD);

        System.out.println(TestUtils.iterToString(res));
        Collections.sort(res);
        assertIterableEquals(Arrays.asList("bat", "can", "cot"), res);
    }

    @Test
    public void minLadder_works() {
        WordLadderGame game = createWithLexicon("small.txt");

        List<String> res = game.getMinLadder("cat", "dog");

        System.out.println(TestUtils.iterToString(res));
        assertIterableEquals(Arrays.asList("cat", "cot", "dot", "dog"), res);
    }
}
