package clases;

import java.util.Arrays;

//Clase que representa un proyecto de construccion con sus departamentos 
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

    //Constructor: inicializa los datos del proyecto y crea el arreglo de departamentos 
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

    //Agrega un departamento al proyecto si hay capacidad disponible 
    public boolean agregarDepartamento(Departamento d) {
        if (contadorDepartamentos < departamentos.length) {
            departamentos[contadorDepartamentos++] = d;
            return true;
        }
        return false;
    }

    //Busca un departamento por su codigo dentro del proyecto 
    public Departamento buscarDepartamento(String codigo) {
        for (int i = 0; i < contadorDepartamentos; i++) {
            if (departamentos[i].getCodigo().equals(codigo)) {
                return departamentos[i];
            }
        }
        return null;
    }

    //Retorna una copia del arreglo de departamentos del proyecto 
    public Departamento[] getDepartamentos() {
        return Arrays.copyOf(departamentos, contadorDepartamentos);
    }

    //Retorna el nombre del proyecto 
    public String getNombre() { return nombre; }
    //Establece el nombre del proyecto 
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Retorna la direccion del proyecto 
    public String getDireccion() { return direccion; }
    //Establece la direccion del proyecto 
    public void setDireccion(String direccion) { this.direccion = direccion; }

    //Retorna el distrito del proyecto 
    public String getDistrito() { return distrito; }
    //Establece el distrito del proyecto 
    public void setDistrito(String distrito) { this.distrito = distrito; }

    //Retorna el numero de pisos del proyecto 
    public int getNumeroPisos() { return numeroPisos; }
    //Establece el numero de pisos del proyecto 
    public void setNumeroPisos(int numeroPisos) { this.numeroPisos = numeroPisos; }

    //Retorna la fecha de inicio de obra 
    public String getFechaInicioObra() { return fechaInicioObra; }
    //Establece la fecha de inicio de obra 
    public void setFechaInicioObra(String fechaInicioObra) { this.fechaInicioObra = fechaInicioObra; }

    //Retorna la fecha estimada de entrega 
    public String getFechaEstimadaEntrega() { return fechaEstimadaEntrega; }
    //Establece la fecha estimada de entrega 
    public void setFechaEstimadaEntrega(String fechaEstimadaEntrega) { this.fechaEstimadaEntrega = fechaEstimadaEntrega; }

    //Retorna el estado del proyecto 
    public String getEstado() { return estado; }
    //Establece el estado del proyecto 
    public void setEstado(String estado) { this.estado = estado; }

    //Retorna la cantidad de departamentos registrados 
    public int getContadorDepartamentos() { return contadorDepartamentos; }

    //Retorna una representacion en cadena del proyecto 
    @Override
    public String toString() {
        return nombre + " - " + distrito;
    }
}
