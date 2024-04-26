import java.util.Stack;

public class BoardStack {
    private final Stack<Character> boardStack;
    private final String word;

    public BoardStack(String word) {
        this.word = word;
        boardStack = new Stack<>();
        initializeBoard(word.length());
    }

    public void initializeBoard(int length) {
        for (int i = 0; i < length; i++) {
            boardStack.push('-');
        }
    }

    public int size() {
        return boardStack.size();
    }

    public boolean isFull() {
        for (char letter : boardStack) {
            if (letter == '-') {
                return false;
            }
        }
        return true;
    }

    public boolean updateBoard(char guess) {
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                boardStack.set(i, guess);
                found = true;
            }
        }
        return found;
    }

    public boolean isComplete() {
        for (char letter : boardStack) {
            if (letter == '-') {
                return false;
            }
        }
        return true;
    }

    public void displayBoard(String missedLetters, int score, String availableLetters) {
        StringBuilder board = new StringBuilder();
        for (char letter : boardStack) {
            if (letter != '-') {
                board.append(letter).append(" ");
            } else {
                board.append("_ ");
            }
        }


        System.out.println("Word: " + board.toString().trim() + "\tMisses: " + missedLetters +
                "\tScore: " + score + "\tAvailable Letters: " + availableLetters);
    }
}
