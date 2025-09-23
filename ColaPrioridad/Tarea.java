package ColaPrioridad;

import java.time.LocalDate;

public class Tarea {
 
    private String descripcion;
    private int prioridad;
    private LocalDate fechaEntrega;
 
    public Tarea(String descripcion, int prioridad, LocalDate fechaEntrega) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaEntrega = fechaEntrega;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
    public int getPrioridad() {
        return prioridad;
    }
 
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
 
    @Override
    public String toString() {
        return "Tarea: '" + descripcion + '\'' +
               " (Prioridad: " + prioridad +
               ", Fecha Límite: " + fechaEntrega + ')';
    }
}
