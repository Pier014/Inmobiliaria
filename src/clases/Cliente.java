package clases;

//Clase hija de Persona. Representa a un cliente con datos personales y de contacto
public class Cliente extends Persona {
    private String fechaNacimiento;
    private String estadoCivil;
    private String ocupacion;
    private double ingresosMensuales;
    private String telefono;
    private String correo;

    //Constructor: inicializa todos los datos del cliente
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

    //Retorna la fecha de nacimiento del cliente
    public String getFechaNacimiento() { return fechaNacimiento; }
    //Establece la fecha de nacimiento del cliente
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    //Retorna el estado civil del cliente
    public String getEstadoCivil() { return estadoCivil; }
    //Establece el estado civil del cliente
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    //Retorna la ocupacion del cliente
    public String getOcupacion() { return ocupacion; }
    //Establece la ocupacion del cliente
    public void setOcupacion(String ocupacion) { this.ocupacion = ocupacion; }

    //Retorna los ingresos mensuales del cliente
    public double getIngresosMensuales() { return ingresosMensuales; }
    //Establece los ingresos mensuales del cliente
    public void setIngresosMensuales(double ingresosMensuales) { this.ingresosMensuales = ingresosMensuales; }

    //Retorna el telefono del cliente
    public String getTelefono() { return telefono; }
    //Establece el telefono del cliente
    public void setTelefono(String telefono) { this.telefono = telefono; }

    //Retorna el correo electronico del cliente
    public String getCorreo() { return correo; }
    //Establece el correo electronico del cliente
    public void setCorreo(String correo) { this.correo = correo; }

    //Retorna una descripcion del cliente con su nombre y ocupacion
    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - " + ocupacion;
    }

    //Retorna la representacion en cadena del cliente
    @Override
    public String toString() {
        return getNombreCompleto() + " (" + DNI + ")";
    }
}
