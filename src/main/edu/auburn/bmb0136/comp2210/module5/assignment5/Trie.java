package edu.auburn.bmb0136.comp2210.module5.assignment5;

public class Trie  {

    private final Trie[] children = new Trie[26];
    private boolean isWord;

    public void add(String str) {
        if (!isWord(str)) {
            return;
        }
        Trie current = this;
        for (int i = 0; i < str.length(); i++) {
            int j = Character.toUpperCase(str.charAt(i)) - 'A';

            Trie next = current.children[j];
            if (next == null) {
                current.children[j] = next = new Trie();
            }
            current = next;
        }
        current.isWord = true;
    }

    public boolean contains(String str) {
        return contains(str, false);
    }

    public boolean containsPrefix(String str) {
        return contains(str, true);
    }

    private boolean contains(String word, boolean allowPrefix) {
        if (!isWord(word)) {
            return false;
        }
        Trie current = this;
        for (int i = 0; i < word.length(); i++) {
            // Extract first 5 bits of ASCII value
            int j = Character.toUpperCase(word.charAt(i)) - 'A';

            Trie next = current.children[j];
            if (next == null) {
                return false;
            }
            current = next;
        }
        return allowPrefix || current.isWord;
    }

    private static boolean isWord(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                return false;
            }
        }
        return true;
    }
}
