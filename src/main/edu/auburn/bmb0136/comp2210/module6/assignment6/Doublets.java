package edu.auburn.bmb0136.comp2210.module6.assignment6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Provides an implementation of the WordLadderGame interface.
 *
 * @author Brandon Buckley (bmb0136@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    private final TreeSet<String> lexicon;

    public Doublets(InputStream in) {
        lexicon = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        try {
            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.nextLine();
                int i = str.indexOf(' ');
                str = i >= 0 ? str.substring(0, i) : str;
                lexicon.add(str.toLowerCase());
            }
            in.close();
        } catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str);
    }

    @Override
    public int getHammingDistance(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return -1;
        }
        int dist = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {
        if (sequence == null || sequence.isEmpty()) {
            return false;
        }
        for (int i = 0; i < sequence.size(); i++) {
            if (!isWord(sequence.get(i))) {
                return false;
            }
            if (i > 0 && getHammingDistance(sequence.get(i - 1), sequence.get(i)) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getNeighbors(String word) {
        if (word == null || word.isEmpty()) {
            return Collections.emptyList();
        }
        word = word.toLowerCase();
        ArrayList<String> l = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            String a = word.substring(0, i);
            String b = word.substring(i + 1);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String s = a + c + b;
                if (isWord(s)) {
                    l.add(s);
                }
            }
        }

        return l;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {
        if (start == null || end == null
            || start.isEmpty() || end.isEmpty()
            || start.length() != end.length()) {
            return Collections.emptyList();
        }
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }
        start = start.toLowerCase();
        end = end.toLowerCase();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        TreeSet<String> seen = new TreeSet<>();

        queue.add(new Node(start, null));
        while (!queue.isEmpty()) {
            Node w = queue.removeFirst();
            if (seen.contains(w.getWord())) {
                continue;
            }
            seen.add(w.getWord());

            List<String> neighbors = getNeighbors(w.getWord());
            for (String i : neighbors) {
                if (seen.contains(i)) {
                    continue;
                }
                if (i.equals(end)) {
                    return new Node(i, w).toPath();
                }
                queue.add(new Node(i, w));
            }
        }
        return Collections.emptyList();
    }

    private static class Node {
        private final String word;
        private final Node parent;

        private Node(String word, Node parent) {
            this.word = word;
            this.parent = parent;
        }

        public String getWord() {
            return word;
        }

        public List<String> toPath() {
            ArrayList<String> list = new ArrayList<>();
            Node n = this;
            while (n != null) {
                list.add(n.word);
                n = n.parent;
            }
            Collections.reverse(list);
            return list;
        }
    }
}

