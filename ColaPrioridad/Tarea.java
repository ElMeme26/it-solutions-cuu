package ColaPrioridad;

import java.time.LocalDate;

public class Tarea {
 
    private static int contadorId = 0;
    private final int id;
    private String descripcion;
    private int prioridad;
    private LocalDate fechaEntrega;
    private int horasEstimadas;
 
    public Tarea(String descripcion, int prioridad, LocalDate fechaEntrega, int horasEstimadas) {
        this.id = ++contadorId;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaEntrega = fechaEntrega;
        this.horasEstimadas = horasEstimadas;
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

    public int getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(int horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public int getId() {
        return id;
    }
 
    @Override
    public String toString() {
        return "Tarea ID-'" + id + ": " + descripcion + '\'' +
               " (Prioridad: " + prioridad +
               ", Fecha Límite: " + fechaEntrega + 
               ", Horas: " + horasEstimadas + ')';
    }
}
