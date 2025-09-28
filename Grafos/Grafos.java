package Grafos;

import ColaPrioridad.Tarea;
import java.util.*;

public class Grafos {

    private Map<Tarea, List<Tarea>> mapaAdyacencia;

    public Grafos() {
        this.mapaAdyacencia = new HashMap<>();
    }

    public void agregarTarea(Tarea tarea) {
        mapaAdyacencia.putIfAbsent(tarea, new ArrayList<>());
    }

    public void agregarDependencia(Tarea tareaPrevia, Tarea tareaDependiente) {
        mapaAdyacencia.putIfAbsent(tareaPrevia, new ArrayList<>());
        mapaAdyacencia.putIfAbsent(tareaDependiente, new ArrayList<>());

        mapaAdyacencia.get(tareaPrevia).add(tareaDependiente);
    }

    public List<Tarea> obtenerRutaCritica() {
        Map<Tarea, Integer> gradosEntrada = new HashMap<>();
        Queue<Tarea> cola = new LinkedList<>();
        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : mapaAdyacencia.keySet()) {
            gradosEntrada.put(tarea, 0);
        }
        for (Tarea tarea : mapaAdyacencia.keySet()) {
            for (Tarea dependiente : mapaAdyacencia.get(tarea)) {
                gradosEntrada.put(dependiente, gradosEntrada.get(dependiente) + 1);
            }
        }

        for (Tarea tarea : gradosEntrada.keySet()) {
            if (gradosEntrada.get(tarea) == 0) {
                cola.add(tarea);
            }
        }

        while (!cola.isEmpty()) {
            Tarea actual = cola.poll();
            resultado.add(actual);

            for (Tarea dependiente : mapaAdyacencia.get(actual)) {
                gradosEntrada.put(dependiente, gradosEntrada.get(dependiente) - 1);
                if (gradosEntrada.get(dependiente) == 0) {
                    cola.add(dependiente);
                }
            }
        }

        if (resultado.size() != mapaAdyacencia.size()) {
            throw new RuntimeException("Error: El proyecto tiene una dependencia circular y no puede completarse.");
        }

        return resultado;
    }

    public void eliminarTarea(Tarea tarea) {
        // Eliminar la tarea como vértice principal (de las llaves del mapa)
        mapaAdyacencia.remove(tarea);

        // Eliminar la tarea de todas las listas de dependencias de otras tareas
        for (List<Tarea> dependientes : mapaAdyacencia.values()) {
            dependientes.remove(tarea);
        }
    }
}