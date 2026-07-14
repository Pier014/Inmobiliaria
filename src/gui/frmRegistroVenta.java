package gui;

import clases.*;
import gestion.*;
import javax.swing.*;
import java.awt.*;
import utilitario.DatePicker;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para registrar una venta de departamento 
public class frmRegistroVenta extends JFrame {

    private GestionCliente gestionCliente;
    private GestionProyecto gestionProyecto;
    private GestionVenta gestionVenta;
    private JComboBox<Cliente> cmbCliente;
    private JTextField txtCodDep, txtModalidad;
    private DatePicker dpFechaVenta;
    private JCheckBox chkCuotas;
    private JTextField txtCuotaInicial, txtNumCuotas, txtMontoCuota;
    private JTextArea txtFechas;
    private JPanel panelCuotas;

    //Constructor: configura la interfaz grafica para el registro de ventas 
    public frmRegistroVenta(GestionCliente gestionCliente, GestionProyecto gestionProyecto,
                             GestionVenta gestionVenta) {
        this.gestionCliente = gestionCliente;
        this.gestionProyecto = gestionProyecto;
        this.gestionVenta = gestionVenta;

        setTitle("Registrar Venta");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REGISTRAR VENTA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Codigo departamento (reservado):"), gbc);
        txtCodDep = new JTextField(15);
        gbc.gridx = 1;
        form.add(txtCodDep, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Cliente:"), gbc);
        cmbCliente = new JComboBox<>();
        cmbCliente.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cliente c) {
                    setText(c.getNombreCompleto() + " (" + c.getDNI() + ")");
                }
                return this;
            }
        });
        cmbCliente.setPreferredSize(new Dimension(250, 25));
        gbc.gridx = 1;
        form.add(cmbCliente, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Modalidad (contado/financiamiento/cuotas directas):"), gbc);
        txtModalidad = new JTextField(15);
        gbc.gridx = 1;
        form.add(txtModalidad, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Fecha venta:"), gbc);
        dpFechaVenta = new DatePicker();
        gbc.gridx = 1;
        form.add(dpFechaVenta, gbc);

        y++;
        chkCuotas = new JCheckBox("Cuotas directas");
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        form.add(chkCuotas, gbc);

        panelCuotas = new JPanel(new GridBagLayout());
        panelCuotas.setBorder(BorderFactory.createTitledBorder("Cronograma de Cuotas"));
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(2, 2, 2, 2);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gbc2.gridx = 0; gbc2.gridy = 0;
        panelCuotas.add(new JLabel("Cuota inicial:"), gbc2);
        txtCuotaInicial = new JTextField(8);
        gbc2.gridx = 1;
        panelCuotas.add(txtCuotaInicial, gbc2);

        gbc2.gridx = 2;
        panelCuotas.add(new JLabel("N° cuotas:"), gbc2);
        txtNumCuotas = new JTextField(5);
        gbc2.gridx = 3;
        panelCuotas.add(txtNumCuotas, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 1;
        panelCuotas.add(new JLabel("Monto por cuota:"), gbc2);
        txtMontoCuota = new JTextField(8);
        gbc2.gridx = 1;
        panelCuotas.add(txtMontoCuota, gbc2);

        gbc2.gridx = 0; gbc2.gridy = 2; gbc2.gridwidth = 4;
        panelCuotas.add(new JLabel("Fechas (una por linea, primera = cuota inicial):"), gbc2);

        txtFechas = new JTextArea(4, 25);
        txtFechas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc2.gridy = 3;
        panelCuotas.add(new JScrollPane(txtFechas), gbc2);

        panelCuotas.setVisible(false);
        chkCuotas.addActionListener(e -> panelCuotas.setVisible(chkCuotas.isSelected()));

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        form.add(panelCuotas, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        JButton btnGuardar = new JButton("Registrar Venta");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(231, 76, 60));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> registrarVenta());
        form.add(btnGuardar, gbc);

        panel.add(form, BorderLayout.CENTER);

        add(panel);
        cargarClientes();
    }

    //Carga los clientes disponibles en el combo 
    private void cargarClientes() {
        cmbCliente.removeAllItems();
        for (Cliente c : gestionCliente.obtenerClientes()) cmbCliente.addItem(c);
    }

    //Valida que un campo de texto no este vacio y resalta el campo en rojo si lo esta 
    private String validarCampoVacio(JTextField campo, String nombre) {
        String val = campo.getText().trim();
        if (val.isEmpty()) {
            campo.requestFocus();
            campo.setBackground(new Color(255, 200, 200));
            throw new IllegalArgumentException(nombre + " no puede estar vacio.");
        }
        campo.setBackground(Color.WHITE);
        return val;
    }

    //Valida que un monto sea un numero positivo 
    private double validarMontoPositivo(JTextField campo, String nombre) {
        double val = Double.parseDouble(validarCampoVacio(campo, nombre));
        if (val <= 0) {
            campo.requestFocus();
            campo.setBackground(new Color(255, 200, 200));
            throw new IllegalArgumentException(nombre + " debe ser mayor a cero.");
        }
        campo.setBackground(Color.WHITE);
        return val;
    }

    //Registra la venta con los datos ingresados y genera el contrato 
    private void registrarVenta() {
        try {
            String cod = validarCampoVacio(txtCodDep, "Codigo de departamento");
            Departamento dep = gestionProyecto.buscarDepartamento(cod);
            if (dep == null || !dep.getEstado().equals("Reservado")) {
                JOptionPane.showMessageDialog(this,
                    "Departamento no reservado o no encontrado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un cliente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String modalidad = validarCampoVacio(txtModalidad, "Modalidad de pago");
            if (!modalidad.equals("contado") && !modalidad.equals("financiamiento")
                && !modalidad.equals("cuotas directas")) {
                JOptionPane.showMessageDialog(this,
                    "Modalidad debe ser: contado, financiamiento o cuotas directas.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String fecha = dpFechaVenta.getFecha();

            Venta v = new Venta(cliente, dep, null, modalidad, fecha);

            if (chkCuotas.isSelected()) {
                double inicial = validarMontoPositivo(txtCuotaInicial, "Cuota inicial");
                int numCuotas = (int) validarMontoPositivo(txtNumCuotas, "Numero de cuotas");
                double montoCuota = validarMontoPositivo(txtMontoCuota, "Monto por cuota");
                String[] lineas = txtFechas.getText().split("\n");
                String[] fechas = new String[numCuotas + 1];
                for (int i = 0; i <= numCuotas && i < lineas.length; i++) {
                    fechas[i] = lineas[i].trim();
                }
                v.registrarCronograma(inicial, numCuotas, montoCuota, fechas);
            }

            gestionVenta.registrar(v);

            ProyectosConstruccion[] proyectos = gestionProyecto.obtenerProyectos();
            Contrato c = new Contrato(v, proyectos.length > 0 ? proyectos[0] : null);
            JTextArea txtContrato = new JTextArea(c.generarReporte());
            txtContrato.setEditable(false);
            txtContrato.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JOptionPane.showMessageDialog(this, new JScrollPane(txtContrato),
                "CONTRATO DE COMPRA-VENTA", JOptionPane.INFORMATION_MESSAGE);

            txtCodDep.setText("");
            txtModalidad.setText("");
            txtCuotaInicial.setText("");
            txtNumCuotas.setText("");
            txtMontoCuota.setText("");
            txtFechas.setText("");
            txtCodDep.setBackground(Color.WHITE);
            txtModalidad.setBackground(Color.WHITE);
            txtCuotaInicial.setBackground(Color.WHITE);
            txtNumCuotas.setBackground(Color.WHITE);
            txtMontoCuota.setBackground(Color.WHITE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                "Error de validacion", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
