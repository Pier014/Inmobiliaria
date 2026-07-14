package gestion;

import clases.*;

//Clase que gestiona el registro y busqueda de ventas usando un arreglo estatico 
public class GestionVenta {

    private static final int MAX_VENTAS = 100;
    private Venta[] ventas;
    private int totalVentas;

    //Constructor: inicializa el arreglo de ventas vacio 
    public GestionVenta() {
        ventas = new Venta[MAX_VENTAS];
        totalVentas = 0;
    }

    //Registra una nueva venta si hay espacio disponible 
    public boolean registrar(Venta v) {
        if (totalVentas < MAX_VENTAS) {
            ventas[totalVentas++] = v;
            return true;
        }
        return false;
    }

    //Busca una venta por el codigo del departamento asociado 
    public Venta buscarPorDepartamento(String codigo) {
        for (int i = 0; i < totalVentas; i++) {
            if (ventas[i].getDepartamento().getCodigo().equals(codigo)) {
                return ventas[i];
            }
        }
        return null;
    }

    //Retorna una copia del arreglo de ventas registradas 
    public Venta[] obtenerVentas() {
        return java.util.Arrays.copyOf(ventas, totalVentas);
    }

    //Retorna la cantidad total de ventas registradas 
    public int obtenerTotal() {
        return totalVentas;
    }
}
