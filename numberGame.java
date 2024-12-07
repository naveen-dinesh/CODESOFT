import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        boolean playAgain = true;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1; // Generate a number between 1 and 100
            int attemptsLeft = 5;
            boolean guessedCorrectly = false;

            System.out.println("\nA new round has started! Guess the number (1 to 100). You have " + attemptsLeft + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    totalScore += attemptsLeft; // Reward points based on remaining attempts
                    guessedCorrectly = true;
                    break;
                } else if (userGuess > generatedNumber) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Your guess is too low.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + generatedNumber);
            }

            System.out.println("Your total score: " + totalScore);
            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\nThanks for playing! Your final score: " + totalScore);
        scanner.close();
    }
}
