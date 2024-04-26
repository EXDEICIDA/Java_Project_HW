import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class HighScoreTable {
    private final Queue qName;
    private final Queue qScore;

    public HighScoreTable() {
        qName = new Queue(12);
        qScore = new Queue(12);
        readHighScoreTable("highscoretable.txt");
    }

    /*
    Hih score table class that is responsible for recoding
    high scores of players and their names in txt file
    Consisted of 5 methods and 1 constructor
     */

    //Read high score table from txt file
    private void readHighScoreTable(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    qName.enqueue(parts[0]);
                    qScore.enqueue(parts[1]);
                } else {
                    System.out.println("Invalid line in high score table: " + line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
            e.printStackTrace();
        }
    }

    //Updating table in the txt file
    public void updateHighScoreTable(String name, int score) {
        boolean found = false;
        Queue tempNameQueue = new Queue(12);
        Queue tempScoreQueue = new Queue(12);

        while (!qName.isEmpty()) {
            String existingName = qName.dequeue();
            String existingScore = qScore.dequeue();
            tempNameQueue.enqueue(existingName);
            tempScoreQueue.enqueue(existingScore);
            if (existingName.equals(name)) {
                found = true;
                int existingScoreInt = Integer.parseInt(existingScore);
                if (score > existingScoreInt) {
                    tempScoreQueue.dequeue();
                    tempScoreQueue.enqueue(String.valueOf(score));
                }
            }
        }

        if (!found || score > Integer.parseInt(tempScoreQueue.peek())) {
            tempNameQueue.enqueue(name);
            tempScoreQueue.enqueue(String.valueOf(score));
        }

        while (!tempNameQueue.isEmpty()) {
            qName.enqueue(tempNameQueue.dequeue());
            qScore.enqueue(tempScoreQueue.dequeue());
        }

        sortHighScoreTable();

        writeHighScoreTable("highscoretable.txt");
    }


    //Sorting method for getting the highest player when showing it
    private void sortHighScoreTable() {
        int n = qScore.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int score1 = Integer.parseInt(qScore.peek());
                qScore.dequeue();
                int score2 = Integer.parseInt(qScore.peek());
                if (score1 < score2) {
                    String name1 = qName.dequeue();
                    String name2 = qName.dequeue();
                    qName.enqueue(name1);
                    qScore.enqueue(String.valueOf(score1));
                    qName.enqueue(name2);
                    qScore.enqueue(String.valueOf(score2));
                } else {
                    qScore.enqueue(String.valueOf(score1));
                    qName.enqueue(qName.dequeue());
                }
            }
        }
    }

    //writing method
    private void writeHighScoreTable(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            while (!qName.isEmpty()) {
                writer.write(qName.dequeue() + " " + qScore.dequeue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + filename);
            e.printStackTrace();
        }
    }


    //display method
    public void displayHighScoreTable() {
        System.out.println("High Score Table:");
        try {
            File file = new File("highscoretable.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error reading file: highscoretable.txt");
            e.printStackTrace();
        }
    }

}
