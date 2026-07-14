package gui;

import clases.Cliente;
import gestion.GestionCliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utilitario.DatePicker;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para gestionar (CRUD) clientes 
public class frmGestionClientes extends JFrame {

    private GestionCliente gestionCliente;
    private JTable tabla;
    private DefaultTableModel model;

    //Constructor: configura la interfaz grafica y carga los clientes existentes 
    public frmGestionClientes(GestionCliente gestionCliente) {
        this.gestionCliente = gestionCliente;

        setTitle("Gestion de Clientes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("GESTION DE CLIENTES");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{
            "DNI", "Nombres", "Apellidos", "F. Nacimiento", "Estado Civil",
            "Ocupacion", "Ingresos S/", "Telefono", "Correo"}, 0);
        tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setRowHeight(25);
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton btnNuevo = new JButton("Nuevo Cliente");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(e -> nuevoCliente());
        btnPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Editar Cliente");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEditar.setBackground(new Color(52, 152, 219));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.addActionListener(e -> editarCliente());
        btnPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Cliente");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnPanel.add(btnEliminar);

        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);
        cargarClientes();
    }

    //Carga los clientes desde GestionCliente a la tabla 
    private void cargarClientes() {
        model.setRowCount(0);
        for (Cliente c : gestionCliente.obtenerClientes()) {
            model.addRow(new Object[]{
                c.getDNI(), c.getNombres(), c.getApellidos(),
                c.getFechaNacimiento(), c.getEstadoCivil(), c.getOcupacion(),
                String.format("%,.2f", c.getIngresosMensuales()),
                c.getTelefono(), c.getCorreo()
            });
        }
    }

    //Valida que un campo de texto no este vacio 
    private String validarCampoVacio(String val, String nombre) {
        if (val == null || val.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede estar vacio.");
        }
        return val.trim();
    }

    //Valida que un monto sea un numero no negativo 
    private double validarMontoNoNegativo(String val, String nombre) {
        double num;
        try {
            num = Double.parseDouble(validarCampoVacio(val, nombre));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nombre + " debe ser un numero valido.");
        }
        if (num < 0) {
            throw new IllegalArgumentException(nombre + " no puede ser negativo.");
        }
        return num;
    }

    //Abre un dialogo para crear un nuevo cliente y lo registra 
    private void nuevoCliente() {
        JTextField dni = new JTextField();
        JTextField nombres = new JTextField();
        JTextField apellidos = new JTextField();
        DatePicker fecNac = new DatePicker();
        JTextField estCivil = new JTextField();
        JTextField ocupacion = new JTextField();
        JTextField ingresos = new JTextField();
        JTextField telefono = new JTextField();
        JTextField correo = new JTextField();

        Object[] fields = {
            "DNI:", dni, "Nombres:", nombres, "Apellidos:", apellidos,
            "Fecha Nacimiento:", fecNac,
            "Estado Civil:", estCivil, "Ocupacion:", ocupacion,
            "Ingresos Mensuales:", ingresos, "Telefono:", telefono, "Correo:", correo
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Nuevo Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String d = validarCampoVacio(dni.getText(), "DNI");
                String n = validarCampoVacio(nombres.getText(), "Nombres");
                String a = validarCampoVacio(apellidos.getText(), "Apellidos");
                String fn = fecNac.getFecha();
                String ec = validarCampoVacio(estCivil.getText(), "Estado civil");
                String oc = validarCampoVacio(ocupacion.getText(), "Ocupacion");
                double ing = validarMontoNoNegativo(ingresos.getText(), "Ingresos mensuales");
                String tel = validarCampoVacio(telefono.getText(), "Telefono");
                String corr = validarCampoVacio(correo.getText(), "Correo");

                gestionCliente.registrar(new Cliente(d, n, a, fn, ec, oc, ing, tel, corr));
                cargarClientes();
                JOptionPane.showMessageDialog(this, "Cliente registrado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Abre un dialogo para editar el cliente seleccionado 
    private void editarCliente() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla.",
                "Editar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente c = gestionCliente.obtenerClientes()[row];

        JTextField dni = new JTextField(c.getDNI());
        JTextField nombres = new JTextField(c.getNombres());
        JTextField apellidos = new JTextField(c.getApellidos());
        DatePicker fecNac = new DatePicker();
        fecNac.setFecha(c.getFechaNacimiento());
        JTextField estCivil = new JTextField(c.getEstadoCivil());
        JTextField ocupacion = new JTextField(c.getOcupacion());
        JTextField ingresos = new JTextField(String.valueOf(c.getIngresosMensuales()));
        JTextField telefono = new JTextField(c.getTelefono());
        JTextField correo = new JTextField(c.getCorreo());

        Object[] fields = {
            "DNI:", dni, "Nombres:", nombres, "Apellidos:", apellidos,
            "Fecha Nacimiento:", fecNac,
            "Estado Civil:", estCivil, "Ocupacion:", ocupacion,
            "Ingresos Mensuales:", ingresos, "Telefono:", telefono, "Correo:", correo
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Editar Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String d = validarCampoVacio(dni.getText(), "DNI");
                String n = validarCampoVacio(nombres.getText(), "Nombres");
                String a = validarCampoVacio(apellidos.getText(), "Apellidos");
                String fn = fecNac.getFecha();
                String ec = validarCampoVacio(estCivil.getText(), "Estado civil");
                String oc = validarCampoVacio(ocupacion.getText(), "Ocupacion");
                double ing = validarMontoNoNegativo(ingresos.getText(), "Ingresos mensuales");
                String tel = validarCampoVacio(telefono.getText(), "Telefono");
                String corr = validarCampoVacio(correo.getText(), "Correo");

                gestionCliente.actualizar(row, new Cliente(d, n, a, fn, ec, oc, ing, tel, corr));
                cargarClientes();
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Elimina el cliente seleccionado tras confirmacion 
    private void eliminarCliente() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla.",
                "Eliminar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente c = gestionCliente.obtenerClientes()[row];
        int confirm = JOptionPane.showConfirmDialog(this,
            "Eliminar a " + c.getNombreCompleto() + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            gestionCliente.eliminar(row);
            cargarClientes();
            JOptionPane.showMessageDialog(this, "Cliente eliminado.");
        }
    }
}
