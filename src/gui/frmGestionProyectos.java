package gui;

import clases.Departamento;
import clases.ProyectosConstruccion;
import gestion.GestionProyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utilitario.DatePicker;
import utilitario.PanelFondo;
import java.util.Arrays;

//Clase hija de JFrame. Ventana para gestionar proyectos y sus departamentos 
public class frmGestionProyectos extends JFrame {

    private GestionProyecto gestionProyecto;
    private JComboBox<ProyectosConstruccion> cmbProyectos;
    private JTable tablaDptos;
    private DefaultTableModel modelDptos;

    //Constructor: configura la interfaz grafica y carga los proyectos existentes 
    public frmGestionProyectos(GestionProyecto gestionProyecto) {
        this.gestionProyecto = gestionProyecto;

        setTitle("Gestion de Proyectos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(java.awt.event.WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("PROYECTOS Y DEPARTAMENTOS");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        topPanel.add(new JLabel("Proyecto:"));
        cmbProyectos = new JComboBox<>();
        cmbProyectos.setPreferredSize(new Dimension(220, 25));
        topPanel.add(cmbProyectos);

        JButton btnNuevoProy = new JButton("+ Proyecto");
        btnNuevoProy.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevoProy.setBackground(new Color(46, 204, 113));
        btnNuevoProy.setForeground(Color.WHITE);
        btnNuevoProy.setFocusPainted(false);
        btnNuevoProy.addActionListener(e -> nuevoProyecto());
        topPanel.add(btnNuevoProy);

        JButton btnEditarProy = new JButton("Editar Proyecto");
        btnEditarProy.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditarProy.setBackground(new Color(52, 152, 219));
        btnEditarProy.setForeground(Color.WHITE);
        btnEditarProy.setFocusPainted(false);
        btnEditarProy.addActionListener(e -> editarProyecto());
        topPanel.add(btnEditarProy);

        JButton btnEliminarProy = new JButton("Eliminar Proyecto");
        btnEliminarProy.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminarProy.setBackground(new Color(231, 76, 60));
        btnEliminarProy.setForeground(Color.WHITE);
        btnEliminarProy.setFocusPainted(false);
        btnEliminarProy.addActionListener(e -> eliminarProyecto());
        topPanel.add(btnEliminarProy);

        panel.add(topPanel, BorderLayout.NORTH);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split.setDividerLocation(120);

        JTextArea txtInfo = new JTextArea(4, 0);
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtInfo.setBackground(new Color(240, 240, 240));
        split.setTopComponent(new JScrollPane(txtInfo));

        modelDptos = new DefaultTableModel(
            new String[]{"Codigo", "Piso", "N°", "Area m²", "Dorm.", "Baños", "Tipo", "Precio S/", "Estado"}, 0);
        tablaDptos = new JTable(modelDptos);
        tablaDptos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaDptos.setRowHeight(25);
        tablaDptos.setEnabled(false);
        split.setBottomComponent(new JScrollPane(tablaDptos));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNuevoDepto = new JButton("+ Nuevo Departamento");
        btnNuevoDepto.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevoDepto.setBackground(new Color(46, 204, 113));
        btnNuevoDepto.setForeground(Color.WHITE);
        btnNuevoDepto.setFocusPainted(false);
        btnNuevoDepto.addActionListener(e -> nuevoDepartamento());
        bottomPanel.add(btnNuevoDepto);

        JButton btnCambiarEstado = new JButton("Cambiar Estado");
        btnCambiarEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCambiarEstado.setBackground(new Color(243, 156, 18));
        btnCambiarEstado.setForeground(Color.WHITE);
        btnCambiarEstado.setFocusPainted(false);
        btnCambiarEstado.addActionListener(e -> cambiarEstadoDepartamento());
        bottomPanel.add(btnCambiarEstado);

        panel.add(bottomPanel, BorderLayout.SOUTH);
        panel.add(split, BorderLayout.CENTER);

        add(panel);
        cargarProyectos();
        cmbProyectos.addActionListener(e -> mostrarDepartamentos(txtInfo));
    }

    //Carga los proyectos en el JComboBox y muestra los departamentos del primero 
    private void cargarProyectos() {
        cmbProyectos.removeAllItems();
        for (ProyectosConstruccion p : gestionProyecto.obtenerProyectos()) {
            cmbProyectos.addItem(p);
        }
        if (cmbProyectos.getItemCount() > 0) {
            mostrarDepartamentos(null);
        }
    }

    //Muestra la informacion del proyecto seleccionado y sus departamentos en la tabla 
    private void mostrarDepartamentos(JTextArea txtInfo) {
        ProyectosConstruccion p = (ProyectosConstruccion) cmbProyectos.getSelectedItem();
        if (p == null) return;

        if (txtInfo != null) {
            txtInfo.setText(
                "Nombre: " + p.getNombre() + "\n" +
                "Direccion: " + p.getDireccion() + ", " + p.getDistrito() + "\n" +
                "Estado: " + p.getEstado() + " | Pisos: " + p.getNumeroPisos() + "\n" +
                "F. Inicio: " + p.getFechaInicioObra() + " | F. Entrega: " + p.getFechaEstimadaEntrega()
            );
        }

        modelDptos.setRowCount(0);
        for (Departamento d : p.getDepartamentos()) {
            modelDptos.addRow(new Object[]{
                d.getCodigo(), d.getPiso(), d.getNumero(), d.getAreaM2(),
                d.getDormitorios(), d.getBanios(), d.getTipo(),
                String.format("%,.2f", d.getPrecioBase()), d.getEstado()
            });
        }
    }

    //Abre un dialogo para crear un nuevo proyecto y lo registra 
    private void nuevoProyecto() {
        JTextField nombre = new JTextField();
        JTextField direccion = new JTextField();
        JTextField distrito = new JTextField();
        JTextField pisos = new JTextField();
        DatePicker fInicio = new DatePicker();
        DatePicker fEntrega = new DatePicker();
        JComboBox<String> estado = new JComboBox<>(new String[]{"En planos", "En construccion", "Terminado", "Entregado"});
        JTextField capacidad = new JTextField("20");

        Object[] fields = {
            "Nombre:", nombre, "Direccion:", direccion, "Distrito:", distrito,
            "N° Pisos:", pisos, "Fecha inicio:", fInicio,
            "Fecha entrega:", fEntrega, "Estado:", estado,
            "Capacidad deptos:", capacidad
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Nuevo Proyecto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String nom = validarCampoVacio(nombre.getText(), "Nombre");
                String dir = validarCampoVacio(direccion.getText(), "Direccion");
                String dist = validarCampoVacio(distrito.getText(), "Distrito");
                int npisos = validarEnteroPositivo(pisos.getText(), "N° Pisos");
                String fIni = fInicio.getFecha();
                String fEnt = fEntrega.getFecha();
                int cap = validarEnteroPositivo(capacidad.getText(), "Capacidad");

                gestionProyecto.registrar(new ProyectosConstruccion(
                    nom, dir, dist, npisos, fIni, fEnt,
                    (String) estado.getSelectedItem(), cap
                ));
                cargarProyectos();
                JOptionPane.showMessageDialog(this, "Proyecto creado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Abre un dialogo para editar el proyecto seleccionado 
    private void editarProyecto() {
        int idx = cmbProyectos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProyectosConstruccion p = gestionProyecto.obtenerProyectos()[idx];

        JTextField nombre = new JTextField(p.getNombre());
        JTextField direccion = new JTextField(p.getDireccion());
        JTextField distrito = new JTextField(p.getDistrito());
        JTextField pisos = new JTextField(String.valueOf(p.getNumeroPisos()));
        DatePicker fInicio = new DatePicker();
        fInicio.setFecha(p.getFechaInicioObra());
        DatePicker fEntrega = new DatePicker();
        fEntrega.setFecha(p.getFechaEstimadaEntrega());
        JComboBox<String> estado = new JComboBox<>(new String[]{"En planos", "En construccion", "Terminado", "Entregado"});
        estado.setSelectedItem(p.getEstado());

        Object[] fields = {
            "Nombre:", nombre, "Direccion:", direccion, "Distrito:", distrito,
            "N° Pisos:", pisos, "Fecha inicio:", fInicio,
            "Fecha entrega:", fEntrega, "Estado:", estado
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Editar Proyecto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String nom = validarCampoVacio(nombre.getText(), "Nombre");
                String dir = validarCampoVacio(direccion.getText(), "Direccion");
                String dist = validarCampoVacio(distrito.getText(), "Distrito");
                int npisos = validarEnteroPositivo(pisos.getText(), "N° Pisos");
                String fIni = fInicio.getFecha();
                String fEnt = fEntrega.getFecha();

                ProyectosConstruccion pAct = new ProyectosConstruccion(
                    nom, dir, dist, npisos, fIni, fEnt,
                    (String) estado.getSelectedItem(),
                    p.getDepartamentos().length
                );
                for (Departamento d : p.getDepartamentos()) {
                    pAct.agregarDepartamento(d);
                }
                gestionProyecto.actualizar(idx, pAct);
                cargarProyectos();
                JOptionPane.showMessageDialog(this, "Proyecto actualizado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Elimina el proyecto seleccionado tras confirmacion 
    private void eliminarProyecto() {
        int idx = cmbProyectos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProyectosConstruccion p = gestionProyecto.obtenerProyectos()[idx];
        int confirm = JOptionPane.showConfirmDialog(this,
            "Eliminar el proyecto \"" + p.getNombre() + "\" y todos sus departamentos?",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            gestionProyecto.eliminar(idx);
            cargarProyectos();
            JOptionPane.showMessageDialog(this, "Proyecto eliminado.");
        }
    }

    //Valida que un campo de texto no este vacio 
    private String validarCampoVacio(String val, String nombre) {
        if (val == null || val.trim().isEmpty()) {
            throw new IllegalArgumentException(nombre + " no puede estar vacio.");
        }
        return val.trim();
    }

    //Valida que un valor sea un entero no negativo 
    private int validarEnteroPositivo(String val, String nombre) {
        int num;
        try {
            num = Integer.parseInt(validarCampoVacio(val, nombre));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(nombre + " debe ser un numero entero valido.");
        }
        if (num < 0) {
            throw new IllegalArgumentException(nombre + " no puede ser negativo.");
        }
        return num;
    }

    //Valida que un valor sea un numero positivo 
    private double validarDoublePositivo(String val, String nombre) {
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

    //Abre un dialogo para crear un nuevo departamento en el proyecto seleccionado 
    private void nuevoDepartamento() {
        int idx = cmbProyectos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto primero.",
                "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProyectosConstruccion p = gestionProyecto.obtenerProyectos()[idx];

        JTextField codigo = new JTextField();
        JTextField piso = new JTextField();
        JTextField numero = new JTextField();
        JTextField area = new JTextField();
        JTextField dormitorios = new JTextField();
        JTextField banios = new JTextField();
        JComboBox<String> tipo = new JComboBox<>(new String[]{"Flat", "Duplex", "Penthouse"});
        JTextField precio = new JTextField();

        Object[] fields = {
            "Codigo:", codigo, "Piso:", piso, "Numero:", numero,
            "Area (m²):", area, "Dormitorios:", dormitorios,
            "Baños:", banios, "Tipo:", tipo, "Precio Base S/:", precio
        };

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, fields, "Nuevo Departamento en " + p.getNombre(),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            try {
                String cod = validarCampoVacio(codigo.getText(), "Codigo");
                int npiso = validarEnteroPositivo(piso.getText(), "Piso");
                int nro = validarEnteroPositivo(numero.getText(), "Numero");
                double areaVal = validarDoublePositivo(area.getText(), "Area");
                int dorm = validarEnteroPositivo(dormitorios.getText(), "Dormitorios");
                int ban = validarEnteroPositivo(banios.getText(), "Baños");
                double precioVal = validarDoublePositivo(precio.getText(), "Precio");

                gestionProyecto.agregarDepartamento(idx, new Departamento(
                    cod, npiso, nro, areaVal, dorm, ban,
                    (String) tipo.getSelectedItem(), precioVal
                ));
                mostrarDepartamentos(null);
                JOptionPane.showMessageDialog(this, "Departamento agregado exitosamente.");
                return;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error de validacion", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Permite cambiar el estado de un departamento del proyecto seleccionado 
    private void cambiarEstadoDepartamento() {
        int idx = cmbProyectos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ProyectosConstruccion p = gestionProyecto.obtenerProyectos()[idx];
        Departamento[] deptos = p.getDepartamentos();
        if (deptos.length == 0) {
            JOptionPane.showMessageDialog(this, "No hay departamentos en este proyecto.",
                "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] opciones = Arrays.stream(deptos)
            .map(d -> d.getCodigo() + " - " + d.getEstado())
            .toArray(String[]::new);

        String seleccion = (String) JOptionPane.showInputDialog(this,
            "Seleccione el departamento:", "Cambiar Estado",
            JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == null) return;

        String codDep = seleccion.split(" - ")[0];
        Departamento dep = p.buscarDepartamento(codDep);
        if (dep == null) return;

        String[] estados = {"Disponible", "Reservado", "Vendido"};
        String nuevoEstado = (String) JOptionPane.showInputDialog(this,
            "Nuevo estado para " + codDep + ":", "Cambiar Estado",
            JOptionPane.PLAIN_MESSAGE, null, estados, dep.getEstado());

        if (nuevoEstado != null && !nuevoEstado.equals(dep.getEstado())) {
            dep.setEstado(nuevoEstado);
            mostrarDepartamentos(null);
            JOptionPane.showMessageDialog(this,
                "Estado de " + codDep + " cambiado a: " + nuevoEstado);
        }
    }
}
