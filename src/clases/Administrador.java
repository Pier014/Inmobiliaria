package clases;

//Clase hija de Empleado. Representa a un administrador con permisos de gestion total
public class Administrador extends Empleado {
    //Constructor: inicializa los datos del administrador usando el constructor de Empleado
    public Administrador(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    //Retorna el rol especifico "Administrador"
    @Override
    public String getRol() {
        return "Administrador";
    }

    //Retorna una descripcion del administrador
    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Administrador";
    }

    //Simula la gestion de empleados por parte del administrador
    public void gestionarEmpleado() {
        System.out.println(getNombreCompleto() + " está gestionando empleados.");
    }

    //Simula la gestion de proyectos por parte del administrador
    public void gestionarProyecto() {
        System.out.println(getNombreCompleto() + " está gestionando proyectos.");
    }

    //Simula la gestion de acabados opcionales por parte del administrador
    public void gestionarAcabado() {
        System.out.println(getNombreCompleto() + " está gestionando acabados/opcionales.");
    }
}
