package clases;

//Clase que representa un acabado opcional que se puede agregar a un departamento
public class AcabadoOpcional {
    private String nombre;
    private String descripcion;
    private double precioAdicional;

    //Constructor: inicializa los datos del acabado opcional
    public AcabadoOpcional(String nombre, String descripcion, double precioAdicional) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioAdicional = precioAdicional;
    }

    //Retorna el nombre del acabado
    public String getNombre() { return nombre; }
    //Establece el nombre del acabado
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Retorna la descripcion del acabado
    public String getDescripcion() { return descripcion; }
    //Establece la descripcion del acabado
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    ///Retorna el precio adicional del acabado
    public double getPrecioAdicional() { return precioAdicional; }
    //Establece el precio adicional del acabado
    public void setPrecioAdicional(double precioAdicional) { this.precioAdicional = precioAdicional; }
}
