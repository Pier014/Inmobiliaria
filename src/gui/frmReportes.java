package gui;

import clases.*;
import gestion.*;
import javax.swing.*;
import java.awt.*;
import utilitario.DatePicker;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para generar y visualizar reportes gerenciales 
public class frmReportes extends JFrame {

    private GestionProyecto gestionProyecto;
    private GestionVenta gestionVenta;
    private GestionEmpleado gestionEmpleado;
    private JComboBox<String> cmbTipoReporte;
    private DatePicker dpDesde, dpHasta;
    private JPanel panelFechas;
    private JTextArea txtResultado;

    //Constructor: configura la interfaz grafica de reportes 
    public frmReportes(GestionProyecto gestionProyecto, GestionVenta gestionVenta,
                        GestionEmpleado gestionEmpleado) {
        this.gestionProyecto = gestionProyecto;
        this.gestionVenta = gestionVenta;
        this.gestionEmpleado = gestionEmpleado;

        setTitle("Reportes Gerenciales");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REPORTES GERENCIALES");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);
        topPanel.add(new JLabel("Tipo de reporte:"));
        cmbTipoReporte = new JComboBox<>(new String[]{
            "Departamentos por proyecto",
            "Ventas por asesor",
            "Ventas por rango de fechas",
            "Ingresos y saldos",
            "Proyectos con mayor % de ventas"
        });
        cmbTipoReporte.setPreferredSize(new Dimension(300, 25));
        topPanel.add(cmbTipoReporte);

        JButton btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGenerar.addActionListener(e -> generarReporte());
        topPanel.add(btnGenerar);

        topPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(topPanel);

        panelFechas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFechas.setOpaque(false);
        dpDesde = new DatePicker();
        dpHasta = new DatePicker();
        panelFechas.add(new JLabel("Desde:"));
        panelFechas.add(dpDesde);
        panelFechas.add(new JLabel("Hasta:"));
        panelFechas.add(dpHasta);
        panelFechas.setVisible(false);
        panelFechas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(panelFechas);
        panel.add(Box.createVerticalStrut(10));

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtResultado.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setPreferredSize(new Dimension(800, 550));
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 700));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(scrollPane);

        cmbTipoReporte.addActionListener(e ->
            panelFechas.setVisible(cmbTipoReporte.getSelectedIndex() == 2));

        add(panel);
    }

    //Genera el reporte seleccionado y lo muestra en el area de texto 
    private void generarReporte() {
        String reporte = "";
        int tipo = cmbTipoReporte.getSelectedIndex();

        try {
            switch (tipo) {
                case 0 -> {
                    for (ProyectosConstruccion p : gestionProyecto.obtenerProyectos()) {
                        reporte += Reporte.departamentosPorProyecto(p).generarReporte() + "\n";
                    }
                }
                case 1 -> {
                    for (Empleado e : gestionEmpleado.obtenerEmpleados()) {
                        if (e instanceof AsesorVenta) {
                            reporte += Reporte.ventasPorAsesor(e, gestionVenta.obtenerVentas()).generarReporte() + "\n";
                        }
                    }
                }
                case 2 -> {
                    String desde = dpDesde.getFecha();
                    String hasta = dpHasta.getFecha();
                    reporte = Reporte.ventasPorRangoFechas(gestionVenta.obtenerVentas(), desde, hasta).generarReporte();
                }
                case 3 ->
                    reporte = Reporte.ingresosYSaldos(gestionVenta.obtenerVentas()).generarReporte();
                case 4 ->
                    reporte = Reporte.proyectosMayorVenta(gestionProyecto.obtenerProyectos()).generarReporte();
            }
            txtResultado.setText(reporte);
        } catch (Exception ex) {
            txtResultado.setText("Error al generar reporte: " + ex.getMessage());
        }
    }
}
