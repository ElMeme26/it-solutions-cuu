package DivideYVenceras;

import java.util.List;
import java.util.ArrayList;
import ArbolBinario.Empleado;
import ColaPrioridad.Tarea;

public class Asignacion {
    
    private Empleado empleado;
    private List<Tarea> tareasAsignadas;

    public Asignacion(Empleado empleado) {
        this.empleado = empleado;
        this.tareasAsignadas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        this.tareasAsignadas.add(tarea);
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public List<Tarea> getTareasAsignadas() {
        return tareasAsignadas;
    }
}
