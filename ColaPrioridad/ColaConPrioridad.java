package ColaPrioridad;

import java.util.Optional;

public class ColaConPrioridad {
    private Nodo<Tarea> inicio;
    private Nodo<Tarea> fin;
    private int numElementos;

    public ColaConPrioridad() {
        this.inicio = null;
        this.fin = null;
        this.numElementos = 0;
    }

    public void push(Tarea tarea) {
        Nodo<Tarea> nuevoNodo = new Nodo<>(tarea, tarea.getPrioridad(), null);

        if (inicio == null) {
            inicio = nuevoNodo;
            fin = nuevoNodo;
        } else {
            // Buscamos su lugar
            Nodo<Tarea> aux = inicio;
            Nodo<Tarea> penultimo = null;

            // Mientras el nodo actual sea más importante que el nuevo, sigue avanzando
            while (aux != null &&
                    (aux.getItem().getPrioridad() < tarea.getPrioridad() ||
                        (aux.getItem().getPrioridad() == tarea.getPrioridad() &&
                            !aux.getItem().getFechaEntrega().isAfter(tarea.getFechaEntrega())))) {
                penultimo = aux;
                aux = aux.getNext();
                
            }

            if (penultimo == null) {
                nuevoNodo.setNext(inicio);
                inicio = nuevoNodo;
            } else {
                penultimo.setNext(nuevoNodo);
                nuevoNodo.setNext(aux);
                if (aux == null) {
                    fin = nuevoNodo;
                }
            }
        }

        this.numElementos++;
    }

    public Optional<Tarea> pop() {

        // No hay nada que sacar
        if (inicio == null) {
            if (numElementos > 0) {
                numElementos = 0;
            }
            return Optional.empty();
        }

        Nodo<Tarea> nodoEliminado = inicio;
        inicio = inicio.getNext();
        numElementos--;

        if (inicio == null) {
            fin = null;
        }

        return Optional.of(nodoEliminado.getItem());
    }

    public boolean hasNext() {
        boolean next = false;

        if (inicio != null) {
            next = true;
        }

        return next;
    }

    public int size() {
        return this.numElementos;
    }

    public boolean isEmpty() {
        return this.numElementos == 0;
    }

    public String toString() {

        if (numElementos == 0) {
            return "La cola está vacía.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("*** Tareas Pendientes ***\n");
        Nodo<Tarea> aux = inicio;

        while (aux != null) {
            sb.append(aux.getItem().toString()).append("\n");
            aux = aux.getNext();
        }
        sb.append("===================");
        return sb.toString();
    }
}
