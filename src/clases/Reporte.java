package clases;

import interfaz.GeneradorReporte;

public class Reporte implements GeneradorReporte {
    private String tipo;
    private String contenido;

    public Reporte(String tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    @Override
    public String generarReporte() {
        return "=== REPORTE: " + tipo + " ===\n\n" + contenido;
    }

    public static Reporte departamentosPorProyecto(ProyectosConstruccion proyecto) {
        StringBuilder sb = new StringBuilder();
        sb.append("Proyecto: ").append(proyecto.getNombre()).append("\n");
        int disponibles = 0, reservados = 0, vendidos = 0;
        for (Departamento d : proyecto.getDepartamentos()) {
            switch (d.getEstado()) {
                case "Disponible" -> disponibles++;
                case "Reservado" -> reservados++;
                case "Vendido" -> vendidos++;
            }
        }
        sb.append("  Disponibles: ").append(disponibles).append("\n");
        sb.append("  Reservados:  ").append(reservados).append("\n");
        sb.append("  Vendidos:    ").append(vendidos).append("\n");
        return new Reporte("Dptos por Proyecto", sb.toString());
    }

    public static Reporte ventasPorAsesor(Empleado asesor, Venta[] ventas) {
        StringBuilder sb = new StringBuilder();
        sb.append("Asesor: ").append(asesor.getNombreCompleto()).append("\n\n");
        int total = 0;
        double montoTotal = 0;
        for (Venta v : ventas) {
            total++;
            montoTotal += v.getPrecioTotal();
            sb.append("  - ").append(v.getDepartamento().getCodigo())
              .append(" | S/ ").append(v.getPrecioTotal())
              .append(" | ").append(v.getFechaVenta()).append("\n");
        }
        sb.append("\nTotal ventas: ").append(total).append("\n");
        sb.append("Monto total: S/ ").append(montoTotal).append("\n");
        return new Reporte("Ventas por Asesor", sb.toString());
    }

    public static Reporte ventasPorRangoFechas(Venta[] ventas,
                                                 String desde, String hasta) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ventas del ").append(desde).append(" al ").append(hasta).append("\n\n");
        int total = 0;
        double montoTotal = 0;
        for (Venta v : ventas) {
            if (v.getFechaVenta().compareTo(desde) >= 0
                && v.getFechaVenta().compareTo(hasta) <= 0) {
                total++;
                montoTotal += v.getPrecioTotal();
                sb.append("  - ").append(v.getCliente().getNombreCompleto())
                  .append(" | S/ ").append(v.getPrecioTotal())
                  .append(" | ").append(v.getFechaVenta()).append("\n");
            }
        }
        sb.append("\nTotal ventas: ").append(total).append("\n");
        sb.append("Monto total: S/ ").append(montoTotal).append("\n");
        return new Reporte("Ventas por Rango de Fechas", sb.toString());
    }

    public static Reporte ingresosYSaldos(Venta[] ventas) {
        StringBuilder sb = new StringBuilder();
        double ingresosTotales = 0;
        double saldoCuotas = 0;
        for (Venta v : ventas) {
            ingresosTotales += v.getPrecioTotal();
            saldoCuotas += v.getSaldoPendiente();
        }
        sb.append("Ingresos totales: S/ ").append(ingresosTotales).append("\n");
        sb.append("Saldo cuotas pendientes: S/ ").append(saldoCuotas).append("\n");
        return new Reporte("Ingresos y Saldos", sb.toString());
    }

    public static Reporte proyectosMayorVenta(ProyectosConstruccion[] proyectos) {
        StringBuilder sb = new StringBuilder();
        double maxPorcentaje = -1;
        String maxProyecto = "";
        for (ProyectosConstruccion p : proyectos) {
            int total = p.getContadorDepartamentos();
            int vendidos = 0;
            for (Departamento d : p.getDepartamentos()) {
                if (d.getEstado().equals("Vendido")) vendidos++;
            }
            double porcentaje = total > 0 ? (vendidos * 100.0 / total) : 0;
            sb.append(p.getNombre()).append(": ")
              .append(String.format("%.1f%%", porcentaje)).append(" vendido\n");
            if (porcentaje > maxPorcentaje) {
                maxPorcentaje = porcentaje;
                maxProyecto = p.getNombre();
            }
        }
        sb.append("\nMayor porcentaje de ventas: ")
          .append(maxProyecto).append(" (").append(String.format("%.1f%%", maxPorcentaje)).append(")");
        return new Reporte("Proyectos con Mayor % de Ventas", sb.toString());
    }
}
