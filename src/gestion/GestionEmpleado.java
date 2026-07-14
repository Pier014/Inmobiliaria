package gestion;

import clases.*;

//Clase que gestiona el CRUD y autenticacion de empleados usando un arreglo estatico 
public class GestionEmpleado {

    private static final int MAX_EMPLEADOS = 50;
    private Empleado[] empleados;
    private int totalEmpleados;

    //Constructor: inicializa el arreglo de empleados y carga datos de ejemplo 
    public GestionEmpleado() {
        empleados = new Empleado[MAX_EMPLEADOS];
        totalEmpleados = 0;
        inicializarDatos();
    }

    //Carga empleados de ejemplo para pruebas 
    private void inicializarDatos() {
        empleados[totalEmpleados++] = new Administrador("11111111", "Admin", "Principal", "admin", "1234");
        empleados[totalEmpleados++] = new AsesorVenta("22222222", "Carlos", "Lopez", "asesor1", "1234");
        empleados[totalEmpleados++] = new Gerente("33333333", "Maria", "Garcia", "gerente", "1234");
    }

    //Valida las credenciales de un empleado y lo retorna si es correcto, null si no 
    public Empleado validarLogin(String usuario, String password) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].autenticar(usuario, password)) {
                return empleados[i];
            }
        }
        return null;
    }

    //Verifica si ya existe un empleado con el DNI indicado 
    public boolean existeDNI(String dni) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDNI().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    //Registra un nuevo empleado si el DNI no esta repetido y hay espacio 
    public boolean registrar(Empleado e) {
        if (existeDNI(e.getDNI())) {
            throw new IllegalArgumentException("Ya existe un empleado con DNI " + e.getDNI());
        }
        if (totalEmpleados < MAX_EMPLEADOS) {
            empleados[totalEmpleados++] = e;
            return true;
        }
        return false;
    }

    //Actualiza un empleado en la posicion indicada 
    public boolean actualizar(int index, Empleado e) {
        if (index >= 0 && index < totalEmpleados) {
            empleados[index] = e;
            return true;
        }
        return false;
    }

    //Elimina un empleado por su indice y reorganiza el arreglo 
    public boolean eliminar(int index) {
        if (index >= 0 && index < totalEmpleados) {
            for (int i = index; i < totalEmpleados - 1; i++) {
                empleados[i] = empleados[i + 1];
            }
            empleados[--totalEmpleados] = null;
            return true;
        }
        return false;
    }

    //Retorna una copia del arreglo de empleados ordenados por rol 
    public Empleado[] obtenerEmpleados() {
        java.util.Arrays.sort(empleados, 0, totalEmpleados,
            (a, b) -> a.getRol().compareTo(b.getRol()));
        return java.util.Arrays.copyOf(empleados, totalEmpleados);
    }

    //Retorna la cantidad total de empleados registrados 
    public int obtenerTotal() {
        return totalEmpleados;
    }
}
