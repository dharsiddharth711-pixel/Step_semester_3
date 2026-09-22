package home_assignment;

public class WordReversalEncoder {

    public static void main(String[] args) {
        String inputSentence = "hello club";
        String encodedSentence = reverseEachWord(inputSentence);

        System.out.println("Input : " + inputSentence);
        System.out.println("Output: " + encodedSentence);
    }

    /**
     * Reverses each word in a space-separated sentence while keeping word order intact.
     *
     * @param sentence Space-separated input string
     * @return Transformed string with each word reversed
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        try {
            // Split sentence into words using space delimiter
            String[] words = sentence.split(" ");
            StringBuilder resultBuilder = new StringBuilder();

            for (int i = 0; i < words.length; i++) {
                // Reverse individual word using StringBuilder
                StringBuilder wordReverser = new StringBuilder(words[i]);
                wordReverser.reverse();

                resultBuilder.append(wordReverser);

                // Add space between words (except for the last word)
                if (i < words.length - 1) {
                    resultBuilder.append(" ");
                }
            }

            return resultBuilder.toString();
        } catch (Exception e) {
            System.err.println("An error occurred during word reversal: " + e.getMessage());
            return "";
        }
    }
}
