import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ArbolBinario.ArbolBinario;
import ArbolBinario.Empleado;
import ColaPrioridad.ColaConPrioridad;
import ColaPrioridad.Tarea;
import Grafos.Grafos;
import HashMap.Registros;
import LinkedList.LinkedList;
import LinkedList.Queue;
import LinkedList.Stack;


public class Main {

    private static final ArbolBinario arbolEmpleados = new ArbolBinario();
    private static final Registros registrosGlobales = new Registros();
    private static final ColaConPrioridad colaDeTareasPriorizadas = new ColaConPrioridad();
    private static final Stack pilaIncidentesCriticos = new Stack();
    private static final Queue colaTareasSimples = new Queue();
    private static final LinkedList listaTareasDepartamento = new LinkedList();
    
    public static void main(String[] args) {
        datosBase(); 
        loopMain();
    }

    private static void loopMain() {
        while (true) {
            String[] roles = {"Director General", "Recurso Humanos", "Líder de Proyecto", "Analista de Datos", "Desarrollador", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Bienvenido a UltiTask\n\nPor favor, inicie sesión:",
                    "Inicio de Sesión", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, roles, roles[0]);

            switch (seleccion) {
                case 0: menuDirectorGeneral(); break;
                case 1: menuRH(); break;
                case 2: menuLiderDeProyecto(); break;
                case 3: menuAnalista(); break;
                case 4: menuDesarrollador(); break;
                case 5: case -1:
                    JOptionPane.showMessageDialog(null, "Gracias por usar UltiTask.");
                    return;
            }
        }
    }

    private static void menuDirectorGeneral() {
        String menu = "Panel de Dirección General\n\n" +
                      "1. Módulo de RH\n" +
                      "2. Crear Tarea Priorizada\n" +
                      "3. Volver";
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Bienvenido, Director/a", JOptionPane.PLAIN_MESSAGE);
            if(opcionStr == null) break;
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch(opcion){
                    case 1: menuRH(); break;
                    case 2: crearTareaPriorizada(); break;
                    case 3: return;
                    default: mostrarError("Opción no válida.");
                }
            } catch (NumberFormatException e) { mostrarError("Por favor, ingrese un número válido."); }
        }
    }
    
    private static void menuRH() {
        String menu = "Panel de Recursos Humanos\n\n" +
                "1. Agregar nuevo empleado\n" +
                "2. Desvincular empleado\n" +
                "3. Buscar empleado por ID\n" +
                "4. Mostrar todo el personal\n" +
                "5. Volver";
        
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Módulo de RH", JOptionPane.PLAIN_MESSAGE);
            if (opcionStr == null) break; // Si el usuario cancela
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch (opcion) {
                    case 1: agregarEmpleado(); break;
                    case 2: eliminarEmpleado(); break;
                    case 3: buscarEmpleado(); break;
                    case 4: 
                        String listadoEmpleados = arbolEmpleados.mostrar();
                        mostrador("Plantilla de Empleados", listadoEmpleados);
                        break;
                    case 5: return;
                    default: mostrarError("Opción no válida.");
                }
            } catch (NumberFormatException e) { mostrarError("Por favor, ingrese un número válido."); }
        }
    }

    private static void menuLiderDeProyecto() {
        String menu = "Panel de Líder de Proyecto\n\n" +
                "1. Crear Tarea Priorizada\n" +
                "2. Planificar Dependencias (Grafos)\n" +
                "3. Consultar Empleados\n" +
                "4. Volver";
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Gestión de Proyectos", JOptionPane.PLAIN_MESSAGE);
            if (opcionStr == null) break;
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch (opcion) {
                    case 1: crearTareaPriorizada(); break;
                    case 2: planificarProyectoConDependencias(); break;
                    case 3: 
                        String listadoEmpleados = arbolEmpleados.mostrar();
                        mostrador("Empleados Disponibles", listadoEmpleados);
                        break;
                    case 4: return;
                    default: mostrarError("Opción no válida.");
                }
            } catch (NumberFormatException e) { mostrarError("Por favor, ingrese un número válido."); }
        }
    }
    
    private static void menuAnalista() {
        JOptionPane.showMessageDialog(null, "");
    }

    private static void menuDesarrollador() {
        JOptionPane.showMessageDialog(null, "");
    }

    // Funciones RH

    private static void agregarEmpleado() {
        try {
            int id = solicitarNumero("ID del nuevo empleado:");
            if (id == -1) return;
            String nombre = solicitarTexto("Nombre completo:");
            if (nombre == null) return;
            String depto = solicitarTexto("Departamento:");
            if (depto == null) return;

            if (!nombre.trim().isEmpty() && !depto.trim().isEmpty()) {
                Empleado nuevo = new Empleado(id, nombre, depto);
                arbolEmpleados.insertar(nuevo);
                registrosGlobales.agregarEmpleado(nuevo);
                JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
            } else {
                mostrarError("Nombre y departamento no pueden estar vacíos.");
            }
        } catch (NumberFormatException e) { mostrarError("El ID debe ser un número entero."); }
    }

    private static void eliminarEmpleado() {
        try {
            int id = solicitarNumero("ID del empleado a eliminar:");
            if (id == -1) return;
            arbolEmpleados.eliminar(id);
            registrosGlobales.eliminarEmpleado(id);
            JOptionPane.showMessageDialog(null, "Operación de eliminación completada.");
        } catch (NumberFormatException e) { mostrarError("El ID debe ser un número entero."); }
    }

    private static void buscarEmpleado() {
         try {
            int id = solicitarNumero("ID del empleado a buscar:");
            if (id == -1) return;
            Empleado encontrado = registrosGlobales.buscarEmpleado(id);
            if (encontrado != null) {
                JOptionPane.showMessageDialog(null, "Empleado encontrado:\n" + encontrado);
            } else {
                mostrarError("Empleado con ID " + id + " no encontrado.");
            }
        } catch (NumberFormatException e) { mostrarError("El ID debe ser un número entero."); }
    }

    // Funciones Lider de Proyectos

    private static void crearTareaPriorizada() {
        try {
            String desc = solicitarTexto("Descripción de la tarea:");
            if(desc == null) return;
            int prio = solicitarNumero("Prioridad (1=Alta, 4=Baja):");
            if(prio == -1) return;
            LocalDate fecha = solicitarFecha("Fecha límite (AAAA-MM-DD):");
            if(fecha == null) return;
            int horas = solicitarNumero("Horas estimadas:");
            if(horas == -1) return;

            if (!desc.trim().isEmpty()) {
                Tarea nueva = new Tarea(desc, prio, fecha, horas);
                colaDeTareasPriorizadas.push(nueva);
                registrosGlobales.agregarTarea(nueva);
                JOptionPane.showMessageDialog(null, "Tarea priorizada creada con éxito:\n" + nueva);
            }
        } catch (NumberFormatException e) {
            mostrarError("Error en el formato de número.");
        } catch (DateTimeParseException e) {
            mostrarError("Formato de fecha incorrecto. Use AAAA-MM-DD.");
        }
    }

    private static void planificarProyectoConDependencias() {
        Grafos proyecto = new Grafos();
        List<Tarea> tareasDelProyecto = new ArrayList<>(registrosGlobales.obtenerTodasLasTareas());
        
        if (tareasDelProyecto.isEmpty()) {
            mostrarError("No hay tareas creadas para iniciar la planificación.");
            return;
        }
        
        tareasDelProyecto.forEach(proyecto::agregarTarea);
        
        while (true) {
            StringBuilder mensaje = new StringBuilder();
            List<Tarea> rutaActual;
            
            try {
                rutaActual = proyecto.obtenerRutaCritica();
            } catch (Exception e) {
                rutaActual = new ArrayList<>();
            }
            
            
            mensaje.append("TAREAS DISPONIBLES (AÚN SIN DEPENDENCIAS):\n");
            List<Tarea> finalRutaActual = rutaActual;
            tareasDelProyecto.stream()
                .filter(t -> !finalRutaActual.contains(t))
                .forEach(t -> mensaje.append("- ").append(t.getDescripcion()).append(" (ID: ").append(t.getId()).append(")\n"));

            // Plan de ejecución actual
            mensaje.append("\nPLAN DE EJECUCIÓN ACTUAL:\n");
            if (rutaActual.isEmpty()) {
                 mensaje.append(proyecto.obtenerRutaCritica().isEmpty() ? "¡PROYECTO COMPLETO!" : "Aún no hay un plan de ejecución claro.\n");
            } else {
                for (int i = 0; i < rutaActual.size(); i++) {
                    mensaje.append(i + 1).append(". ").append(rutaActual.get(i).getDescripcion()).append(" --ID: ").append(rutaActual.get(i).getId()).append("\n");
                }
            }


            String[] opciones = {"Añadir Dependencia", "Completar Tarea Actual", "Terminar Planificación"};
            int seleccion = JOptionPane.showOptionDialog(null, new JTextArea(mensaje.toString()),
                "Planificador Interactivo de Dependencias", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == 2 || seleccion == -1) { // Terminar o cerrar ventana
                break; 
            }

            try {
                if (seleccion == 0) { // Añadir dependencia
                    int idPrevia = solicitarNumero("ID de la tarea que va PRIMERO:");
                    if(idPrevia == -1) continue;
                    int idDependiente = solicitarNumero("ID de la tarea que DEPENDE de la anterior:");
                    if(idDependiente == -1) continue;

                    Tarea previa = registrosGlobales.buscarTarea(idPrevia);
                    Tarea dependiente = registrosGlobales.buscarTarea(idDependiente);
                    
                    if (previa != null && dependiente != null) {
                        proyecto.agregarDependencia(previa, dependiente);
                        JOptionPane.showMessageDialog(null, "Dependencia agregada. El plan se ha actualizado.");
                    } else {
                        mostrarError("Uno o ambos IDs de tarea no son válidos.");
                    }

                } else if (seleccion == 1) { // Completar tarea
                    List<Tarea> rutaParaCompletar = proyecto.obtenerRutaCritica();
                    if(rutaParaCompletar.isEmpty()){
                        mostrarError("¡Felicidades! Todas las tareas del proyecto ya han sido completadas.");
                        continue;
                    }
                    
                    int idCompletar = solicitarNumero("Ingrese el ID de la tarea que desea completar:");
                    if(idCompletar == -1) continue;


                    if (idCompletar == rutaParaCompletar.get(0).getId()) {
                        Tarea completada = registrosGlobales.buscarTarea(idCompletar);
                        proyecto.eliminarTarea(completada);
                        tareasDelProyecto.remove(completada);
                        JOptionPane.showMessageDialog(null, "¡Tarea \"" + completada.getDescripcion() + "\" completada con éxito!");
                    } else {
                        mostrarError("No se puede completar esta tarea.\nDebe completar primero: \"" + rutaParaCompletar.get(0).getDescripcion() + "\" (ID: " + rutaParaCompletar.get(0).getId() + ")");
                    }
                }
            } catch (NumberFormatException e) {
                mostrarError("El ID debe ser un número entero.");
            } catch (Exception e) {
                mostrarError("Error en la operación: " + e.getMessage());
            }
        }
    }

    // Datos base

    private static void datosBase() {
        Empleado e1 = new Empleado(1, "Leonardo Pereda", "Dirección");
        Empleado e2 = new Empleado(2, "Zaif Castillo", "Líder de Proyecto");
        Empleado e3 = new Empleado(3, "Carlos Coronado", "Desarrollo");
        Empleado e4 = new Empleado(4, "Alexis Espino", "Análisis de Datos");
        Empleado e5 = new Empleado(5, "Ricardo Pereda", "Recursos Humanos");
        
        arbolEmpleados.insertar(e1);
        arbolEmpleados.insertar(e2);
        arbolEmpleados.insertar(e3);
        arbolEmpleados.insertar(e4);
        arbolEmpleados.insertar(e5);

        registrosGlobales.agregarEmpleado(e1);
        registrosGlobales.agregarEmpleado(e2);
        registrosGlobales.agregarEmpleado(e3);
        registrosGlobales.agregarEmpleado(e4);
        registrosGlobales.agregarEmpleado(e5);

        Tarea t1 = new Tarea("Diseñar BD", 1, LocalDate.of(2025, 10, 1), 8);
        Tarea t2 = new Tarea("Desarrollar API", 1, LocalDate.of(2025, 10, 10), 40);
        Tarea t3 = new Tarea("Crear UI", 2, LocalDate.of(2025, 10, 20), 32);
        Tarea t4 = new Tarea("Realizar Pruebas", 2, LocalDate.of(2025, 10, 25), 16);
        registrosGlobales.agregarTarea(t1);
        registrosGlobales.agregarTarea(t2);
        registrosGlobales.agregarTarea(t3);
        registrosGlobales.agregarTarea(t4);

        pilaIncidentesCriticos.push("Servidor de correos no responde");
        colaTareasSimples.enqueue("Revisión semanal de respaldos");
        listaTareasDepartamento.insertFirst("Desarrollo: Arreglar bug en main de Java");
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private static void mostrador(String titulo, String mensaje) {
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setRows(15);
        textArea.setColumns(50);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, new javax.swing.JScrollPane(textArea), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    private static String solicitarTexto(String mensaje) {
        return JOptionPane.showInputDialog(null, mensaje);
    }

    private static int solicitarNumero(String mensaje) throws NumberFormatException {
        String input = JOptionPane.showInputDialog(null, mensaje);
        if (input == null) return -1; // Manejo de cancelación
        return Integer.parseInt(input);
    }

    private static LocalDate solicitarFecha(String mensaje) throws DateTimeParseException {
        String input = JOptionPane.showInputDialog(null, mensaje);
        if (input == null) return null; // Manejo de cancelación
        return LocalDate.parse(input);
    }
}