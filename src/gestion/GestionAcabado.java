package gestion;

import clases.AcabadoOpcional;

public class GestionAcabado {

    private static final int MAX_ACABADOS = 50;
    private AcabadoOpcional[] acabados;
    private int totalAcabados;

    public GestionAcabado() {
        acabados = new AcabadoOpcional[MAX_ACABADOS];
        totalAcabados = 0;
    }

    public boolean registrar(AcabadoOpcional a) {
        if (totalAcabados < MAX_ACABADOS) {
            acabados[totalAcabados++] = a;
            return true;
        }
        return false;
    }

    public boolean actualizar(int index, AcabadoOpcional a) {
        if (index >= 0 && index < totalAcabados) {
            acabados[index] = a;
            return true;
        }
        return false;
    }

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

    public AcabadoOpcional[] obtenerAcabados() {
        return java.util.Arrays.copyOf(acabados, totalAcabados);
    }

    public int obtenerTotal() {
        return totalAcabados;
    }
}
