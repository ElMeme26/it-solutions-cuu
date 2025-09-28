import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.ArrayList;

import ArbolBinario.ArbolBinario;
import ArbolBinario.Empleado;
import ColaPrioridad.ColaConPrioridad;
import ColaPrioridad.Tarea;
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
        String[] opciones = {"Módulo de RH", "Volver"};
        int seleccion = JOptionPane.showOptionDialog(null, "Panel de Dirección General", "Bienvenido, Director/a", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        
        if (seleccion == 0) {
            menuRH();
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
        JOptionPane.showMessageDialog(null, "");
    }
    
    private static void menuAnalista() {
        JOptionPane.showMessageDialog(null, "");
    }

    private static void menuDesarrollador() {
        JOptionPane.showMessageDialog(null, "");
    }

    private static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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


        pilaIncidentesCriticos.push("Servidor de correos no responde");
        colaTareasSimples.enqueue("Revisión semanal de respaldos");
        listaTareasDepartamento.insertFirst("Desarrollo: Arreglar bug en main de Java");
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
}