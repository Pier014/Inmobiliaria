package interfaz;

//Interfaz que define el contrato para la autenticacion de usuarios 
public interface Autenticable {
    //Valida las credenciales de un usuario 
    boolean autenticar(String usuario, String password);
}
