package inmobiliaria;

import gui.frmLogin;
import javax.swing.*;

public class Inmobiliaria {

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
            }
        }

        SwingUtilities.invokeLater(() -> {
            frmLogin login = new frmLogin();
            login.setVisible(true);
        });
    }
}
