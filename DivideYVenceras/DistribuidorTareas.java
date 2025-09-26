package DivideYVenceras;

import java.util.ArrayList;
import java.util.List;

import ArbolBinario.Empleado;
import ColaPrioridad.Tarea;

public class DistribuidorTareas {
    
    public List<Asignacion> distribuir(Proyecto proyecto, List<Empleado> empleados) {
        List<Tarea> tareasTotales = proyecto.getTareas();
        List<Asignacion> listaDeAsignaciones = new ArrayList<>();

        for (Empleado empleado : empleados) {
            listaDeAsignaciones.add(new Asignacion(empleado));
        }

        distribuirRecursivo(tareasTotales, listaDeAsignaciones);

        return listaDeAsignaciones;
    }

    private void distribuirRecursivo(List<Tarea> tareas, List<Asignacion> asignaciones) {
        // Caso base, si no hay tareas que repartir
        if (tareas.isEmpty() || asignaciones.isEmpty()) {
            return;
        }

        Tarea tareaActual = tareas.get(0);
        Asignacion asignacionActual = asignaciones.get(0);

        List<Tarea> tareasRestantes = tareas.subList(1, tareas.size());

        List<Asignacion> asignacionesSiguientes = new ArrayList<>(asignaciones);
        asignacionesSiguientes.add(asignacionesSiguientes.remove(0));

        asignacionActual.agregarTarea(tareaActual);

        distribuirRecursivo(tareasRestantes, asignacionesSiguientes);
    }
}
