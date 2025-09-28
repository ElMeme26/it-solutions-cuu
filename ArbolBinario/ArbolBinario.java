package ArbolBinario;

import java.util.Stack;
 
public class ArbolBinario {
 
    private NodoArbol raiz;
 
    public ArbolBinario() {
        this.raiz = null;
    }
 
    public void insertar(Empleado empleado) {
        NodoArbol nuevoNodo = new NodoArbol(empleado);
        
        // Si el árbol está vacío
        if (raiz == null) {
            raiz = nuevoNodo;
            return;
        }
        // Si el árbol no está vacío
        NodoArbol aux = raiz;
        NodoArbol padre = null;

        while (aux != null) {
            padre = aux;

            if (empleado.getId() < aux.getEmpleado().getId()) {
                aux = aux.getIzq();
            } else if (empleado.getId() > aux.getEmpleado().getId()) {
                aux = aux.getDer();
            } else {
                // Si existe el ID no se hace nada
                System.out.println("ID duplicado, no se insertó el empleado");
                return;
            }
        }

        // Si se encuentra donde insertarlo
        if (empleado.getId() < padre.getEmpleado().getId()) {
            padre.setIzq(nuevoNodo);
        } else {
            padre.setDer(nuevoNodo);
        }
    }    
 
    public Empleado buscar(int id) {
        NodoArbol aux = raiz;
        
        while (aux != null) {
            // Si se encuentra el empleado
            if (id == aux.getEmpleado().getId()) {
                return aux.getEmpleado();
            }

            // Si no se encuentra, se decide si bajamos al nodo izquirdo o derecho
            if (id < aux.getEmpleado().getId()) {
                aux = aux.getIzq();
            } else {
                aux = aux.getDer();
            }
        }
        // Si no se encuentra nada
        return null;
    }
 
    public void eliminar(int id) {
        NodoArbol aux = raiz;
        NodoArbol padre = null;
        boolean esHijoIzq = false;
 
        // Encontrar nodo a eliminar
        while (aux != null && aux.getEmpleado().getId() != id) {
            padre = aux;

            if (id < aux.getEmpleado().getId()) {
                aux = aux.getIzq();
                esHijoIzq = true;
            } else {
                aux = aux.getDer();
                esHijoIzq = false;
            }
        }

        // Si el nodo no se encuentra, pasamos
        if (aux == null) {
            System.err.println("No se encontró el empleado con ID " + id + " para eliminar.");
            return;
        }

        // Si el nodo a eliminar es un nodo hoja
        if (aux.getIzq() == null && aux.getDer() == null) {
            if (aux == raiz) {
                raiz = null;
            } else if (esHijoIzq) {
                padre.setIzq(null);
            } else {
                padre.setDer(null);
            }
        
        // Si el nodo a eliminar tiene un solo hijo
        } else if (aux.getDer() == null) {
            if (aux == raiz) {
                raiz = aux.getIzq();
            } else if (esHijoIzq) {
                padre.setIzq(aux.getIzq());
            } else {
                padre.setDer(aux.getIzq());
            }

        // Si no tiene hijo izquierdo
        } else if (aux.getIzq() == null) {
            if (aux == raiz) {
                raiz = aux.getDer();
            } else if (esHijoIzq) {
                padre.setIzq(aux.getDer());
            } else {
                padre.setDer(aux.getDer());
            }

        // Si el nodo a eliminar tiene 2 hijos
        } else {
            NodoArbol sucesor = encontrarSucesor(aux);

            aux.setEmpleado(sucesor.getEmpleado());

            eliminarSucesor(aux, sucesor);
        }
    }

    private NodoArbol encontrarSucesor(NodoArbol nodoConDosHijos) {
        NodoArbol aux = nodoConDosHijos.getDer();

        while (aux.getIzq() != null) {
            aux = aux.getIzq();
        }
        return aux;
    }

    private void eliminarSucesor(NodoArbol nodoPadreDelSucesor, NodoArbol sucesor) {
        NodoArbol aux = nodoPadreDelSucesor.getDer();

        if (aux == sucesor) {
            nodoPadreDelSucesor.setDer(sucesor.getDer());
            return;
        }

        while (aux.getIzq() != sucesor) {
            aux = aux.getIzq();
        }

        aux.setIzq(sucesor.getDer());
    }

    public String mostrar() {
        if (raiz == null) {
            return "No hay empleados en el sistema.";
        }

        StringBuilder sb = new StringBuilder("=== Listado de Empleados ===\n");
        Stack<NodoArbol> stack = new Stack<>();
        NodoArbol aux = raiz;

        while (aux != null || !stack.isEmpty()) {
            
            while (aux != null) {
                stack.push(aux);
                aux = aux.getIzq();
            }

            aux = stack.pop();

            sb.append(aux.getEmpleado().toString()).append("\n");
            
            aux = aux.getDer();
        }
        return sb.toString();
    }
 
 
}