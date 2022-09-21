import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class Tools {

    public static void paintText(Graphics2D g, int posx, int posy) {
        Font fnt = new Font("Times New Roman", Font.PLAIN, 30);
        paintText  (g, posx, posy, fnt, "Hello", Color.white, 0.4f);
    }

    public static void paintText(Graphics2D g, int posx, int posy, Font fnt, String txt, Color col, float alpha) {
        Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(c);
        // Draw some text.
        g.setPaint(col);
        g.setFont(fnt);
        g.drawString(txt, posx, posy);
    }

    public static void saveImg (JComponent jp, String path) {
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

    public static int findAndSelect(java.awt.List li, String s) {
        for (int i = 0; i < li.getItemCount(); i++) {
            String item = li.getItem(i);
            if (item.equals(s)) {
                li.select(i);
                return i;
            }
        }
        return -1;
    }

}
