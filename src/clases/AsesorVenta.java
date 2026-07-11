package clases;

public class AsesorVenta extends Empleado {
    public AsesorVenta(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    @Override
    public String getRol() {
        return "AsesorVenta";
    }

    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Asesor de Venta";
    }

    public void registrarCliente() {
        System.out.println(getNombreCompleto() + " está registrando un cliente.");
    }

    public void registrarReserva() {
        System.out.println(getNombreCompleto() + " está registrando una reserva.");
    }

    public void registrarVenta() {
        System.out.println(getNombreCompleto() + " está registrando una venta.");
    }
}
