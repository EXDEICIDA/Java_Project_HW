/*
    I created custom stack class
       for the project needs
 */
class Stack {
    private final String[] array;
    private int top;

    public Stack(int capacity) {
        array = new String[capacity];
        top = -1;
    }

    public void push(String item) {
        if (top == array.length - 1) {
            System.out.println("Stack overflow!");
            return;
        }
        top++;
        array[top] = item;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow!");
            return null;
        }
        String item = array[top];
        top--;
        return item;
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
