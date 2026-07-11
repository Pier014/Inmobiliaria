package clases;

public class Departamento {
    private String codigo;
    private int piso;
    private int numero;
    private double areaM2;
    private int dormitorios;
    private int banios;
    private String tipo;
    private double precioBase;
    private String estado;

    public Departamento(String codigo, int piso, int numero, double areaM2,
                        int dormitorios, int banios, String tipo,
                        double precioBase) {
        this.codigo = codigo;
        this.piso = piso;
        this.numero = numero;
        this.areaM2 = areaM2;
        this.dormitorios = dormitorios;
        this.banios = banios;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.estado = "Disponible";
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public double getAreaM2() { return areaM2; }
    public void setAreaM2(double areaM2) { this.areaM2 = areaM2; }

    public int getDormitorios() { return dormitorios; }
    public void setDormitorios(int dormitorios) { this.dormitorios = dormitorios; }

    public int getBanios() { return banios; }
    public void setBanios(int banios) { this.banios = banios; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return codigo + " - S/ " + precioBase;
    }
}
