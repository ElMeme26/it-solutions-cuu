package ArbolBinario;

public class NodoArbol {
 
    private Empleado empleado;
    private NodoArbol izq;
    private NodoArbol der;
     
    public NodoArbol(Empleado empleado) {
        this.empleado = empleado;
        this.izq = null;
        this.der = null;
    }
 
    public Empleado getEmpleado() {
        return empleado;
    }
 
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
 
    public NodoArbol getIzq() {
        return izq;
    }
 
    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }
 
    public NodoArbol getDer() {
        return der;
    }
 
    public void setDer(NodoArbol der) {
        this.der = der;
    }
}