import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        new HangmanGame().startGame();
    }
  /*
   Main game class that is responsible for
   controlling game flow and initializations of
   class instances etc

   */
    public void startGame() {
        //Below we are initializing the instances of classes we have created so far
        Scanner scanner = new Scanner(System.in);//creating scanner
        AnimalStack animalStack = new AnimalStack(14);//Animal stack class instance here or object
        LetterStack letterStack = new LetterStack();//same thing
        HighScoreTable highScoreTable = new HighScoreTable();//same

        System.out.println("Welcome to Hangman Game!");
        boolean playAgain;
        char guess = ' ';

        do {
            String word = animalStack.getRandomAnimal().toUpperCase();
            WordStack wordStack = new WordStack(word);
            BoardStack boardStack = new BoardStack(word);
            MissingLetterStack missingLetterStack = new MissingLetterStack();
            boolean jokerUsed = false;

            int score = 120;
            boolean gameWon = false;
            boolean gameOver = false;

            do {
                boardStack.displayBoard(missingLetterStack.getMissedLetters(), score, letterStack.displayLetters());

                System.out.print("Guess a letter or use Joker (type 'J'): ");
                String input = scanner.nextLine().toUpperCase();

                if (!input.isEmpty() && input.charAt(0) == 'J') {
                    if (!jokerUsed) {
                        guess = word.charAt((int) (Math.random() * word.length()));
                        System.out.println("Joker used! Random letter chosen: " + guess);
                        jokerUsed = true;
                        letterStack.removeLetter(guess);
                    } else {
                        System.out.println("You already used Joker!");
                        continue;
                    }
                } else if (!input.isEmpty() && input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    guess = input.charAt(0);
                } else {
                    System.out.println("Invalid input. Please enter a letter or 'J' for Joker.");
                    continue;
                }

                if (wordStack.contains(guess)) {
                    System.out.println("Correct guess!");
                    score += 5;
                    boolean found = boardStack.updateBoard(guess);
                    if (!found) {
                        System.out.println("Error updating board.");
                    }
                    if (boardStack.isComplete()) {
                        gameWon = true;
                        break;
                    }
                } else {
                    System.out.println("Incorrect guess!");
                    if (!missingLetterStack.contains(guess)) {
                        score -= isVowel(guess) ? 15 : 20;
                        missingLetterStack.addMissedLetter(guess);
                    } else {
                        System.out.println("You already guessed this letter. Try another one.");
                    }
                }

                gameOver = score <= 0;
            } while (!gameOver);

            if (gameWon) {
                System.out.println("You win!!");
                System.out.println("Your score is " + score + ".");
                wordStack.updateWord();

                System.out.println("Word: " + wordStack.getRevealedWord());
            }

         else {
                System.out.println("You lost! The word was: " + word);
            }

            System.out.print("What is your name: ");
            String playerName = scanner.nextLine();

            highScoreTable.updateHighScoreTable(playerName, score);
            highScoreTable.displayHighScoreTable();

            System.out.print("Play again? (Y/N): ");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("Y");
        } while (playAgain);

        scanner.close();
    }

    private static boolean isVowel(char ch) {
        return "AEIOU".indexOf(ch) != -1;
    }
}
