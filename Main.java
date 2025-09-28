import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Algoritmos.Algoritmos;
import ArbolBinario.ArbolBinario;
import ArbolBinario.Empleado;
import ColaPrioridad.ColaConPrioridad;
import ColaPrioridad.Tarea;
import DivideYVenceras.Asignacion;
import DivideYVenceras.DistribuidorTareas;
import DivideYVenceras.Proyecto;
import Grafos.Grafos;
import HashMap.Registros;
import LinkedList.LinkedList;
import LinkedList.Queue;
import LinkedList.Stack;
import Recursividad.CalcStats;


public class Main {

    private static final ArbolBinario arbolEmpleados = new ArbolBinario();
    private static final Registros registrosGlobales = new Registros();
    private static final ColaConPrioridad colaDeTareasPriorizadas = new ColaConPrioridad();
    private static final Stack pilaIncidentesCriticos = new Stack();
    private static final Queue colaTareasSimples = new Queue();
    private static final LinkedList listaTareasDepartamento = new LinkedList();
    private static final Algoritmos algoritmos = new Algoritmos();
    
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
                      "2. Crear Tarea Prioritaria\n" +
                      "3. Ver Reporte de Tareas\n" +
                      "4. Planificación estratégica\n" +
                      "5. Volver";
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Bienvenido, Director/a", JOptionPane.PLAIN_MESSAGE);
            if(opcionStr == null) break;
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch(opcion){
                    case 1: menuRH(); break;
                    case 2: crearTareaPriorizada(); break;
                    case 3: generarReporteTareas(); break;
                    case 4: planificacionEstrategica(); break;
                    case 5: return;
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
                "1. Crear Tarea Prioritaria\n" +
                "2. Planificar Dependencias\n" +
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
        String menu = "Panel de Análisis de Datos\n\n" +
                "1. Generar Reporte de Tareas\n" +
                "2. Calcular Carga de Trabajo\n" +
                "3. Búsqueda Rápida de Tarea por ID\n" +
                "4. Volver";
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Business Intelligence", JOptionPane.PLAIN_MESSAGE);
            if (opcionStr == null) break;
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch (opcion) {
                    case 1: generarReporteTareas(); break;
                    case 2: calcularCargaTrabajo(); break;
                    case 3: busquedaRapidaTarea(); break;
                    case 4: return;
                    default: mostrarError("Opción no válida.");
                }
            } catch (NumberFormatException e) { mostrarError("Por favor, ingrese un número válido."); }
        }
    }

    private static void menuDesarrollador() {
        String menu = "Portal del Desarrollador\n\n" +
                "1. Agregar Tarea Simple\n" +
                "2. Ver Próxima Tarea Programada\n" +
                "3. Consultar Tareas de mi Departamento\n" +
                "4. Volver";
        while (true) {
            String opcionStr = JOptionPane.showInputDialog(null, menu, "Mis Tareas", JOptionPane.PLAIN_MESSAGE);
            if (opcionStr == null) break;
            try {
                int opcion = Integer.parseInt(opcionStr);
                switch (opcion) {
                    case 1: agregarTarea(); break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Próxima tarea simple programada:\n" + colaTareasSimples.peek());
                        break;
                    case 3:
                        String depto = solicitarTexto("Ingrese su departamento:");
                        if (depto != null && !depto.trim().isEmpty()) {
                             String tareasDepto = listaTareasDepartamento.findByDepartment(depto);
                             mostrador("Tareas del Departamento", tareasDepto);
                        }
                        break;
                    case 4: return;
                    default: mostrarError("Opción no válida.");
                }
            } catch (NumberFormatException e) { mostrarError("Por favor, ingrese un número válido."); }
        }
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

    private static void planificacionEstrategica() {
        try {
            Proyecto p = new Proyecto("Lanzamiento Nuevo Software v2.0");
            List<Tarea> tareasProyecto = registrosGlobales.obtenerTodasLasTareas();
            if (tareasProyecto.isEmpty()) {
                mostrarError("No hay tareas para crear un proyecto.");
                return;
            }
            tareasProyecto.forEach(p::agregarTarea);
            List<Empleado> empleadosDisponibles = new ArrayList<>();
            empleadosDisponibles.add(registrosGlobales.buscarEmpleado(1));
            empleadosDisponibles.add(registrosGlobales.buscarEmpleado(2));
            empleadosDisponibles.add(registrosGlobales.buscarEmpleado(3));
            empleadosDisponibles.add(registrosGlobales.buscarEmpleado(4));

            DistribuidorTareas distribuidor = new DistribuidorTareas();
            List<Asignacion> asignaciones = distribuidor.distribuir(p, empleadosDisponibles);
            StringBuilder resultado = new StringBuilder("Distribución del Proyecto '" + p.getNombre() + "':\n\n");
            for(Asignacion a : asignaciones) {
                resultado.append(">> Empleado: ").append(a.getEmpleado().getNombre()).append("\n");
                a.getTareasAsignadas().forEach(t -> resultado.append("   - ").append(t.getDescripcion()).append("\n"));
                resultado.append("\n");
            }
            mostrador("Plan de Distribución", resultado.toString());
        } catch (Exception e) {
            mostrarError("Error al planificar. Verifique que existan tareas y empleados suficientes.");
        }
    }

    private static void generarReporteTareas() {
        String[] criterios = {"Por Prioridad", "Por Fecha de Entrega", "Por ID"};
        int sel = JOptionPane.showOptionDialog(null, "Ordenar reporte por:", "Generar Reporte",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, criterios, criterios[0]);
        if (sel == -1) return;
        List<Tarea> lista = registrosGlobales.obtenerTodasLasTareas();
        if (lista.isEmpty()) {
            mostrarError("No hay tareas para generar un reporte.");
            return;
        }
        Comparator<Tarea> comp = null;
        switch (sel) {
            case 0: comp = Comparator.comparingInt(Tarea::getPrioridad).thenComparing(Tarea::getFechaEntrega); break;
            case 1: comp = Comparator.comparing(Tarea::getFechaEntrega); break;
            case 2: comp = Comparator.comparingInt(Tarea::getId); break;
        }
        if (comp != null) {
            List<Tarea> tareasOrdenadas = algoritmos.bubbleSort(lista, comp);
            StringBuilder reporte = new StringBuilder("--- Reporte de Tareas ---\n\n");
            tareasOrdenadas.forEach(t -> reporte.append(t.toString()).append("\n"));
            mostrador("Reporte Generado", reporte.toString());
        }
    }

    private static void agregarTarea() {
        String[] tipos = {"Incidente Crítico", "Tarea Simple", "Tarea Departamental"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de tarea a agregar:",
                "Agregar Tarea ", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        if (seleccion == -1) return;

        switch (seleccion) {
            case 0:
                String incidente = solicitarTexto("Descripción del incidente crítico:");
                if (incidente != null && !incidente.trim().isEmpty()) {
                    pilaIncidentesCriticos.push(incidente);
                    JOptionPane.showMessageDialog(null, "Incidente agregado a la Pila.");
                }
                break;
            case 1:
                String tareaSimple = solicitarTexto("Descripción de la tarea programada:");
                if (tareaSimple != null && !tareaSimple.trim().isEmpty()) {
                    colaTareasSimples.enqueue(tareaSimple);
                    JOptionPane.showMessageDialog(null, "Tarea agregada a la Cola.");
                }
                break;
            case 2:
                String depto = solicitarTexto("Departamento:");
                String tareaDepto = solicitarTexto("Descripción de la tarea:");
                if (depto != null && !depto.trim().isEmpty() && tareaDepto != null && !tareaDepto.trim().isEmpty()) {
                    listaTareasDepartamento.insertFirst(depto.trim() + ": " + tareaDepto.trim());
                    JOptionPane.showMessageDialog(null, "Tarea departamental agregada a la Lista.");
                }
                break;
        }
    }

    private static void calcularCargaTrabajo() {
        List<Tarea> todas = registrosGlobales.obtenerTodasLasTareas();
        if (todas.isEmpty()) {
            mostrarError("No hay tareas para calcular.");
            return;
        }
        CalcStats calc = new CalcStats();
        int totalHoras = calc.calcularTiempoTotal(todas);
        JOptionPane.showMessageDialog(null, "La carga de trabajo total es: " + totalHoras + " horas.");
    }
    
    private static void busquedaRapidaTarea() {
        try {
            int id = solicitarNumero("Ingrese el ID de la tarea a buscar:");
            if(id == -1) return;
            List<Tarea> ordenadas = algoritmos.bubbleSort(registrosGlobales.obtenerTodasLasTareas(), Comparator.comparingInt(Tarea::getId));
            Tarea encontrada = algoritmos.busquedaBinaria(ordenadas, id);
            if (encontrada != null) {
                JOptionPane.showMessageDialog(null, "Tarea encontrada:\n" + encontrada);
            } else {
                mostrarError("No se encontró ninguna tarea con ese ID.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser un número entero.");
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

        Tarea t1 = new Tarea("Diseñar clases", 1, LocalDate.of(2025, 10, 1), 5);
        Tarea t2 = new Tarea("Implementar lógica de clases", 1, LocalDate.of(2025, 10, 10), 10);
        Tarea t3 = new Tarea("Crear el main", 2, LocalDate.of(2025, 10, 20), 20);
        Tarea t4 = new Tarea("Verificar que todo funcione bien", 3, LocalDate.of(2025, 10, 25), 2);
        colaDeTareasPriorizadas.push(t1);
        colaDeTareasPriorizadas.push(t2);
        colaDeTareasPriorizadas.push(t3);
        colaDeTareasPriorizadas.push(t4);
        
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