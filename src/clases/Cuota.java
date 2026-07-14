package clases;

//Clase que representa una cuota dentro de un cronograma de pagos de una venta
public class Cuota {
    private int numero;
    private double monto;
    private String fechaVencimiento;
    private boolean pagada;

    //Constructor: inicializa la cuota con su numero, monto y fecha de vencimiento. Por defecto no esta pagada
    public Cuota(int numero, double monto, String fechaVencimiento) {
        this.numero = numero;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = false;
    }

    //Marca la cuota como pagada
    public void pagar() {
        this.pagada = true;
    }

    //Retorna el numero de la cuota
    public int getNumero() { return numero; }
    //Retorna el monto de la cuota
    public double getMonto() { return monto; }
    //Retorna la fecha de vencimiento de la cuota
    public String getFechaVencimiento() { return fechaVencimiento; }
    //Indica si la cuota ha sido pagada
    public boolean isPagada() { return pagada; }
}
