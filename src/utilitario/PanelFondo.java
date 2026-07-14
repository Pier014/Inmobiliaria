package utilitario;

import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Clase hija de JPanel. Personaliza el fondo del panel con una imagen 
public class PanelFondo extends JPanel {
    private BufferedImage img;

    //Constructor sin layout: carga la imagen de fondo 
    public PanelFondo() {
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        } catch (Exception e) {
        }
    }

    //Constructor con layout: carga la imagen de fondo y asigna el layout recibido 
    public PanelFondo(LayoutManager layout) {
        super(layout);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/imagenes/fondo.jpg"));
        } catch (Exception e) {
        }
    }

    //Dibuja la imagen de fondo redimensionada al tamano del panel 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
