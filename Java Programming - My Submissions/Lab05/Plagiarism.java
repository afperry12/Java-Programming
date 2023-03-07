public class Plagiarism {
    public static void main(String[] args) {
        // Create new object of WordCounter
        WordCounter plagiarismData = new WordCounter();
        // Ensure something is being passed into command line
        if (args.length > 0) {
            // Separate words into array and put words into WordCounter
            String[] splitWords = args[0].split(" ");
            for (int x = 0; x < splitWords.length; x++) {
                if (splitWords[x].length() > 0) {
                    plagiarismData.inputWord(splitWords[x].toLowerCase().replaceAll("[^A-Za-z0-9]", ""));
                }
            }
            // Retrieve values from WordCounter object
            double totalNumberOfWords = plagiarismData.getTotal();
            double totalNumberOfThes = plagiarismData.getTotalThes();
            double totalNumberOfAsorAns = plagiarismData.getTotalAsOrAns();
            // Calculate requested percentages and cast to doubles
            double percentageOfThe = ((double) (totalNumberOfThes / totalNumberOfWords) * 100);
            double percentageOfAOrAn = ((double) (totalNumberOfAsorAns / totalNumberOfWords) * 100);
            // Print percentages as integers
            System.out.println("Percentage of THE words is " + (int) (percentageOfThe));
            System.out.println("Percentage of A or AN words is " + (int) percentageOfAOrAn);
        }
    }
}

class WordCounter {
    // Keep track of word counts
    int totalNumberOfWords = 0;
    int numberOfThes = 0;
    int numberOfAsOrAns = 0;

    // Adjust word count values based on inputted string
    public void inputWord(String x) {
        if (x.equals("the")) {
            totalNumberOfWords += 1;
            numberOfThes += 1;
        } else if (x.equals("a") || x.equals("an")) {
            totalNumberOfWords += 1;
            numberOfAsOrAns += 1;
        } else {
            totalNumberOfWords += 1;
        }
    }

    // Methods to retrieve values from class
    public int getTotalThes() {
        return numberOfThes;
    }

    public int getTotalAsOrAns() {
        return numberOfAsOrAns;
    }

    public int getTotal() {
        return totalNumberOfWords;
    }
}