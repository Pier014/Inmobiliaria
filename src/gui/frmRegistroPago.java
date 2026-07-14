package gui;

import clases.Cuota;
import clases.Venta;
import gestion.GestionVenta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para buscar una venta y registrar el pago de una cuota 
public class frmRegistroPago extends JFrame {

    private GestionVenta gestionVenta;
    private JTextField txtCodDep;
    private JTable tablaCuotas;
    private DefaultTableModel modelCuotas;

    //Constructor: configura la interfaz grafica para el registro de pagos 
    public frmRegistroPago(GestionVenta gestionVenta) {
        this.gestionVenta = gestionVenta;

        setTitle("Registrar Pago de Cuota");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REGISTRAR PAGO DE CUOTA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Codigo de departamento:"));
        txtCodDep = new JTextField(15);
        topPanel.add(txtCodDep);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarVenta());
        topPanel.add(btnBuscar);
        panel.add(topPanel, BorderLayout.NORTH);

        modelCuotas = new DefaultTableModel(new String[]{"N° Cuota", "Monto S/", "Vencimiento", "Pagada"}, 0);
        tablaCuotas = new JTable(modelCuotas);
        tablaCuotas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaCuotas.setRowHeight(25);
        panel.add(new JScrollPane(tablaCuotas), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton btnPagar = new JButton("Pagar Cuota Seleccionada");
        btnPagar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnPagar.setBackground(new Color(46, 204, 113));
        btnPagar.setForeground(Color.WHITE);
        btnPagar.setFocusPainted(false);
        btnPagar.addActionListener(e -> pagarCuota());
        bottomPanel.add(btnPagar);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
    }

    //Busca la venta asociada al codigo de departamento y muestra sus cuotas 
    private void buscarVenta() {
        String cod = txtCodDep.getText().trim();
        if (cod.isEmpty()) return;

        Venta v = gestionVenta.buscarPorDepartamento(cod);
        if (v == null) {
            JOptionPane.showMessageDialog(this, "Venta no encontrada para ese departamento.",
                "Error", JOptionPane.ERROR_MESSAGE);
            modelCuotas.setRowCount(0);
            return;
        }

        modelCuotas.setRowCount(0);
        Cuota[] cuotas = v.getCuotas();
        if (cuotas != null) {
            for (Cuota c : cuotas) {
                modelCuotas.addRow(new Object[]{
                    c.getNumero(),
                    String.format("%,.2f", c.getMonto()),
                    c.getFechaVencimiento(),
                    c.isPagada() ? "SI" : "NO"
                });
            }
        }
    }

    //Marca la cuota seleccionada como pagada 
    private void pagarCuota() {
        int row = tablaCuotas.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una cuota para pagar.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String cod = txtCodDep.getText().trim();
        Venta v = gestionVenta.buscarPorDepartamento(cod);
        if (v == null) return;

        boolean pagada = modelCuotas.getValueAt(row, 3).equals("SI");
        if (pagada) {
            JOptionPane.showMessageDialog(this, "Esta cuota ya esta pagada.");
            return;
        }

        int numCuota = (Integer) modelCuotas.getValueAt(row, 0);
        if (v.pagarCuota(numCuota)) {
            JOptionPane.showMessageDialog(this, "Pago registrado. Saldo pendiente: S/ "
                + String.format("%,.2f", v.getSaldoPendiente()));
            buscarVenta();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el pago.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
