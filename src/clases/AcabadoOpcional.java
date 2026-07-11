package clases;

public class AcabadoOpcional {
    private String nombre;
    private String descripcion;
    private double precioAdicional;

    public AcabadoOpcional(String nombre, String descripcion, double precioAdicional) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAdicional = precioAdicional;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioAdicional() { return precioAdicional; }
    public void setPrecioAdicional(double precioAdicional) { this.precioAdicional = precioAdicional; }
}
