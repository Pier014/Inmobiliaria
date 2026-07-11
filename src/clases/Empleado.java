package clases;

import interfaz.Autenticable;

public class Empleado extends Persona implements Autenticable {
    protected String usuario;
    protected String password;

    public Empleado(String dni, String nombres, String apellidos,
                    String usuario, String password) {
        super(dni, nombres, apellidos);
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean autenticar(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    public String getRol() {
        return "Empleado";
    }

    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Empleado";
    }
}
