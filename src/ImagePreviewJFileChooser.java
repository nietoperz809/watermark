
import javax.swing.*;
import java.awt.*;
import java.beans.*;
import javax.swing.filechooser.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.concurrent.*;

class ImagePreviewJFileChooser extends JFileChooser
{
    JLabel img;

    public ImagePreviewJFileChooser()
    {
        initialize();
        showOpenDialog(null);
    }

    private void initialize()
    {
        // Create label
        img=new JLabel();

        // Let label come fatty!!
        img.setPreferredSize(new Dimension(175,175));

        // Set label as accessory
        setAccessory(img);

        // Accept only image files
        setAcceptAllFileFilterUsed(false);

        // Create filter for image files
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Image Files","jpg","jpeg","png","gif");

        // Set it as current filter
        setFileFilter(filter);

        // Add property change listener
        // When any JFileChooser property changes, this handler
// is executed
        addPropertyChangeListener(pe -> {
            // Create SwingWorker for smooth experience
            SwingWorker<Image,Void> worker=new SwingWorker<Image,Void>(){

                // The image processing method
                protected Image doInBackground()
                {
                    // If selected file changes..
                    if(pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                    {
                        // Get selected file
                        File f=getSelectedFile();

                        try
                        {
                            // Create FileInputStream for file
                            FileInputStream fin=new FileInputStream(f);

                            // Read image from fin
                            BufferedImage bim=ImageIO.read(fin);

                            // Return the scaled version of image
                            return bim.getScaledInstance(178,170,BufferedImage.SCALE_FAST);

                        }catch(Exception e){
                            // If there is a problem reading image,
                            // it might not be a valid image or unable
                            // to read
                            img.setText(" Not valid image/Unable to read");
                        }
                    }

                    return null;
                }

                protected void done()
                {
                    try
                    {
                        // Get the image
                        Image i=get(1L,TimeUnit.NANOSECONDS);

                        // If i is null, go back!
                        if(i==null) return;

                        // Set icon otherwise
                        img.setIcon(new ImageIcon(i));
                    }catch(Exception e){
                        // Print error occured
                        img.setText(" Error occured.");
                    }
                }
            };

            // Start worker thread
            worker.execute();
        });
    }
}
