
import javax.swing.*;
import java.io.File;

public class MainApp {

    public static void start (File imgFile) {
        JFrame f = new JFrame();
        TransparentText tp = new TransparentText(imgFile);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add (tp);
        f.setSize (tp.getWidth(), tp.getHeight());
        f.setVisible(true);
        TransparentText.saveImg(f,"C:\\Users\\Administrator\\Desktop\\jmemPractice.png");

    }

    public static void main(String[] args) {
        ImagePreviewJFileChooser ifc = new ImagePreviewJFileChooser();
        start(ifc.getSelectedFile());
    }

}
