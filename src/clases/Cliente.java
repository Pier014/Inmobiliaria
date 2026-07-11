package clases;

public class Cliente extends Persona {
    private String fechaNacimiento;
    private String estadoCivil;
    private String ocupacion;
    private double ingresosMensuales;
    private String telefono;
    private String correo;

    public Cliente(String dni, String nombres, String apellidos, String fechaNacimiento,
                   String estadoCivil, String ocupacion, double ingresosMensuales,
                   String telefono, String correo) {
        super(dni, nombres, apellidos);
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.ocupacion = ocupacion;
        this.ingresosMensuales = ingresosMensuales;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getOcupacion() { return ocupacion; }
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    public double getIngresosMensuales() { return ingresosMensuales; }
    public void setIngresosMensuales(double ingresosMensuales) { this.ingresosMensuales = ingresosMensuales; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - " + ocupacion;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (" + DNI + ")";
    }
}
