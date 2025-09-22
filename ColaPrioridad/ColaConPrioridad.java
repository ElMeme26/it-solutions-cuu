package ColaPrioridad;

import java.util.Optional;

public class ColaConPrioridad<V> {
    private Nodo<V> inicio;
    private Nodo<V> fin;
    private int numElementos;

    public ColaConPrioridad() {
        this.inicio = null;
        this.fin = null;
        this.numElementos = 0;
    }

    public void push(V item, int prioridad) {
        Nodo<V> nodo = new Nodo<>(item, prioridad, null);

        if (inicio == null) {
            inicio = nodo;
            fin = nodo;
        } else {
            // Buscamos su lugar
            Nodo<V> aux = inicio;

            // Miramos si tenemos que insertar al principio
            if (aux.getPrioridad() < prioridad) {
                inicio = nodo;
                inicio.setNext(aux);
            } else {
                Nodo<V> penultimoElemento = null;

                while (aux != null && aux.getPrioridad() >= prioridad) {
                    penultimoElemento = aux;
                    aux = aux.getNext();
                }

                // Aqui ya tenemos donde tenemos que insertar
                penultimoElemento.setNext(nodo);
                nodo.setNext(aux);
            }
        }

        this.numElementos++;
    }

    public Optional<V> pop() {
        Optional<V> item = Optional.empty();

        if (this.numElementos > 0) {
            item = Optional.of(inicio.getItem());
            this.numElementos--;

            inicio = inicio.getNext();
        }

        return item;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<V> aux = inicio;

        while (aux != null) {
            sb.append(aux.getItem() + ", Prioridad: " + aux.getPrioridad() + "\n");
            aux = aux.getNext();
        }

        return sb.toString();
    }
}
