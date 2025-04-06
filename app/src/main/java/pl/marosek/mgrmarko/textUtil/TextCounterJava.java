package pl.marosek.mgrmarko.textUtil;

import java.util.HashMap;
import java.util.Map;

public class TextCounterJava {
    public Map<String, Integer> countWords(String text) {
        Map<String, Integer> words = new HashMap<>();
        String[] wordsArray = text.split("\\s+");
        for (String word : wordsArray) {
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }
        return words;
    }
}
