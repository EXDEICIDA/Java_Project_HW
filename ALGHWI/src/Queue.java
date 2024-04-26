   /*
      Same goes here only for the custom queue class
    */
class Queue {
    private final int maxSize;
    private final String[] queueArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int size) {
        maxSize = size;
        queueArray = new String[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void enqueue(String item) {
        if (!isFull()) {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queueArray[++rear] = item;
            nItems++;
        } else {
            System.out.println("Queue is full");
        }
    }

    public String dequeue() {
        if (!isEmpty()) {
            String temp = queueArray[front++];
            if (front == maxSize) {
                front = 0;
            }
            nItems--;
            return temp;
        } else {
            System.out.println("Queue is empty");
            return null;
        }
    }

    public String peek() {
        return queueArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }
}
