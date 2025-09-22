package ColaPrioridad;

public class Nodo<V> {
    
    private V item;
    private int prioridad;
    private Nodo<V> next;

    public Nodo(V item, Nodo<V> next) {
        super();
        this.next = next;
        this.item = item;
    }

    public Nodo(V item, int prioridad, Nodo<V> next) {
        super();
        this.item = item;
        this.prioridad = prioridad;
        this.next = next;
    }

    public Nodo<V> getNext() {
        return next;
    }

    public void setNext(Nodo<V> next) {
        this.next = next;
    }

    public V getItem() {
        return item;
    }

    public void setItem(V item) {
        this.item = item;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
