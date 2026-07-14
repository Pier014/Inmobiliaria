package gui;

import clases.Empleado;
import gestion.*;
import javax.swing.*;
import java.awt.*;
import utilitario.PanelFondo;

//Clase hija de JFrame. Ventana principal del menu con botones segun el rol del usuario 
public class frmPrincipal extends JFrame {

    private GestionEmpleado gestionEmpleado;
    private GestionCliente gestionCliente;
    private GestionProyecto gestionProyecto;
    private GestionVenta gestionVenta;
    private GestionAcabado gestionAcabado;
    private Empleado sesion;

    //Constructor: configura el menu principal mostrando opciones segun el rol del empleado 
    public frmPrincipal(GestionEmpleado gestionEmpleado, Empleado sesion) {
        this.gestionEmpleado = gestionEmpleado;
        this.gestionCliente = new GestionCliente();
        this.gestionProyecto = new GestionProyecto();
        this.gestionVenta = new GestionVenta();
        this.gestionAcabado = new GestionAcabado();
        this.sesion = sesion;

        setTitle("Inmobiliaria - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        ;

        PanelFondo panel = new PanelFondo(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(236, 240, 241));

        JLabel lblBienvenida = new JLabel(
            "<html><h1>Bienvenido, " + sesion.getNombreCompleto() + "</h1>"
            + "<p>Rol: <b>" + sesion.getRol() + "</b></p></html>");
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblBienvenida, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridBagLayout());
        botones.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String rol = sesion.getRol();
        int y = 0;

        if (rol.equals("Administrador")) {
            JButton btnEmpleados = crearBoton("Gestionar Empleados", new Color(52, 152, 219));
            btnEmpleados.addActionListener(e -> abrir(new frmGestionEmpleados(gestionEmpleado)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnEmpleados, gbc);

            JButton btnProyectos = crearBoton("Gestionar Proyectos", new Color(155, 89, 182));
            btnProyectos.addActionListener(e -> abrir(new frmGestionProyectos(gestionProyecto)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnProyectos, gbc);

            JButton btnAcabados = crearBoton("Gestionar Acabados", new Color(230, 126, 34));
            btnAcabados.addActionListener(e -> abrir(new frmGestionAcabados(gestionAcabado)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnAcabados, gbc);
        }

        if (rol.equals("AsesorVenta") || rol.equals("Administrador")) {
            JButton btnClientes = crearBoton("Gestionar Clientes", new Color(46, 204, 113));
            btnClientes.addActionListener(e -> abrir(new frmGestionClientes(gestionCliente)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnClientes, gbc);

            JButton btnReservas = crearBoton("Registrar Reserva", new Color(52, 152, 219));
            btnReservas.addActionListener(e -> abrir(new frmRegistroReserva(gestionCliente, gestionProyecto)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnReservas, gbc);

            JButton btnVentas = crearBoton("Registrar Venta", new Color(231, 76, 60));
            btnVentas.addActionListener(e -> abrir(new frmRegistroVenta(gestionCliente, gestionProyecto, gestionVenta)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnVentas, gbc);

            JButton btnPagos = crearBoton("Registrar Pago de Cuota", new Color(243, 156, 18));
            btnPagos.addActionListener(e -> abrir(new frmRegistroPago(gestionVenta)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnPagos, gbc);
        }

        if (rol.equals("Gerente") || rol.equals("Administrador")) {
            JButton btnReportes = crearBoton("Ver Reportes", new Color(142, 68, 173));
            btnReportes.addActionListener(e -> abrir(new frmReportes(gestionProyecto, gestionVenta, gestionEmpleado)));
            gbc.gridx = 0; gbc.gridy = y++; botones.add(btnReportes, gbc);
        }

        panel.add(botones, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar Sesion");
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrar.addActionListener(e -> cerrarSesion());
        JPanel sur = new JPanel();
        sur.setOpaque(false);
        sur.add(btnCerrar);
        panel.add(sur, BorderLayout.SOUTH);

        add(panel);
    }

    //Crea un boton estilizado con texto y color de fondo 
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(280, 45));
        return btn;
    }

    //Abre una ventana hija ocultando la ventana principal y la muestra al cerrarse 
    private void abrir(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                setVisible(true);
            }
        });
        setVisible(false);
        frame.setVisible(true);
    }

    //Cierra la sesion actual y regresa a la ventana de login 
    private void cerrarSesion() {
        dispose();
        new frmLogin(gestionEmpleado).setVisible(true);
    }
}
