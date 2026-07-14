package clases;

//Clase que representa un departamento dentro de un proyecto de construccion
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

    //Constructor: inicializa los datos del departamento. El estado inicial es "Disponible"
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

    //Retorna el codigo del departamento
    public String getCodigo() { return codigo; }
    //Establece el codigo del departamento
    public void setCodigo(String codigo) { this.codigo = codigo; }

    //Retorna el piso donde se ubica el departamento 
    public int getPiso() { return piso; }
    //Establece el piso del departamento 
    public void setPiso(int piso) { this.piso = piso; }

    //Retorna el numero del departamento 
    public int getNumero() { return numero; }
    //Establece el numero del departamento 
    public void setNumero(int numero) { this.numero = numero; }

    //Retorna el area en metros cuadrados del departamento 
    public double getAreaM2() { return areaM2; }
    //Establece el area en metros cuadrados del departamento 
    public void setAreaM2(double areaM2) { this.areaM2 = areaM2; }

    //Retorna la cantidad de dormitorios del departamento 
    public int getDormitorios() { return dormitorios; }
    //Establece la cantidad de dormitorios del departamento 
    public void setDormitorios(int dormitorios) { this.dormitorios = dormitorios; }

    //Retorna la cantidad de banios del departamento 
    public int getBanios() { return banios; }
    //Establece la cantidad de banios del departamento 
    public void setBanios(int banios) { this.banios = banios; }

    //Retorna el tipo de departamento (Flat, Duplex, Penthouse) 
    public String getTipo() { return tipo; }
    //Establece el tipo de departamento 
    public void setTipo(String tipo) { this.tipo = tipo; }

    //Retorna el precio base del departamento 
    public double getPrecioBase() { return precioBase; }
    //Establece el precio base del departamento 
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    //Retorna el estado actual (Disponible, Reservado, Vendido) 
    public String getEstado() { return estado; }
    //Establece el estado del departamento 
    public void setEstado(String estado) { this.estado = estado; }

    //Retorna una representacion en cadena del departamento 
    @Override
    public String toString() {
        return codigo + " - S/ " + precioBase;
    }
}
