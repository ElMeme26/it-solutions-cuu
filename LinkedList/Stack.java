package LinkedList;

public class Stack {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    private int top;
    private final int capacity = 5;
    private String[] data;
    
    public Stack() {
        this.data = new String[this.capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == this.capacity - 1;
    }

    public void push(String urg_task) {
        if (isFull()) {
            System.out.println(RED + "\nYa hay demasiados incidentes críticos por atender." + RESET);
            return;
        }

        data[++top] = urg_task;
        

    }

    public String pop() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay incidentes críticos por atender." + RESET);
            return null;
        } else {
            return data[top--];
        }
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay incidentes críticos por atender." + RESET);
            return null;
        } else {
            return data[top];
        }
    }

    public void show() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay incidentes críticos por atender." + RESET);
        } else {
            System.out.println(CYAN + "\nTareas urgentes pendientes: " + RESET);
            for (int i = top; i >= 0; i--) {
                System.out.println(YELLOW + (i+1) + ". " + data[i] + RESET);
            }
        }
    }
}