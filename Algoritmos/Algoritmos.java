package Algoritmos;

import ColaPrioridad.Tarea;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Algoritmos {

    public List<Tarea> bubbleSort(List<Tarea> tareas, Comparator<Tarea> comparador) {
        List<Tarea> listaOrdenada = new ArrayList<>(tareas);
        int n = listaOrdenada.size();
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparador.compare(listaOrdenada.get(j), listaOrdenada.get(j + 1)) > 0) {
                    Tarea temp = listaOrdenada.get(j);
                    listaOrdenada.set(j, listaOrdenada.get(j + 1));
                    listaOrdenada.set(j + 1, temp);
                }
            }
        }
        return listaOrdenada;
    }

    public Tarea busquedaBinaria(List<Tarea> tareasOrdenadas, int id) {
        int bajo = 0;
        int alto = tareasOrdenadas.size() - 1;

        while (bajo <= alto) {
            int medio = bajo + (alto - bajo) / 2;
            Tarea tareaMedio = tareasOrdenadas.get(medio);

            if (tareaMedio.getId() == id) {
                return tareaMedio;
            }

            if (tareaMedio.getId() < id) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }
        return null;
    }
}