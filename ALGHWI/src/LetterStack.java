import java.util.Stack;

public class LetterStack {
    private final Stack<Character> letterStack;

    public LetterStack() {
        letterStack = new Stack<>();
        initializeLetterStack();
    }
   //Letter stack initializer
    private void initializeLetterStack() {
        for (char c = 'A'; c <= 'Z'; c++) {
            letterStack.push(c);
        }
    }

    public char getLetter() {
        if (!letterStack.isEmpty()) {
            return letterStack.pop();
        } else {
            System.out.println("Letter stack is empty!");
            return '\0';
        }
    }

    // Method to remove a specific letter from the stack
    public void removeLetter(char letter) {
        letterStack.remove((Character) letter);
    }

    public String displayLetters() {
        StringBuilder availableLetters = new StringBuilder();
        for (char letter : letterStack) {
            availableLetters.append(letter);
        }
        return availableLetters.toString();
    }
}
