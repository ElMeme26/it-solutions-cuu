package LinkedList;

public class LinkedList {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";

    private NodeLL head;
    private NodeLL tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void insertFirst(String task) {
        NodeLL newNode = new NodeLL(task);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.forward = head;
            head = newNode;
        }
    }

    public void insertLast(String task) {
        NodeLL newNode = new NodeLL(task);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.forward = newNode;
            tail = newNode;
        }
    }

    public void deleteFirst() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas pendientes." + RESET);
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.forward;
        }
    }

    public void deleteLast() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas pendientes" + RESET);
            return;
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            NodeLL iteratorNode = head;

            while (iteratorNode.forward != tail) {
                iteratorNode = iteratorNode.forward;
            }

            tail = iteratorNode;
            tail.forward = null;
        }
    }

    public String findByDepartment(String department) {
        if (isEmpty()) {
            return "No hay tareas pendientes";
        }

        StringBuilder sb = new StringBuilder("--- Tareas del Depto: " + department + " ---\n\n");
        NodeLL iteratorNode = head;
        boolean found = false;

        while (iteratorNode != null) {
            if (iteratorNode.data.startsWith(department + ":")) {
                System.out.println(YELLOW + iteratorNode.data + RESET);
                found = true;
            }

            iteratorNode = iteratorNode.forward;
        }
        
        if (!found) {
            return "No se encontraron tareas para el departamento: " + department;
        }

        return sb.toString();
    }

    public void show() {
        if (isEmpty()) {
            System.out.println(RED + "\nNo hay tareas pendientes." + RESET);
            return;
        }

        NodeLL iteratorNode = head;

        while (iteratorNode != null) {
            System.out.print(YELLOW + iteratorNode.data + RESET);
            if (iteratorNode.forward != null) {
                System.out.print(BLUE + " -> " + RESET);
            }
            
            iteratorNode = iteratorNode.forward;
        }
    }
}
