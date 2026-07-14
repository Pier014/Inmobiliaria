package clases;

//Clase hija de Empleado. Representa a un gerente con acceso a reportes 
public class Gerente extends Empleado {
    //Constructor: inicializa los datos del gerente usando el constructor de Empleado 
    public Gerente(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    //Retorna el rol especifico "Gerente" 
    @Override
    public String getRol() {
        return "Gerente";
    }

    //Retorna una descripcion del gerente 
    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Gerente";
    }

    //Simula la visualizacion de reportes por parte del gerente 
    public void verReportes() {
        System.out.println(getNombreCompleto() + " esta visualizando reportes.");
    }
}
