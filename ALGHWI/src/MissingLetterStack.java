import java.util.Stack;

class MissingLetterStack {
    private final Stack<Character> missingLetterStack;

    public MissingLetterStack() {
        missingLetterStack = new Stack<>();
    }

    /*
    public void pushMissingLetter(char letter) {
        missingLetterStack.push(letter);
    }
    */
    /*
    public char popMissingLetter() {
        return missingLetterStack.pop();
    }
   */
    public void displayMissingLetters() {
        System.out.print("Misses: ");
        Stack<Character> tempStack = new Stack<>();
        while (!missingLetterStack.isEmpty()) {
            char letter = missingLetterStack.pop();
            System.out.print(letter + " ");
            tempStack.push(letter);
        }
        while (!tempStack.isEmpty()) {
            missingLetterStack.push(tempStack.pop());
        }
        System.out.println();
    }

    public boolean contains(char letter) {
        Stack<Character> tempStack = new Stack<>();
        boolean found = false;
        while (!missingLetterStack.isEmpty()) {
            char currentLetter = missingLetterStack.pop();
            tempStack.push(currentLetter);
            if (currentLetter == letter) {
                found = true;
            }
        }
        while (!tempStack.isEmpty()) {
            missingLetterStack.push(tempStack.pop());
        }
        return found;
    }

    public void clear() {
        missingLetterStack.clear();
    }

    public String getMissedLetters() {
        StringBuilder sb = new StringBuilder();
        for (char letter : missingLetterStack) {
            sb.append(letter).append(" ");
        }
        return sb.toString();
    }

    public String getAvailableLetters() {
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!missingLetterStack.contains(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void addMissedLetter(char letter) {
        missingLetterStack.push(letter);
    }
}
