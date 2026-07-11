package utilitario;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DatePicker extends JTextField {

    private String fechaSeleccionada;
    private JPopupMenu popup;

    public DatePicker() {
        this(new Date());
    }

    public DatePicker(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fechaSeleccionada = sdf.format(fecha);
        setText(fechaSeleccionada);
        setEditable(false);
        setPreferredSize(new Dimension(150, 25));
        setHorizontalAlignment(CENTER);
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarCalendario();
            }
        });
    }

    public String getFecha() {
        return fechaSeleccionada;
    }

    public void setFecha(String fecha) {
        this.fechaSeleccionada = fecha;
        setText(fecha);
    }

    private void mostrarCalendario() {
        popup = new JPopupMenu();
        popup.setBorder(new LineBorder(new Color(52, 152, 219), 2));

        Calendar cal = Calendar.getInstance();
        if (fechaSeleccionada != null) {
            try {
                String[] parts = fechaSeleccionada.split("-");
                cal.set(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
            } catch (Exception e) {
            }
        }

        int anioActual = cal.get(Calendar.YEAR);
        int mesActual = cal.get(Calendar.MONTH);

        JPanel panelCalendario = new JPanel(new BorderLayout(5, 5));
        panelCalendario.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panelCalendario.setBackground(new Color(255, 255, 255));

        JPanel panelHeader = new JPanel(new BorderLayout());
        panelHeader.setOpaque(false);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        final int[] refAnio = {anioActual};
        final int[] refMes = {mesActual};

        JPanel panelNavegacion = new JPanel(new BorderLayout());
        panelNavegacion.setOpaque(false);

        JPanel panelMes = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 0));
        panelMes.setOpaque(false);

        JButton btnAnioAnt = new JButton("<<");
        btnAnioAnt.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAnioAnt.setPreferredSize(new Dimension(45, 28));
        btnAnioAnt.setFocusPainted(false);

        JButton btnMesAnt = new JButton("<");
        btnMesAnt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnMesAnt.setPreferredSize(new Dimension(35, 28));
        btnMesAnt.setFocusPainted(false);

        JLabel lblMes = new JLabel();
        lblMes.setHorizontalAlignment(SwingConstants.CENTER);
        lblMes.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblMes.setToolTipText("Click para cambiar mes");

        JButton btnMesSig = new JButton(">");
        btnMesSig.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnMesSig.setPreferredSize(new Dimension(35, 28));
        btnMesSig.setFocusPainted(false);

        JButton btnAnioSig = new JButton(">>");
        btnAnioSig.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAnioSig.setPreferredSize(new Dimension(45, 28));
        btnAnioSig.setFocusPainted(false);

        panelMes.add(btnAnioAnt);
        panelMes.add(btnMesAnt);
        panelMes.add(lblMes);
        panelMes.add(btnMesSig);
        panelMes.add(btnAnioSig);

        panelNavegacion.add(panelMes, BorderLayout.NORTH);

        JLabel lblAnio = new JLabel(String.valueOf(refAnio[0]), SwingConstants.CENTER);
        lblAnio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblAnio.setForeground(new Color(52, 152, 219));
        lblAnio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblAnio.setToolTipText("Click para cambiar ano rapido");

        JPanel panelAnio = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAnio.setOpaque(false);
        panelAnio.add(new JLabel("Ano: "));
        panelAnio.add(lblAnio);
        panelNavegacion.add(panelAnio, BorderLayout.SOUTH);

        panelHeader.add(panelNavegacion, BorderLayout.CENTER);

        JPanel panelDias = new JPanel(new GridLayout(7, 7, 2, 2));
        panelDias.setOpaque(false);

        String[] nombresDias = {"Lu", "Ma", "Mi", "Ju", "Vi", "Sa", "Do"};

        Runnable refrescarCalendario = () -> {
            lblMes.setText(meses[refMes[0]]);
            lblAnio.setText(String.valueOf(refAnio[0]));
            panelDias.removeAll();

            for (String nombre : nombresDias) {
                JLabel lbl = new JLabel(nombre, SwingConstants.CENTER);
                lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
                lbl.setForeground(new Color(100, 100, 100));
                panelDias.add(lbl);
            }

            Calendar temp = Calendar.getInstance();
            temp.set(refAnio[0], refMes[0], 1);
            int diaInicio = temp.get(Calendar.DAY_OF_WEEK) - 2;
            if (diaInicio < 0) diaInicio = 6;
            int diasEnMes = temp.getActualMaximum(Calendar.DAY_OF_MONTH);

            for (int i = 0; i < diaInicio; i++) {
                panelDias.add(new JLabel(""));
            }

            String hoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            for (int dia = 1; dia <= diasEnMes; dia++) {
                JButton btnDia = new JButton(String.valueOf(dia));
                btnDia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                btnDia.setFocusPainted(false);
                btnDia.setBorderPainted(false);
                btnDia.setContentAreaFilled(false);

                String fechaCandidata = String.format("%04d-%02d-%02d", refAnio[0], refMes[0] + 1, dia);

                if (fechaCandidata.equals(fechaSeleccionada)) {
                    btnDia.setBackground(new Color(52, 152, 219));
                    btnDia.setForeground(Color.WHITE);
                    btnDia.setOpaque(true);
                } else if (fechaCandidata.equals(hoy)) {
                    btnDia.setBackground(new Color(230, 230, 230));
                    btnDia.setOpaque(true);
                }

                btnDia.addActionListener(e -> {
                    fechaSeleccionada = fechaCandidata;
                    setText(fechaSeleccionada);
                    popup.setVisible(false);
                });

                btnDia.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (!btnDia.getBackground().equals(new Color(52, 152, 219))) {
                            btnDia.setBackground(new Color(200, 220, 240));
                            btnDia.setOpaque(true);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (!btnDia.getBackground().equals(new Color(52, 152, 219))
                            && !fechaCandidata.equals(hoy)) {
                            btnDia.setBackground(UIManager.getColor("Button.background"));
                            btnDia.setOpaque(false);
                        }
                    }
                });

                panelDias.add(btnDia);
            }

            panelDias.revalidate();
            panelDias.repaint();
        };

        btnAnioAnt.addActionListener(e -> {
            refAnio[0]--;
            refrescarCalendario.run();
        });

        btnMesAnt.addActionListener(e -> {
            refMes[0]--;
            if (refMes[0] < 0) {
                refMes[0] = 11;
                refAnio[0]--;
            }
            refrescarCalendario.run();
        });

        btnMesSig.addActionListener(e -> {
            refMes[0]++;
            if (refMes[0] > 11) {
                refMes[0] = 0;
                refAnio[0]++;
            }
            refrescarCalendario.run();
        });

        btnAnioSig.addActionListener(e -> {
            refAnio[0]++;
            refrescarCalendario.run();
        });

        lblMes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] mesesLargos = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
                String seleccion = (String) JOptionPane.showInputDialog(
                    panelCalendario, "Seleccione mes:", "Mes",
                    JOptionPane.PLAIN_MESSAGE, null, mesesLargos, mesesLargos[refMes[0]]);
                if (seleccion != null) {
                    for (int i = 0; i < mesesLargos.length; i++) {
                        if (mesesLargos[i].equals(seleccion)) {
                            refMes[0] = i;
                            break;
                        }
                    }
                    refrescarCalendario.run();
                }
            }
        });

        lblAnio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int[] anios = new int[201];
                for (int i = 0; i < 201; i++) {
                    anios[i] = 1950 + i;
                }
                String[] aniosStr = new String[201];
                for (int i = 0; i < 201; i++) {
                    aniosStr[i] = String.valueOf(anios[i]);
                }
                int idx = refAnio[0] - 1950;
                if (idx < 0) idx = 0;
                if (idx > 200) idx = 200;
                String seleccion = (String) JOptionPane.showInputDialog(
                    panelCalendario, "Seleccione ano:", "Ano",
                    JOptionPane.PLAIN_MESSAGE, null, aniosStr, aniosStr[idx]);
                if (seleccion != null) {
                    refAnio[0] = Integer.parseInt(seleccion);
                    refrescarCalendario.run();
                }
            }
        });

        refrescarCalendario.run();

        panelCalendario.add(panelHeader, BorderLayout.NORTH);
        panelCalendario.add(panelDias, BorderLayout.CENTER);

        popup.add(panelCalendario);
        popup.setPreferredSize(new Dimension(300, 320));
        popup.show(this, 0, getHeight());
    }
}
