package home_assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordFrequencyReport {

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        System.out.println("Feedback Input:\n\"" + feedback + "\"\n");
        System.out.println("Word Frequency Report:");
        printFilteredWordFrequency(feedback);
    }

    /**
     * Normalizes text, strips punctuation, filters stop-words, and prints frequency in descending order.
     *
     * @param feedback Paragraph text input
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        try {
            // Fixed set of stop-words to exclude
            Set<String> stopWords = new HashSet<>(Arrays.asList(
                    "the", "was", "and", "a", "is", "of", "in"
            ));

            // Clean input: lowercase and remove punctuation marks
            String cleanedText = feedback.toLowerCase()
                    .replace(".", "")
                    .replace(",", "");

            // Split into words using whitespace pattern
            String[] words = cleanedText.split("\\s+");

            Map<String, Integer> frequencyMap = new HashMap<>();

            // Count frequencies while skipping stop-words
            for (String word : words) {
                if (!word.isEmpty() && !stopWords.contains(word)) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }

            // Convert map entries to list and sort descending by frequency count
            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
            sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Print the resulting frequencies
            for (Map.Entry<String, Integer> entry : sortedEntries) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception e) {
            System.err.println("Error processing word frequency: " + e.getMessage());
        }
    }
}