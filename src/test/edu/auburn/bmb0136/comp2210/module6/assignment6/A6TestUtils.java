package edu.auburn.bmb0136.comp2210.module6.assignment6;

import edu.auburn.bmb0136.comp2210.TempFile;
import edu.auburn.bmb0136.comp2210.TestUtils;
import org.opentest4j.TestAbortedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public final class A6TestUtils {

    public static WordLadderGame createWithLexicon(String lex) {
        WordLadderGame game;
        try (TempFile tmp = TestUtils.loadResource(lex)) {
            long start = System.nanoTime();
            game = new Doublets(Files.newInputStream(Paths.get(tmp.getPath())));
            double time = (System.nanoTime() - start) / 10.e9;
            String caller = Thread.currentThread().getStackTrace()[2].getMethodName();
            System.out.printf("%s > createWithLexicon(\"%s\") took %.3fms%n", caller, lex, time);
        } catch (Exception e) {
            throw new TestAbortedException("Failed to load lexicon", e);
        }
        return Objects.requireNonNull(game);
    }
}
