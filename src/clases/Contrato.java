package clases;

import interfaz.GeneradorReporte;

public class Contrato implements GeneradorReporte {
    private Venta venta;
    private ProyectosConstruccion proyecto;

    public Contrato(Venta venta, ProyectosConstruccion proyecto) {
        this.venta = venta;
        this.proyecto = proyecto;
    }

    @Override
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("     CONTRATO DE COMPRA-VENTA\n");
        sb.append("========================================\n\n");
        sb.append("DATOS DEL CLIENTE\n");
        sb.append("  Nombre: ").append(venta.getCliente().getNombreCompleto()).append("\n");
        sb.append("  DNI: ").append(venta.getCliente().getDNI()).append("\n\n");
        sb.append("DATOS DEL DEPARTAMENTO\n");
        sb.append("  Codigo: ").append(venta.getDepartamento().getCodigo()).append("\n");
        sb.append("  Piso: ").append(venta.getDepartamento().getPiso()).append("\n");
        sb.append("  Area: ").append(venta.getDepartamento().getAreaM2()).append(" m²\n");
        sb.append("  Tipo: ").append(venta.getDepartamento().getTipo()).append("\n\n");
        sb.append("PROYECTO\n");
        sb.append("  Nombre: ").append(proyecto.getNombre()).append("\n");
        sb.append("  Direccion: ").append(proyecto.getDireccion()).append("\n");
        sb.append("  Distrito: ").append(proyecto.getDistrito()).append("\n");
        sb.append("  Fecha estimada de entrega: ")
          .append(proyecto.getFechaEstimadaEntrega()).append("\n\n");
        sb.append("CONDICIONES DE PAGO\n");
        sb.append("  Precio total: S/ ").append(venta.getPrecioTotal()).append("\n");
        sb.append("  Modalidad de pago: ").append(venta.getModalidadPago()).append("\n");
        if (venta.getModalidadPago().equals("cuotas directas")) {
            sb.append("  Saldo pendiente: S/ ").append(venta.getSaldoPendiente()).append("\n");
        }
        sb.append("\n========================================\n");
        return sb.toString();
    }
}
