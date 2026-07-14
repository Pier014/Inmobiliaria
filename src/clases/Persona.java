package clases;

//Clase abstracta padre que representa a una persona con datos basicos 
public abstract class Persona {
    protected String DNI;
    protected String nombres;
    protected String apellidos;

    //Constructor: inicializa los datos basicos de la persona 
    public Persona(String DNI, String nombres, String apellidos) {
        this.DNI = DNI;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    //Retorna los apellidos de la persona 
    public String getApellidos() {
        return apellidos;
    }

    //Establece los apellidos de la persona 
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    //Retorna los nombres de la persona 
    public String getNombres() {
        return nombres;
    }

    //Establece los nombres de la persona 
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    //Retorna el DNI de la persona 
    public String getDNI() {
        return DNI;
    }

    //Establece el DNI de la persona 
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    //Retorna el nombre completo (nombres + apellidos) 
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    //Metodo abstracto que debe implementar cada subclase para obtener una descripcion 
    public abstract String getDescripcion();
}
