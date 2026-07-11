package clases;

public class Gerente extends Empleado {
    public Gerente(String dni, String nombres, String apellidos, String usuario, String password) {
        super(dni, nombres, apellidos, usuario, password);
    }

    @Override
    public String getRol() {
        return "Gerente";
    }

    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Gerente";
    }

    public void verReportes() {
        System.out.println(getNombreCompleto() + " esta visualizando reportes.");
    }
}
