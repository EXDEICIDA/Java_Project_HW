import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class AnimalStack {
    private final Stack animalStack;

    public AnimalStack(int capacity) {
        animalStack = new Stack(capacity);
        readAnimalFile("animals.txt");
    }


    //read animal txt file
    private void readAnimalFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                animalStack.push(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        }
    }

    public String getRandomAnimal() {
        if (animalStack.isEmpty()) {
            System.out.println("Animal stack is empty!");
            return null;
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(animalStack.size());
            return getAnimalAt(randomIndex);
        }
        //getting random animal name
    }


    //this code is for getting the spesficied index from the stack
    private String getAnimalAt(int index) {
        Stack tempStack = new Stack(animalStack.size());
        String animal = "";
        for (int i = 0; i <= index; i++) {
            animal = (String) animalStack.pop();
            tempStack.push(animal);
        }
        while (!tempStack.isEmpty()) {
            animalStack.push((String) tempStack.pop());
        }
        return animal;
    }
}
