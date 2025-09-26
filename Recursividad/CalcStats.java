package Recursividad;

import ColaPrioridad.Tarea;
import java.util.List;

public class CalcStats {
    
    public int calcularTiempoTotal(List<Tarea> tareas) {
        // Caso base, si la lista esta vacía
        if (tareas.isEmpty()) {
            return 0;
        } else {
            Tarea primeraTarea = tareas.get(0);
            List<Tarea> tareasRestantes = tareas.subList(1, tareas.size());
            return primeraTarea.getHorasEstimadas() + calcularTiempoTotal(tareasRestantes);
        }
    }
}
