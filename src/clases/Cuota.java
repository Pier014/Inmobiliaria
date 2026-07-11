package clases;

public class Cuota {
    private int numero;
    private double monto;
    private String fechaVencimiento;
    private boolean pagada;

    public Cuota(int numero, double monto, String fechaVencimiento) {
        this.numero = numero;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = false;
    }

    public void pagar() {
        this.pagada = true;
    }

    public int getNumero() { return numero; }
    public double getMonto() { return monto; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public boolean isPagada() { return pagada; }
}
