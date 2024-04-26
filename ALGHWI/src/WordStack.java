public class WordStack {
    private String word;
    private boolean[] revealed;

    public WordStack(String word) {
        this.word = word;
        revealed = new boolean[word.length()];
    }

    public boolean contains(char letter) {
        return word.indexOf(letter) != -1;
    }

    public void updateWord() {
        for (int i = 0; i < word.length(); i++) {
            revealed[i] = true;
        }
    }



    public String getRevealedWord() {
        StringBuilder revealedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (revealed[i]) {
                revealedWord.append(word.charAt(i));
            } else {
                revealedWord.append("_");
            }
            revealedWord.append(" ");
        }
        return revealedWord.toString().trim();
    }

    public boolean isComplete() {
        for (boolean letterRevealed : revealed) {
            if (!letterRevealed) {
                return false;
            }
        }
        return true;
    }
}
