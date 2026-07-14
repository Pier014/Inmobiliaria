package clases;

import interfaz.Autenticable;

//Clase hija de Persona. Representa un empleado del sistema con credenciales de acceso 
public class Empleado extends Persona implements Autenticable {
    protected String usuario;
    protected String password;

    //Constructor: inicializa los datos personales y credenciales del empleado 
    public Empleado(String dni, String nombres, String apellidos,
                    String usuario, String password) {
        super(dni, nombres, apellidos);
        this.usuario = usuario;
        this.password = password;
    }

    //Retorna el nombre de usuario del empleado 
    public String getUsuario() {
        return usuario;
    }

    //Establece el nombre de usuario del empleado 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //Retorna la contrasena del empleado 
    public String getPassword() {
        return password;
    }

    //Establece la contrasena del empleado 
    public void setPassword(String password) {
        this.password = password;
    }

    //Verifica si el usuario y contrasena coinciden con los del empleado 
    @Override
    public boolean autenticar(String usuario, String password) {
        return this.usuario.equals(usuario) && this.password.equals(password);
    }

    //Retorna el rol generico del empleado 
    public String getRol() {
        return "Empleado";
    }

    //Retorna una descripcion del empleado con su nombre completo y rol 
    @Override
    public String getDescripcion() {
        return getNombreCompleto() + " - Empleado";
    }
}
