package gestion;

import clases.*;

public class GestionVenta {

    private static final int MAX_VENTAS = 100;
    private Venta[] ventas;
    private int totalVentas;

    public GestionVenta() {
        ventas = new Venta[MAX_VENTAS];
        totalVentas = 0;
    }

    public boolean registrar(Venta v) {
        if (totalVentas < MAX_VENTAS) {
            ventas[totalVentas++] = v;
            return true;
        }
        return false;
    }

    public Venta buscarPorDepartamento(String codigo) {
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i].getDepartamento().getCodigo().equals(codigo)) {
                return ventas[i];
            }
        }
        return null;
    }

    public Venta[] obtenerVentas() {
        return java.util.Arrays.copyOf(ventas, totalVentas);
    }

    public int obtenerTotal() {
        return totalVentas;
    }
}
