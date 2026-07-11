package gestion;

import clases.*;

public class GestionEmpleado {

    private static final int MAX_EMPLEADOS = 50;
    private Empleado[] empleados;
    private int totalEmpleados;

    public GestionEmpleado() {
        empleados = new Empleado[MAX_EMPLEADOS];
        totalEmpleados = 0;
        inicializarDatos();
    }

    private void inicializarDatos() {
        empleados[totalEmpleados++] = new Administrador("11111111", "Admin", "Principal", "admin", "1234");
        empleados[totalEmpleados++] = new AsesorVenta("22222222", "Carlos", "Lopez", "asesor1", "1234");
        empleados[totalEmpleados++] = new Gerente("33333333", "Maria", "Garcia", "gerente", "1234");
    }

    public Empleado validarLogin(String usuario, String password) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].autenticar(usuario, password)) {
                return empleados[i];
            }
        }
        return null;
    }

    public boolean existeDNI(String dni) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDNI().equals(dni)) {
                return true;
            }
        }
        return false;
    }

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

    public boolean actualizar(int index, Empleado e) {
        if (index >= 0 && index < totalEmpleados) {
            empleados[index] = e;
            return true;
        }
        return false;
    }

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

    public Empleado[] obtenerEmpleados() {
        java.util.Arrays.sort(empleados, 0, totalEmpleados,
            (a, b) -> a.getRol().compareTo(b.getRol()));
        return java.util.Arrays.copyOf(empleados, totalEmpleados);
    }

    public int obtenerTotal() {
        return totalEmpleados;
    }
}
