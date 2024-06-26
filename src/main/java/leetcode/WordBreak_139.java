package main.java.leetcode;

import main.java.common.TrieNode;

import java.util.Arrays;
import java.util.List;

public class WordBreak_139 {

    public static void main(String[] args) {
        WordBreak_139_Solution solution = new WordBreak_139_Solution();
        String[] dictionary = new String[]{"aaaa", "aaa"};
        String sentence = "aaaaaaa";
        System.out.println(solution.wordBreak(sentence, Arrays.asList(dictionary)));
    }
}


class WordBreak_139_Solution {
    private final TrieNode root;

    public WordBreak_139_Solution() {
        root = new TrieNode();
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            insert(word);
        }

        int index = 0;
        while (index < s.length()) {
            s = s.substring(index);
            index = checkPrefix(s);
            if (index == 0) {
                return false;
            }
        }
        return true;
    }

    private int checkPrefix(String s) {
        TrieNode curr = root;
        int depth = 0;
        for (char c : s.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                break;
            }
            curr = curr.children.get(c);
            depth++;
        }
        return curr.endOfWord ? depth : 0;
    }


    private void insert(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.endOfWord = true;
    }
}