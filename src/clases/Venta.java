package clases;

public class Venta {
    private Cliente cliente;
    private Departamento departamento;
    private Reserva reserva;
    private String modalidadPago;
    private String fechaVenta;
    private double precioTotal;
    private Cuota[] cuotas;
    private int contadorCuotas;

    public Venta(Cliente cliente, Departamento departamento, Reserva reserva,
                 String modalidadPago, String fechaVenta) {
        this.cliente = cliente;
        this.departamento = departamento;
        this.reserva = reserva;
        this.modalidadPago = modalidadPago;
        this.fechaVenta = fechaVenta;
        this.precioTotal = departamento.getPrecioBase();
        this.departamento.setEstado("Vendido");
    }

    public void registrarCronograma(double cuotaInicial, int numeroCuotas,
                                     double montoPorCuota, String[] fechasVencimiento) {
        if (!modalidadPago.equals("cuotas directas")) return;

        this.cuotas = new Cuota[numeroCuotas + 1];
        this.cuotas[0] = new Cuota(0, cuotaInicial, fechasVencimiento[0]);
        this.contadorCuotas = 1;

        for (int i = 1; i <= numeroCuotas; i++) {
            this.cuotas[i] = new Cuota(i, montoPorCuota, fechasVencimiento[i]);
            this.contadorCuotas++;
        }
    }

    public boolean pagarCuota(int numeroCuota) {
        if (cuotas != null && numeroCuota >= 0 && numeroCuota < contadorCuotas) {
            cuotas[numeroCuota].pagar();
            return true;
        }
        return false;
    }

    public double getSaldoPendiente() {
        double saldo = 0;
        if (cuotas != null) {
            for (int i = 0; i < contadorCuotas; i++) {
                if (!cuotas[i].isPagada()) {
                    saldo += cuotas[i].getMonto();
                }
            }
        }
        return saldo;
    }

    public Cliente getCliente() { return cliente; }
    public Departamento getDepartamento() { return departamento; }
    public Reserva getReserva() { return reserva; }
    public String getModalidadPago() { return modalidadPago; }
    public String getFechaVenta() { return fechaVenta; }
    public double getPrecioTotal() { return precioTotal; }
    public Cuota[] getCuotas() { return cuotas; }
}
