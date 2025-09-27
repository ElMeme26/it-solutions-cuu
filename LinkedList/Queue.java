package LinkedList;

public class Queue {
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    private int front;
    private int rear;
    private final int capacity = 40;
    private String[] data;

    public Queue() {
        this.data = new String[capacity];
        front = -1;
        rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return rear == this.capacity - 1;
    }

    public void enqueue(String norm_task) {
        if (isFull()) {
            System.out.println(RED + "\nYa hay demasiadas tareas programadas." + RESET);
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear++;
        }

        data[rear] = norm_task;
        System.out.println(CYAN + "\nTarea programada: " + norm_task + RESET);
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas programadas por eliminar." + RESET);
            return null;
        }

        String task = data[front];
        data[front] = null;

        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front++;
        }

        return task;
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas programadas." + RESET);
            return null;
        } else {
            return data[front];
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas programadas." + RESET);
            return;
        }

        System.out.println(CYAN + "\nTareas programadas: " + RESET);
            for (int i = front; i <= rear; i++) {
                System.out.print(YELLOW + data[i] + " | " + RESET);
            }
    }
}
