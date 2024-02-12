package edu.auburn.bmb0136.comp2210.module5.assignment5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Game implements WordSearchGame {
    private Trie lexicon;
    private String[] board = new String[0];
    private int squareSize;

    @Override
    public void loadLexicon(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException();
        }
        try {
            lexicon = new Trie();
            for (String line : Files.readAllLines(new File(fileName).toPath())) {
                int i = line.indexOf(' ');
                i = i < 0 ? line.length() : i;
                lexicon.add(line.substring(0, i).toUpperCase());
            }
        } catch (IOException e) {
            lexicon = null;
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        int max = Arrays.stream(board).mapToInt(String::length).max().orElse(1);
        for (int i = 0; i < squareSize; i++) {
            for (int j = 0; j < squareSize; j++) {
                int pos = (i * squareSize) + j;
                String s = board[pos];
                sb.append(s);
                for (int k = s.length(); k <= max; k++) {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }
        double size = Math.sqrt(letterArray.length);
        if (Math.abs(size - (int)size) > 1e-6) {
            throw new IllegalArgumentException();
        }
        board = new String[letterArray.length];
        squareSize = (int)Math.round(size);
        for (int i = 0; i < letterArray.length; i++) {
            board[i] = letterArray[i];
        }
    }

    @Override
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        throwIfNullOrNoLexicon(minimumWordLength);
        SortedSet<String> set = new TreeSet<>();
        ArrayList<Integer> path = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            path.clear();
            path.add(i);
            getAllScorableWordsSearch(set, i, minimumWordLength, board[i], path);
        }
        return set;
    }

    private void getAllScorableWordsSearch(SortedSet<String> words, int pos, int size, String w, ArrayList<Integer> path) {
        if (w.length() >= size && isValidWord(w)) {
            words.add(w);
        }
        int x = pos % squareSize;
        int y = pos / squareSize;
        for (int i = 0; i < 9; i++) {
            // 0 1 2
            // 3 4 5
            // 6 7 8
            if (i == 4) {
                continue;
            }
            int dx = (i % 3) - 1;
            int dy = (i / 3) - 1;
            if (dx < 0 && x == 0 || dx > 0 && x == squareSize - 1) {
                continue;
            }
            if (dy < 0 && y == 0 || dy > 0 && y == squareSize - 1) {
                continue;
            }
            int nextPos = pos + dx + (dy * squareSize);
            if (path.contains(nextPos)) {
                continue;
            }
            String nextW = w + board[nextPos];

            if (isValidPrefix(nextW)) {
                path.add(nextPos);
                getAllScorableWordsSearch(words, nextPos, size, nextW, path);
                path.remove(path.size() - 1);
            }
        }
    }

    @Override
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        throwIfNullOrNoLexicon(words);
        int score = 0;
        for (String w : words) {
            if (isValidWord(w)) {
                score += Math.max(0, isOnBoard(w).size() - minimumWordLength + 1);
            }
        }
        return score;
    }

    @Override
    public boolean isValidWord(String wordToCheck) {
        throwIfNullOrNoLexicon(wordToCheck);
        return lexicon.contains(wordToCheck.toUpperCase());
    }

    @Override
    public boolean isValidPrefix(String prefixToCheck) {
        throwIfNullOrNoLexicon(prefixToCheck);
        return lexicon.containsPrefix(prefixToCheck.toUpperCase());
    }

    @Override
    public List<Integer> isOnBoard(String wordToCheck) {
        throwIfNullOrNoLexicon(wordToCheck);
        if (wordToCheck.isEmpty()) {
            return Collections.emptyList();
        }
        for (int i = 0; i < board.length; i++) {
            if (!wordToCheck.startsWith(board[i])) {
                continue;
            }
            ArrayList<Integer> seen = new ArrayList<>();
            seen.add(i);
            List<Integer> path = isOnBoardSearch(wordToCheck.substring(board[i].length()), i, seen);
            if (path != null) {
                return path;
            }
        }
        return Collections.emptyList();
    }

    private List<Integer> isOnBoardSearch(String word, int pos, ArrayList<Integer> path) {
        if (word.isEmpty()) {
            return path;
        }
        int x = pos % squareSize;
        int y = pos / squareSize;
        for (int i = 0; i < 9; i++) {
            // 0 1 2
            // 3 4 5
            // 6 7 8
            if (i == 4) {
                continue;
            }
            int dx = (i % 3) - 1;
            int dy = (i / 3) - 1;
            if (dx < 0 && x == 0 || dx > 0 && x == squareSize - 1) {
                continue;
            }
            if (dy < 0 && y == 0 || dy > 0 && y == squareSize - 1) {
                continue;
            }
            int nextPos = pos + dx + (dy * squareSize);
            if (path.contains(nextPos)) {
                continue;
            }
            if (word.startsWith(board[nextPos])) {
                path.add(nextPos);
                List<Integer> res = isOnBoardSearch(word.substring(board[nextPos].length()), nextPos, path);
                if (res != null) {
                    return res;
                }
                path.remove(path.size() - 1);
            }
        }
        return null;
    }

    private <T> void throwIfNullOrNoLexicon(T input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (lexicon == null) {
            throw new IllegalStateException();
        }
    }
}
