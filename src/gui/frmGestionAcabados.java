package gui;

import clases.AcabadoOpcional;
import gestion.GestionAcabado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utilitario.PanelFondo;

public class frmGestionAcabados extends JFrame {

    private GestionAcabado gestionAcabado;
    private JTable tabla;
    private DefaultTableModel model;

    public frmGestionAcabados(GestionAcabado gestionAcabado) {
        this.gestionAcabado = gestionAcabado;

        setTitle("Acabados Opcionales");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("ACABADOS OPCIONALES");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Nombre", "Descripcion", "Precio Adicional S/"}, 0);
        tabla = new JTable(model);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        JButton btnNuevo = new JButton("Nuevo Acabado");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(e -> nuevoAcabado());
        btnPanel.add(btnNuevo);

        JButton btnEditar = new JButton("Editar Acabado");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEditar.setBackground(new Color(52, 152, 219));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.addActionListener(e -> editarAcabado());
        btnPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Acabado");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(e -> eliminarAcabado());
        btnPanel.add(btnEliminar);

        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);
        cargarAcabados();
    }

    private void cargarAcabados() {
        model.setRowCount(0);
        for (AcabadoOpcional a : gestionAcabado.obtenerAcabados()) {
            model.addRow(new Object[]{
                a.getNombre(), a.getDescripcion(),
                String.format("%,.2f", a.getPrecioAdicional())
            });
        }
    }

    private String validarCampoVacio(String val, String nombre) {
        if (val == null || val.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede estar vacio.");
        }
        return val.trim();
    }

    private double validarMontoPositivo(String val, String nombre) {
        double num;
        try {
            num = Double.parseDouble(validarCampoVacio(val, nombre));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nombre + " debe ser un numero valido.");
        }
        if (num <= 0) {
            throw new IllegalArgumentException(nombre + " debe ser mayor a cero.");
        }
        return num;
    }

    private void nuevoAcabado() {
        JTextField nombre = new JTextField();
        JTextField descripcion = new JTextField();
        JTextField precio = new JTextField();

        Object[] fields = {"Nombre:", nombre, "Descripcion:", descripcion, "Precio Adicional S/:", precio};

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Nuevo Acabado",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String n = validarCampoVacio(nombre.getText(), "Nombre");
                String d = validarCampoVacio(descripcion.getText(), "Descripcion");
                double p = validarMontoPositivo(precio.getText(), "Precio adicional");

                gestionAcabado.registrar(new AcabadoOpcional(n, d, p));
                cargarAcabados();
                JOptionPane.showMessageDialog(this, "Acabado agregado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarAcabado() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un acabado de la tabla.",
                "Editar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        AcabadoOpcional a = gestionAcabado.obtenerAcabados()[row];

        JTextField nombre = new JTextField(a.getNombre());
        JTextField descripcion = new JTextField(a.getDescripcion());
        JTextField precio = new JTextField(String.valueOf(a.getPrecioAdicional()));

        Object[] fields = {"Nombre:", nombre, "Descripcion:", descripcion, "Precio Adicional S/:", precio};

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Editar Acabado",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String n = validarCampoVacio(nombre.getText(), "Nombre");
                String d = validarCampoVacio(descripcion.getText(), "Descripcion");
                double p = validarMontoPositivo(precio.getText(), "Precio adicional");

                gestionAcabado.actualizar(row, new AcabadoOpcional(n, d, p));
                cargarAcabados();
                JOptionPane.showMessageDialog(this, "Acabado actualizado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarAcabado() {
        int row = tabla.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un acabado de la tabla.",
                "Eliminar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
            "Eliminar el acabado \"" + gestionAcabado.obtenerAcabados()[row].getNombre() + "\"?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            gestionAcabado.eliminar(row);
            cargarAcabados();
            JOptionPane.showMessageDialog(this, "Acabado eliminado.");
        }
    }
}
