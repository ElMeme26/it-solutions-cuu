package ArbolBinario;

import java.util.ArrayList;
 
public class ArbolBinario {
 
    Nodo<Integer> raiz = null;
    private int numeroElementos = 0;
    private int numeroBusquedas = 0;
    private int numeroIteracionesTotal = 0;
    private int numeroIteracionesUltimaBusqueda = 0;
 
    public ArbolBinario() {
 
    }
 
    public void insertarElemento(Integer value) {
        Nodo<Integer> nuevoNodo = new Nodo<Integer>(value, null, null);
 
        if (raiz == null) {
            raiz = nuevoNodo;
            System.out.println("Inserto la raiz");
        } else {
            // Necesitamos encontrar en que posición debemos insertar el nodo
            Nodo<Integer> aux = raiz;
 
            while (aux != null) {
                // Comprobamos si tenemos que insertarlo ya
                // Comprobamos si nodo hoja
                if (aux.getDer() == null && aux.getIzq() == null) {
                    if (value > aux.getValue()) {
                        // Derecha
                        System.out.println(value + " Lo insertamos a la derecha de: " + aux.getValue());
                        aux.setDer(nuevoNodo);
                        aux = null;
                    } else {
                        // Izquierda
                        System.out.println(value + " Lo insertamos a la izquierda de: " + aux.getValue());
                        aux.setIzq(nuevoNodo);
                        aux = null;
                    }
                } else if (value > aux.getValue() && aux.getDer() == null) {
                    // Lo insertamos a la derecha
                    System.out.println(value + " Lo insertamos a la derecha de: " + aux.getValue());
                    aux.setDer(nuevoNodo);
                    aux = null;
                } else if (value < aux.getValue() && aux.getIzq() == null) {
                    // Lo insertamos a la izquierda
                    System.out.println(value + " Lo insertamos a la izquierda de: " + aux.getValue());
                    aux.setIzq(nuevoNodo);
                    aux = null;
                } else {
                    // Pasamos de nodo
                    if (value > aux.getValue()) {
                        aux = aux.getDer();
                    } else {
                        aux = aux.getIzq();
                    }
                }
 
            }
 
        }
 
        // Incrementamos el número de elementos en 1
        numeroElementos++;
 
    }
 
    private void insertarNodo(Nodo<Integer> nodo) {
        if (raiz == null) {
            raiz = nodo;
            System.out.println("Inserto la raiz");
        } else {
            // Necesitamos encontrar en que posición debemos insertar el nodo
            Nodo<Integer> aux = raiz;
 
            while (aux != null) {
                // Comprobamos si tenemos que insertarlo ya
                // Comprobamos si nodo hoja
                if (aux.getDer() == null && aux.getIzq() == null) {
                    if (nodo.getValue() > aux.getValue()) {
                        // Derecha
                        System.out.println(nodo.getValue() + " Lo insertamos a la derecha de: " + aux.getValue());
                        aux.setDer(nodo);
                        aux = null;
                    } else {
                        // Izquierda
                        System.out.println(nodo.getValue() + " Lo insertamos a la izquierda de: " + aux.getValue());
                        aux.setIzq(nodo);
                        aux = null;
                    }
                } else if (nodo.getValue() > aux.getValue() && aux.getDer() == null) {
                    // Lo insertamos a la derecha
                    System.out.println(nodo.getValue() + " Lo insertamos a la derecha de: " + aux.getValue());
                    aux.setDer(nodo);
                    aux = null;
                } else if (nodo.getValue() < aux.getValue() && aux.getIzq() == null) {
                    // Lo insertamos a la izquierda
                    System.out.println(nodo.getValue() + " Lo insertamos a la izquierda de: " + aux.getValue());
                    aux.setIzq(nodo);
                    aux = null;
                } else {
                    // Pasamos de nodo
                    if (nodo.getValue() > aux.getValue()) {
                        aux = aux.getDer();
                    } else {
                        aux = aux.getIzq();
                    }
                }
 
            }
        }
    }
 
    public Integer buscarElemento(Integer value) {
        Nodo<Integer> aux = raiz;
        Nodo<Integer> resultado = null;
        numeroIteracionesUltimaBusqueda = 0;
 
        while (aux != null) {
            // Comprobamos si es el valor
            if (aux.getValue() == value) {
                // Hemos encontrado el elemento
                resultado = aux;
                aux = null;
            } else if (aux.getDer() == null && aux.getIzq() == null) {
                // Si hemos llegado a un nodo hoja el elemento no está en el ABB
                // El elemento no está
                aux = null;
            } else if (value > aux.getValue() && aux.getDer() != null) {
                // Si el valor es mayor y tenemos nodo a la derecha, vamos a la derecha
                aux = aux.getDer();
            } else if (value < aux.getValue() && aux.getIzq() != null) {
                // Si el valor es menos y tenemos nodo a la izquierda, vamos a la izquierda
                aux = aux.getIzq();
            } else {
                // Si el nodo es mayor y no tenemos nodo a la derecha o es menor y no tenemos
                // nodo a la izquierda
                // El elemento no está
                aux = null;
            }
 
            numeroIteracionesUltimaBusqueda++;
        }
 
        numeroIteracionesTotal += numeroIteracionesUltimaBusqueda;
        numeroBusquedas++;
 
        if (resultado != null) {
            return resultado.getValue();
        }else {
            return null;
        }
    }
 
    public boolean eliminarElemento(Integer value) {
        boolean resultado = false;
        Nodo<Integer> aux = raiz;
 
        while (aux != null) {
            // Si es la raiz
            if (aux.getValue() == value) {
                Nodo<Integer> nodoAEliminar = aux;
 
                if (aux.getDer() != null) {
                    raiz = aux.getDer();
                    if (nodoAEliminar.getIzq() != null) {
                        insertarNodo(nodoAEliminar.getIzq());
                        nodoAEliminar.setIzq(null);
                        nodoAEliminar.setDer(null);
                    }
                }else if (aux.getIzq() != null) {
                    raiz = aux.getIzq();
                    if (nodoAEliminar.getDer() != null) {
                        insertarNodo(nodoAEliminar.getDer());
                        nodoAEliminar.setIzq(null);
                        nodoAEliminar.setDer(null);
                    }
                }else {
                    raiz = null;
                }
                 
                resultado = true;
                aux = null;
            } else if (aux.getIzq() != null && aux.getIzq().getValue() == value) {
                Nodo<Integer> nodoAEliminar = aux.getIzq();
                // Si el valor está a la izquierda del nodo que estamos recorriendo
                // Miramos si tenemos izquierda en el nodo a eliminar
 
                if (aux.getIzq().getIzq() != null) {
                    // Tenemos Nodo a la izquierda
                    // Apuntamos el nodo que estamos recorriendo al siguiente del nodo a eliminar
                    aux.setIzq(aux.getIzq().getIzq());
                    // Reposicionamos sus hijos
                    if (nodoAEliminar.getDer() != null) {
                        insertarNodo(nodoAEliminar.getDer());
                    }
                    nodoAEliminar.setDer(null);
                    nodoAEliminar.setIzq(null);
                    resultado = true;
                    aux = null;
                } else {
                    // No tenemos nodo a la izquierda del elemento a eliminar
                    // Miramos si es nodo hoja
 
                    if (aux.getIzq() == null && aux.getDer() == null) {
                        aux.setIzq(null);
                    } else {
                        aux.setIzq(null);
                        if (nodoAEliminar.getDer() != null) {
                            insertarNodo(nodoAEliminar.getDer());
                        }
                    }
                     
                    resultado = true;
                    aux = null;
                }
 
            } else if (aux.getDer() != null && aux.getDer().getValue() == value) {
                Nodo<Integer> nodoAEliminar = aux.getDer();
                // Si el valor está a la derecha del nodo que estamos recorriendo
                // Miramos si tenemos derecha en el nodo a eliminar
 
                if (aux.getDer().getDer() != null) {
                    // Tenemos Nodo a la izquierda
                    // Apuntamos el nodo que estamos recorriendo al siguiente del nodo a eliminar
                    aux.setDer(aux.getDer().getDer());
                    // Reposicionamos sus hijos
                    if (nodoAEliminar.getIzq() != null) {
                        insertarNodo(nodoAEliminar.getIzq());
                    }
                    nodoAEliminar.setDer(null);
                    nodoAEliminar.setIzq(null);
                    resultado = true;
                    aux = null;
                } else {
                    // No tenemos nodo a la izquierda del elemento a eliminar
                    // Miramos si es nodo hoja
 
                    if (aux.getIzq().getIzq() == null && aux.getDer().getDer() == null) {
                        aux.setDer(null);
                    } else {
                        aux.setDer(null);
                        if (nodoAEliminar.getIzq() != null) {
                            insertarNodo(nodoAEliminar.getIzq());
                        }
 
                    }
                     
                    resultado = true;
                    aux = null;
                }
            } else {
                if (value > aux.getValue()) {
                    aux = aux.getDer();
                } else {
                    aux = aux.getIzq();
                }
            }
        }
         
        //En caso de borrar el nodo disminuimos la cantidad de nodos en 1
        if (resultado) {
            numeroElementos--;
        }
 
        return resultado;
 
    }
 
    public int size() {
        return this.numeroElementos;
    }
 
    public int getNumeroIteracionesMedioEnBusquedas() {
        return (int) Math.ceil(numeroIteracionesTotal / (double) this.numeroBusquedas);
    }
 
    public int getNumeroIteracionesUltimaBusqueda() {
        return this.numeroIteracionesUltimaBusqueda;
    }
 
    public ArrayList<Integer> obtenerElementosOrdenadosAscendentemente() {
        ArrayList<Integer> elementosOrdenados = new ArrayList<>();
        recorrerAscendente(raiz, elementosOrdenados);
        return elementosOrdenados;
    }
 
    public ArrayList<Integer> obtenerElementosOrdenadosDescendentemente() {
        ArrayList<Integer> elementosOrdenados = new ArrayList<>();
        recorrerDescendente(raiz, elementosOrdenados);
        return elementosOrdenados;
    }
 
    private void recorrerAscendente(Nodo<Integer> nodo, ArrayList<Integer> elementos) {
        if (nodo != null) {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                elementos.add(nodo.getValue());
            } else {
                recorrerAscendente(nodo.getIzq(), elementos);
                elementos.add(nodo.getValue());
                recorrerAscendente(nodo.getDer(), elementos);
            }
        }
 
    }
 
    private void recorrerDescendente(Nodo<Integer> nodo, ArrayList<Integer> elementos) {
        if (nodo != null) {
            if (nodo.getIzq() == null && nodo.getDer() == null) {
                elementos.add(nodo.getValue());
            } else {
                recorrerDescendente(nodo.getDer(), elementos);
                elementos.add(nodo.getValue());
                recorrerDescendente(nodo.getIzq(), elementos);
            }
        }
 
    }
 
}