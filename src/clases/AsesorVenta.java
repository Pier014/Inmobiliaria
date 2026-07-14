package clases;

//Clase hija de Empleado. Representa a un asesor de ventas
public class AsesorVenta extends Empleado {
    //Constructor: inicializa los datos del asesor de ventas usando el constructor de Empleado
    public AsesorVenta(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    //Retorna el rol especifico "AsesorVenta"
    @Override
    public String getRol() {
        return "AsesorVenta";
    }

    //Retorna una descripcion del asesor de ventas
    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Asesor de Venta";
    }

    //Simula el registro de un cliente por parte del asesor
    public void registrarCliente() {
        System.out.println(getNombreCompleto() + " está registrando un cliente.");
    }

    //Simula el registro de una reserva por parte del asesor
    public void registrarReserva() {
        System.out.println(getNombreCompleto() + " está registrando una reserva.");
    }

    //Simula el registro de una venta por parte del asesor
    public void registrarVenta() {
        System.out.println(getNombreCompleto() + " está registrando una venta.");
    }
}
