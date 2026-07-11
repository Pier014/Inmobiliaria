package clases;

public class Reserva {
    private Cliente cliente;
    private Departamento departamento;
    private double montoSeparacion;
    private String fechaReserva;
    private String fechaVigencia;

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

    public Cliente getCliente() { return cliente; }
    public Departamento getDepartamento() { return departamento; }
    public double getMontoSeparacion() { return montoSeparacion; }
    public String getFechaReserva() { return fechaReserva; }
    public String getFechaVigencia() { return fechaVigencia; }
}
