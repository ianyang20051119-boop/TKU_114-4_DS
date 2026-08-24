import java.util.*;

public class WordIndexSystem {
    public static void main(String[] args) {
        String[] sentences = {
            "Java is easy, Java is powerful.",
            "Java makes programming easy.",
            "Programming is powerful."
        };

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        Set<String> uniqueWords = new LinkedHashSet<>();

        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().split("[.,\\s]+");

            for (String word : words) {
                if (word.isEmpty()) {
                    continue;
                }

                uniqueWords.add(word);
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println("單字次數：");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }

        System.out.println();

        System.out.println("不重複單字：");
        System.out.println(uniqueWords);

        System.out.println();

        System.out.println("出現至少兩次的單字：");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + "：" + entry.getValue());
            }
        }
    }
}