import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TransparentText extends JPanel {

    private Image __img;
    private final String __text = "nacktes mädchen!";

    TransparentText(String imgPath) {
        loadImage (new File (imgPath));
    }

    TransparentText(File f) {
        loadImage (f);
    }

    private void loadImage (File imgF) {
        try
        {
            __img = ImageIO.read (imgF);
            int nx = __img.getWidth(null);
            int ny = __img.getHeight(null);
            int div = 2;
            while (nx > 700 || ny > 700) {
                nx = __img.getWidth(null)/div;
                ny = __img.getHeight(null)/div;
                div++;
            }
            __img = __img.getScaledInstance(nx,ny,BufferedImage.SCALE_FAST);
            setSize(nx, ny);
            setPreferredSize(new Dimension(nx, ny));
            repaint();
        }
        catch (IOException e)
        {
            System.err.println("imd load fail");
        }
    }


    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // the rendering quality.
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.drawImage(__img, 0, 0, null);
        Tools.paintText(g2, 25, 130);
    }

    @Override
    public int getWidth() {
        return __img.getWidth(null);
    }

    @Override
    public int getHeight() {
        return __img.getHeight(null);
    }
}
