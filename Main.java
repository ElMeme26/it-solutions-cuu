import javax.swing.JOptionPane;

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
        // datosBase(); 
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
        JOptionPane.showMessageDialog(null, "");
    }
    
    private static void menuRH() {
         JOptionPane.showMessageDialog(null, "");
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
}