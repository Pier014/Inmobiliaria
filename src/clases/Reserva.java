package clases;

//Clase que representa una reserva de un departamento realizada por un cliente 
public class Reserva {
    private Cliente cliente;
    private Departamento departamento;
    private double montoSeparacion;
    private String fechaReserva;
    private String fechaVigencia;

    //Constructor: crea una reserva si el departamento esta disponible. Cambia su estado a "Reservado" 
    public Reserva(Cliente cliente, Departamento departamento,
                   double montoSeparacion, String fechaReserva,
                   String fechaVigencia) {
        if (!departamento.getEstado().equals("Disponible")) {
            throw new IllegalArgumentException(
                "El departamento no esta disponible para reservar");
        }
        this.cliente = cliente;
        this.departamento = departamento;
        this.montoSeparacion = montoSeparacion;
        this.fechaReserva = fechaReserva;
        this.fechaVigencia = fechaVigencia;
        this.departamento.setEstado("Reservado");
    }

    //Retorna el cliente que realizo la reserva 
    public Cliente getCliente() { return cliente; }
    //Retorna el departamento reservado 
    public Departamento getDepartamento() { return departamento; }
    //Retorna el monto de separacion pagado 
    public double getMontoSeparacion() { return montoSeparacion; }
    //Retorna la fecha en que se realizo la reserva 
    public String getFechaReserva() { return fechaReserva; }
    //Retorna la fecha de vigencia de la reserva 
    public String getFechaVigencia() { return fechaVigencia; }
}
