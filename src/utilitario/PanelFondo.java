package utilitario;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {
    private BufferedImage img;

    public PanelFondo() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        } catch (Exception e) {
        }
    }

    public PanelFondo(LayoutManager layout) {
        super(layout);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        } catch (Exception e) {
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
