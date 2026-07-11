package clases;

import java.util.Arrays;

public class ProyectosConstruccion {
    private String nombre;
    private String direccion;
    private String distrito;
    private int numeroPisos;
    private String fechaInicioObra;
    private String fechaEstimadaEntrega;
    private String estado;
    private Departamento[] departamentos;
    private int contadorDepartamentos;

    public ProyectosConstruccion(String nombre, String direccion, String distrito,
                                  int numeroPisos, String fechaInicioObra,
                                  String fechaEstimadaEntrega, String estado,
                                  int capacidadDepartamentos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.distrito = distrito;
        this.numeroPisos = numeroPisos;
        this.fechaInicioObra = fechaInicioObra;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.estado = estado;
        this.departamentos = new Departamento[capacidadDepartamentos];
        this.contadorDepartamentos = 0;
    }

    public boolean agregarDepartamento(Departamento d) {
        if (contadorDepartamentos < departamentos.length) {
            departamentos[contadorDepartamentos++] = d;
            return true;
        }
        return false;
    }

    public Departamento buscarDepartamento(String codigo) {
        for (int i = 0; i < contadorDepartamentos; i++) {
            if (departamentos[i].getCodigo().equals(codigo)) {
                return departamentos[i];
            }
        }
        return null;
    }

    public Departamento[] getDepartamentos() {
        return Arrays.copyOf(departamentos, contadorDepartamentos);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public int getNumeroPisos() { return numeroPisos; }
    public void setNumeroPisos(int numeroPisos) { this.numeroPisos = numeroPisos; }

    public String getFechaInicioObra() { return fechaInicioObra; }
    public void setFechaInicioObra(String fechaInicioObra) { this.fechaInicioObra = fechaInicioObra; }

    public String getFechaEstimadaEntrega() { return fechaEstimadaEntrega; }
    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) { this.fechaEstimadaEntrega = fechaEstimadaEntrega; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getContadorDepartamentos() { return contadorDepartamentos; }

    @Override
    public String toString() {
        return nombre + " - " + distrito;
    }
}
