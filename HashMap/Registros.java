package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ArbolBinario.Empleado;
import ColaPrioridad.Tarea;

public class Registros {

    // La llave es el id del empleado, y el value es el Empleado
    private HashMap<Integer, Empleado> registroEmpleados;

    // La llave es el id de la tarea, y el value es la tarea
    private HashMap<Integer, Tarea> registroTareas;

    public Registros() {
        this.registroEmpleados = new HashMap<>();
        this.registroTareas = new HashMap<>();
    }

    // Gestion de empleados
    public void agregarEmpleado(Empleado empleado) {
        registroEmpleados.put(empleado.getId(), empleado);
    }

    public Empleado buscarEmpleado(int id) {
        return registroEmpleados.get(id);
    }

    public void eliminarEmpleado(int id) {
        registroEmpleados.remove(id);
    }

    // Gestion de tareas
    public void agregarTarea(Tarea tarea) {
        registroTareas.put(tarea.getId(), tarea);
    }

    public Tarea buscarTarea(int id) {
        return registroTareas.get(id);
    }

    public void eliminarTarea(int id) {
        registroTareas.remove(id);
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return new ArrayList<>(registroTareas.values());
    }

    public void mostrarEmpleados() {
        System.out.println("=== Registro de empleados ===");

        for (Empleado empleado : registroEmpleados.values()) {
            System.out.println(empleado);
        }
        System.out.println("=====================================");
    }

}
