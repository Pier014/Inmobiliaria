package gestion;

import clases.AcabadoOpcional;

//Clase que gestiona el CRUD de acabados opcionales usando un arreglo estatico 
public class GestionAcabado {

    private static final int MAX_ACABADOS = 50;
    private AcabadoOpcional[] acabados;
    private int totalAcabados;

    //Constructor: inicializa el arreglo de acabados vacio 
    public GestionAcabado() {
        acabados = new AcabadoOpcional[MAX_ACABADOS];
        totalAcabados = 0;
    }

    //Registra un nuevo acabado opcional si hay espacio disponible 
    public boolean registrar(AcabadoOpcional a) {
        if (totalAcabados < MAX_ACABADOS) {
            acabados[totalAcabados++] = a;
            return true;
        }
        return false;
    }

    //Actualiza un acabado en la posicion indicada 
    public boolean actualizar(int index, AcabadoOpcional a) {
        if (index >= 0 && index < totalAcabados) {
            acabados[index] = a;
            return true;
        }
        return false;
    }

    //Elimina un acabado por su indice y reorganiza el arreglo 
    public boolean eliminar(int index) {
        if (index >= 0 && index < totalAcabados) {
            for (int i = index; i < totalAcabados - 1; i++) {
                acabados[i] = acabados[i + 1];
            }
            acabados[--totalAcabados] = null;
            return true;
        }
        return false;
    }

    //Retorna una copia del arreglo de acabados registrados 
    public AcabadoOpcional[] obtenerAcabados() {
        return java.util.Arrays.copyOf(acabados, totalAcabados);
    }

    //Retorna la cantidad total de acabados registrados 
    public int obtenerTotal() {
        return totalAcabados;
    }
}
