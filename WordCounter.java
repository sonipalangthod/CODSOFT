import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Word Counter!");
        System.out.println("Enter '1' to input text or '2' to provide a file path:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String inputText = "";

        switch (choice) {
            case 1:
                System.out.println("Enter your text:");
                inputText = scanner.nextLine();
                break;
            case 2:
                System.out.println("Enter the file path:");
                String filePath = scanner.nextLine();
                try {
                    inputText = readFromFile(filePath);
                } catch (IOException e) {
                    System.out.println("Error reading the file: " + e.getMessage());
                    System.exit(1);
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                System.exit(1);
        }

        int wordCount = countWords(inputText);
        System.out.println("Total word count: " + wordCount);
    }

    private static String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static int countWords(String inputText) {
        String[] words = inputText.split("\\s+|\\p{Punct}"); // Split using space or punctuation as delimiters
        return words.length;
    }
}
