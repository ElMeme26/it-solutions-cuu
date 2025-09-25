package ArbolBinario;

public class Empleado {
    
    private int id;
    private String nombre;
    private String departamento;

    public Empleado(int id, String nombre, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public String toString() {
        return "Empleado [ID=" + id + ", Nombre='" + nombre + "', Depto='" + departamento + "']";
    }
}
