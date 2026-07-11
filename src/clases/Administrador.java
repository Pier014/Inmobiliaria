package clases;

public class Administrador extends Empleado {
    public Administrador(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Administrador";
    }

    public void gestionarEmpleado() {
        System.out.println(getNombreCompleto() + " está gestionando empleados.");
    }

    public void gestionarProyecto() {
        System.out.println(getNombreCompleto() + " está gestionando proyectos.");
    }

    public void gestionarAcabado() {
        System.out.println(getNombreCompleto() + " está gestionando acabados/opcionales.");
    }
}
