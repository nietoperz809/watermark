
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class MainApp {

    public static void start (File imgFile) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        TransparentText tp = new TransparentText(imgFile);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add (tp, BorderLayout.CENTER);
        f.setSize (tp.getWidth()+100, tp.getHeight()+100);
        //f.pack();
        f.setVisible(true);
        TransparentText.saveImg(f,"C:\\Users\\Administrator\\Desktop\\jmemPractice.png");

    }

    public static void main(String[] args) {
        ImagePreviewJFileChooser ifc = new ImagePreviewJFileChooser();
        start(ifc.getSelectedFile());
    }

}
