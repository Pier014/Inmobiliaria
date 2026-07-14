package gui;

import clases.*;
import gestion.GestionEmpleado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana para gestionar (CRUD) empleados 
public class frmGestionEmpleados extends JFrame {

    private GestionEmpleado gestionEmpleado;
    private JTable tabla;
    private DefaultTableModel model;

    //Constructor: configura la interfaz grafica y carga los empleados existentes 
    public frmGestionEmpleados(GestionEmpleado gestionEmpleado) {
        this.gestionEmpleado = gestionEmpleado;

        setTitle("Gestion de Empleados");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("GESTION DE EMPLEADOS");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"DNI", "Nombre", "Apellidos", "Rol", "Usuario"}, 0);
        tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton btnNuevo = new JButton("Nuevo Empleado");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(e -> nuevoEmpleado());
        btnPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Editar Empleado");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEditar.setBackground(new Color(52, 152, 219));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.addActionListener(e -> editarEmpleado());
        btnPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Empleado");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(e -> eliminarEmpleado());
        btnPanel.add(btnEliminar);

        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);
        cargarEmpleados();
    }

    //Carga los empleados desde GestionEmpleado a la tabla 
    private void cargarEmpleados() {
        model.setRowCount(0);
        for (Empleado e : gestionEmpleado.obtenerEmpleados()) {
            model.addRow(new Object[]{e.getDNI(), e.getNombres(), e.getApellidos(), e.getRol(), e.getUsuario()});
        }
    }

    //Valida que un campo de texto no este vacio 
    private String validarCampoVacio(String val, String nombre) {
        if (val == null || val.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede estar vacio.");
        }
        return val.trim();
    }

    //Abre un dialogo para crear un nuevo empleado y lo registra 
    private void nuevoEmpleado() {
        JTextField dni = new JTextField();
        JTextField nombres = new JTextField();
        JTextField apellidos = new JTextField();
        JTextField usuario = new JTextField();
        JPasswordField password = new JPasswordField();
        JComboBox<String> cmbRol = new JComboBox<>(new String[]{"Administrador", "AsesorVenta", "Gerente"});

        Object[] fields = {
            "DNI:", dni, "Nombres:", nombres, "Apellidos:", apellidos,
            "Usuario:", usuario, "Password:", password, "Rol:", cmbRol
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Nuevo Empleado",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String d = validarCampoVacio(dni.getText(), "DNI");
                String n = validarCampoVacio(nombres.getText(), "Nombres");
                String a = validarCampoVacio(apellidos.getText(), "Apellidos");
                String u = validarCampoVacio(usuario.getText(), "Usuario");
                String pwd = new String(password.getPassword());
                if (pwd.isEmpty()) {
                    throw new IllegalArgumentException("Password no puede estar vacio.");
                }
                String rol = (String) cmbRol.getSelectedItem();

                if (gestionEmpleado.existeDNI(d)) {
                    throw new IllegalArgumentException("Ya existe un empleado con DNI " + d);
                }

                Empleado emp = switch (rol) {
                    case "Administrador" -> new Administrador(d, n, a, u, pwd);
                    case "AsesorVenta" -> new AsesorVenta(d, n, a, u, pwd);
                    case "Gerente" -> new Gerente(d, n, a, u, pwd);
                    default -> throw new IllegalArgumentException("Rol no valido");
                };

                gestionEmpleado.registrar(emp);
                cargarEmpleados();
                JOptionPane.showMessageDialog(this, "Empleado agregado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Abre un dialogo para editar el empleado seleccionado 
    private void editarEmpleado() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.",
                "Editar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Empleado e = gestionEmpleado.obtenerEmpleados()[row];

        JTextField dni = new JTextField(e.getDNI());
        JTextField nombres = new JTextField(e.getNombres());
        JTextField apellidos = new JTextField(e.getApellidos());
        JTextField usuario = new JTextField(e.getUsuario());
        JPasswordField password = new JPasswordField(e.getPassword());
        JComboBox<String> cmbRol = new JComboBox<>(new String[]{"Administrador", "AsesorVenta", "Gerente"});
        cmbRol.setSelectedItem(e.getRol());

        Object[] fields = {
            "DNI:", dni, "Nombres:", nombres, "Apellidos:", apellidos,
            "Usuario:", usuario, "Password:", password, "Rol:", cmbRol
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Editar Empleado",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String d = validarCampoVacio(dni.getText(), "DNI");
                String n = validarCampoVacio(nombres.getText(), "Nombres");
                String a = validarCampoVacio(apellidos.getText(), "Apellidos");
                String u = validarCampoVacio(usuario.getText(), "Usuario");
                String p = new String(password.getPassword());
                if (p.isEmpty()) {
                    throw new IllegalArgumentException("Password no puede estar vacio.");
                }
                String rol = (String) cmbRol.getSelectedItem();

                if (!d.equals(e.getDNI()) && gestionEmpleado.existeDNI(d)) {
                    throw new IllegalArgumentException("Ya existe un empleado con DNI " + d);
                }

                Empleado emp = switch (rol) {
                    case "Administrador" -> new Administrador(d, n, a, u, p);
                    case "AsesorVenta" -> new AsesorVenta(d, n, a, u, p);
                    case "Gerente" -> new Gerente(d, n, a, u, p);
                    default -> throw new IllegalArgumentException("Rol no valido");
                };

                gestionEmpleado.actualizar(row, emp);
                cargarEmpleados();
                JOptionPane.showMessageDialog(this, "Empleado actualizado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Elimina el empleado seleccionado tras confirmacion 
    private void eliminarEmpleado() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.",
                "Eliminar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Empleado e = gestionEmpleado.obtenerEmpleados()[row];
        int confirm = JOptionPane.showConfirmDialog(this,
            "Eliminar a " + e.getNombreCompleto() + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            gestionEmpleado.eliminar(row);
            cargarEmpleados();
            JOptionPane.showMessageDialog(this, "Empleado eliminado.");
        }
    }
}
