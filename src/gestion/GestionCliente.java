package gestion;

import clases.Cliente;

public class GestionCliente {

    private static final int MAX_CLIENTES = 50;
    private Cliente[] clientes;
    private int totalClientes;

    public GestionCliente() {
        clientes = new Cliente[MAX_CLIENTES];
        totalClientes = 0;
        inicializarDatos();
    }

    private void inicializarDatos() {
        clientes[totalClientes++] = new Cliente("44444444", "Juan", "Perez", "1985-03-15",
            "Casado", "Ingeniero", 8000, "987654321", "juan@correo.com");
        clientes[totalClientes++] = new Cliente("55555555", "Ana", "Torres", "1990-07-22",
            "Soltera", "Arquitecta", 6000, "987654322", "ana@correo.com");
    }

    public boolean registrar(Cliente c) {
        if (totalClientes < MAX_CLIENTES) {
            clientes[totalClientes++] = c;
            return true;
        }
        return false;
    }

    public boolean actualizar(int index, Cliente c) {
        if (index >= 0 && index < totalClientes) {
            clientes[index] = c;
            return true;
        }
        return false;
    }

    public boolean eliminar(int index) {
        if (index >= 0 && index < totalClientes) {
            for (int i = index; i < totalClientes - 1; i++) {
                clientes[i] = clientes[i + 1];
            }
            clientes[--totalClientes] = null;
            return true;
        }
        return false;
    }

    public Cliente[] obtenerClientes() {
        return java.util.Arrays.copyOf(clientes, totalClientes);
    }

    public int obtenerTotal() {
        return totalClientes;
    }
}
