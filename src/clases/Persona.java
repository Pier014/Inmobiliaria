package clases;

public abstract class Persona {
    protected String DNI;
    protected String nombres;
    protected String apellidos;

    public Persona(String DNI, String nombres, String apellidos) {
        this.DNI = DNI;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public abstract String getDescripcion();
}
