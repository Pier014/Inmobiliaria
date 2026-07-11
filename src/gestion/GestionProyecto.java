package gestion;

import clases.*;

public class GestionProyecto {

    private static final int MAX_PROYECTOS = 20;
    private ProyectosConstruccion[] proyectos;
    private int totalProyectos;

    public GestionProyecto() {
        proyectos = new ProyectosConstruccion[MAX_PROYECTOS];
        totalProyectos = 0;
        inicializarDatos();
    }

    private void inicializarDatos() {
        ProyectosConstruccion p1 = new ProyectosConstruccion("Edificio Aurora", "Av. Principal 123",
            "Miraflores", 10, "2024-01-15", "2025-06-30", "En construccion", 20);
        p1.agregarDepartamento(new Departamento("AUR-101", 1, 1, 80, 3, 2, "Flat", 250000));
        p1.agregarDepartamento(new Departamento("AUR-102", 1, 2, 100, 3, 2, "Flat", 280000));
        p1.agregarDepartamento(new Departamento("AUR-201", 2, 1, 120, 4, 3, "Duplex", 350000));
        proyectos[totalProyectos++] = p1;
    }

    public boolean registrar(ProyectosConstruccion p) {
        if (totalProyectos < MAX_PROYECTOS) {
            proyectos[totalProyectos++] = p;
            return true;
        }
        return false;
    }

    public boolean actualizar(int index, ProyectosConstruccion p) {
        if (index >= 0 && index < totalProyectos) {
            proyectos[index] = p;
            return true;
        }
        return false;
    }

    public boolean eliminar(int index) {
        if (index >= 0 && index < totalProyectos) {
            for (int i = index; i < totalProyectos - 1; i++) {
                proyectos[i] = proyectos[i + 1];
            }
            proyectos[--totalProyectos] = null;
            return true;
        }
        return false;
    }

    public void agregarDepartamento(int indexProyecto, Departamento d) {
        if (indexProyecto >= 0 && indexProyecto < totalProyectos) {
            proyectos[indexProyecto].agregarDepartamento(d);
        }
    }

    public Departamento buscarDepartamento(String codigo) {
        for (int i = 0; i < totalProyectos; i++) {
            Departamento d = proyectos[i].buscarDepartamento(codigo);
            if (d != null) return d;
        }
        return null;
    }

    public ProyectosConstruccion[] obtenerProyectos() {
        return java.util.Arrays.copyOf(proyectos, totalProyectos);
    }

    public int obtenerTotal() {
        return totalProyectos;
    }
}
