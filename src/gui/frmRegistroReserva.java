package gui;

import clases.*;
import gestion.*;
import javax.swing.*;
import java.awt.*;
import utilitario.DatePicker;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para registrar una reserva de departamento 
public class frmRegistroReserva extends JFrame {

    private GestionCliente gestionCliente;
    private GestionProyecto gestionProyecto;
    private JComboBox<Cliente> cmbCliente;
    private JComboBox<ProyectosConstruccion> cmbProyecto;
    private JComboBox<String> cmbDepartamento;
    private JTextField txtMonto;
    private DatePicker dpFechaReserva, dpFechaVigencia;

    //Constructor: configura la interfaz grafica para el registro de reservas 
    public frmRegistroReserva(GestionCliente gestionCliente, GestionProyecto gestionProyecto) {
        this.gestionCliente = gestionCliente;
        this.gestionProyecto = gestionProyecto;

        setTitle("Registrar Reserva");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("REGISTRAR RESERVA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
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
        form.add(new JLabel("Proyecto:"), gbc);
        cmbProyecto = new JComboBox<>();
        cmbProyecto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ProyectosConstruccion p) {
                    setText(p.getNombre() + " - " + p.getDistrito());
                }
                return this;
            }
        });
        cmbProyecto.setPreferredSize(new Dimension(250, 25));
        gbc.gridx = 1;
        form.add(cmbProyecto, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Departamento:"), gbc);
        cmbDepartamento = new JComboBox<>();
        cmbDepartamento.setPreferredSize(new Dimension(250, 25));
        gbc.gridx = 1;
        form.add(cmbDepartamento, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Monto separacion:"), gbc);
        txtMonto = new JTextField(15);
        txtMonto.setBackground(Color.WHITE);
        gbc.gridx = 1;
        form.add(txtMonto, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Fecha reserva:"), gbc);
        dpFechaReserva = new DatePicker();
        gbc.gridx = 1;
        form.add(dpFechaReserva, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        form.add(new JLabel("Fecha vigencia:"), gbc);
        dpFechaVigencia = new DatePicker();
        gbc.gridx = 1;
        form.add(dpFechaVigencia, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        JButton btnGuardar = new JButton("Registrar Reserva");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(e -> registrarReserva());
        form.add(btnGuardar, gbc);

        panel.add(form, BorderLayout.CENTER);

        add(panel);
        cargarCombos();
        cmbProyecto.addActionListener(e -> cargarDepartamentos());
    }

    //Carga los clientes y proyectos disponibles en los combos 
    private void cargarCombos() {
        cmbCliente.removeAllItems();
        for (Cliente c : gestionCliente.obtenerClientes()) cmbCliente.addItem(c);

        cmbProyecto.removeAllItems();
        for (ProyectosConstruccion p : gestionProyecto.obtenerProyectos()) cmbProyecto.addItem(p);

        cargarDepartamentos();
    }

    //Carga los departamentos disponibles del proyecto seleccionado 
    private void cargarDepartamentos() {
        cmbDepartamento.removeAllItems();
        ProyectosConstruccion p = (ProyectosConstruccion) cmbProyecto.getSelectedItem();
        if (p != null) {
            for (Departamento d : p.getDepartamentos()) {
                if (d.getEstado().equals("Disponible")) {
                    cmbDepartamento.addItem(d.toString());
                }
            }
        }
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

    //Registra la reserva con los datos ingresados en el formulario 
    private void registrarReserva() {
        if (cmbCliente.getSelectedItem() == null || cmbDepartamento.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay clientes o departamentos disponibles.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
            ProyectosConstruccion p = (ProyectosConstruccion) cmbProyecto.getSelectedItem();
            String codDep = cmbDepartamento.getSelectedItem().toString().split(" - ")[0];
            Departamento dep = p.buscarDepartamento(codDep);

            double monto = validarMontoPositivo(txtMonto, "Monto de separacion");
            String fecha = dpFechaReserva.getFecha();
            String vigencia = dpFechaVigencia.getFecha();

            if (fecha.compareTo(vigencia) > 0) {
                JOptionPane.showMessageDialog(this,
                    "La fecha de vigencia debe ser posterior a la fecha de reserva.",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new Reserva(cliente, dep, monto, fecha, vigencia);
            JOptionPane.showMessageDialog(this, "Reserva registrada exitosamente.");
            cargarDepartamentos();
            txtMonto.setText("");
            txtMonto.setBackground(Color.WHITE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                "Error de validacion", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
