package gui;

import gestion.GestionEmpleado;
import javax.swing.*;
import java.awt.*;
import utilitario.PanelFondo;

public class frmLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private GestionEmpleado gestionEmpleado;

    public frmLogin() {
        this(new GestionEmpleado());
    }

    public frmLogin(GestionEmpleado gestionEmpleado) {
        this.gestionEmpleado = gestionEmpleado;

        setTitle("Inmobiliaria - Inicio de Sesion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);

        PanelFondo panel = new PanelFondo(new GridBagLayout());
        panel.setBackground(new Color(45, 62, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("SISTEMA INMOBILIARIA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        JLabel lblUser = new JLabel("Usuario:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(lblUser, gbc);

        txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtUsuario, gbc);

        JLabel lblPass = new JLabel("Contrasena:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblPass, gbc);

        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtPassword, gbc);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setBackground(new Color(52, 152, 219));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnLogin, gbc);

        btnLogin.addActionListener(e -> login());
        txtPassword.addActionListener(e -> login());

        add(panel);
    }

    private void login() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y contrasena.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        clases.Empleado emp = gestionEmpleado.validarLogin(user, pass);
        if (emp != null) {
            frmPrincipal menu = new frmPrincipal(gestionEmpleado, emp);
            menu.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contrasena incorrectos.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
