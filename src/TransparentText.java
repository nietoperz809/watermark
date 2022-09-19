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
            __img = __img.getScaledInstance(400,400,BufferedImage.SCALE_FAST);
            repaint();
        }
        catch (IOException e)
        {
            System.err.println("imd load fail");
        }
    }

    private void paintText (Graphics2D g, int posx, int posy) {
        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
        g.setComposite(c);
        // Draw some text.
        g.setPaint(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString(__text, posx, posy);
    }

    public static void saveImg (JFrame jp, String path) {
        try
        {
            BufferedImage image = new BufferedImage(jp.getWidth(), jp.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            jp.paint(graphics2D);
            ImageIO.write(image,"png",
                    new File(path));
        }
        catch(Exception exception)
        {
            System.err.println("saveImg failed");
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
        paintText(g2, 25, 130);
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
