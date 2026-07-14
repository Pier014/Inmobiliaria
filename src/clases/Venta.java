package clases;

//Clase que representa la venta de un departamento a un cliente 
public class Venta {
    private Cliente cliente;
    private Departamento departamento;
    private Reserva reserva;
    private String modalidadPago;
    private String fechaVenta;
    private double precioTotal;
    private Cuota[] cuotas;
    private int contadorCuotas;

    //Constructor: registra la venta y cambia el estado del departamento a "Vendido" 
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

    //Registra el cronograma de cuotas si la modalidad de pago es "cuotas directas" 
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

    //Marca una cuota como pagada si existe en el cronograma 
    public boolean pagarCuota(int numeroCuota) {
        if (cuotas != null && numeroCuota >= 0 && numeroCuota < contadorCuotas) {
            cuotas[numeroCuota].pagar();
            return true;
        }
        return false;
    }

    //Calcula y retorna el saldo pendiente sumando las cuotas no pagadas 
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

    //Retorna el cliente asociado a la venta 
    public Cliente getCliente() { return cliente; }
    //Retorna el departamento vendido 
    public Departamento getDepartamento() { return departamento; }
    //Retorna la reserva asociada a la venta (puede ser null) 
    public Reserva getReserva() { return reserva; }
    //Retorna la modalidad de pago de la venta 
    public String getModalidadPago() { return modalidadPago; }
    //Retorna la fecha de la venta 
    public String getFechaVenta() { return fechaVenta; }
    //Retorna el precio total de la venta 
    public double getPrecioTotal() { return precioTotal; }
    //Retorna el arreglo de cuotas del cronograma 
    public Cuota[] getCuotas() { return cuotas; }
}
