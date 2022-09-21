
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField txtWatermarkToShow;
    private JTextField textField_1;
    private BufferedImage theImage;

    private final JScrollPane scrollPane = new JScrollPane();

    private final JPanel picturePanel = new JPanel() {

        private void paintText (Graphics2D g, int posx, int posy) {
            Composite c = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
            g.setComposite(c);
            // Draw some text.
            g.setPaint(Color.white);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            g.drawString("hello", posx, posy);
        }

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            // the rendering quality.
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            if (theImage != null)
                g2.drawImage(theImage, 0, 0, null);
            paintText(g2, 25, 130);
        }
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrame() {
        setTitle("Watermarker 1.0");
        setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 767, 535);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.GREEN);
        panel_1.setBounds(5, 5, 746, 67);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        textField = new JTextField();
        textField.setToolTipText("X/Y separated by /");
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setText("0/0");
        textField.setBounds(193, 0, 46, 20);
        panel_1.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Position");
        lblNewLabel_1.setLabelFor(textField);
        lblNewLabel_1.setBounds(147, -1, 46, 22);
        panel_1.add(lblNewLabel_1);

        txtWatermarkToShow = new JTextField();
        txtWatermarkToShow.setToolTipText("Watermark");
        txtWatermarkToShow.setText("Watermark to show");
        txtWatermarkToShow.setBounds(243, 32, 315, 20);
        panel_1.add(txtWatermarkToShow);
        txtWatermarkToShow.setColumns(10);

        JButton btnNewButton = new JButton("Print");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setBounds(147, 31, 89, 23);
        panel_1.add(btnNewButton);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImagePreviewJFileChooser ifc = new ImagePreviewJFileChooser();
                try {
                    theImage = ImageIO.read (ifc.getSelectedFile());
                    Dimension dim = new Dimension (theImage.getWidth(), theImage.getHeight());
                    JViewport vp = scrollPane.getViewport();
                    vp.setViewSize (dim);
                    picturePanel.setPreferredSize(dim);
                    repaint();
                } catch (IOException ex) {
                    System.out.println("IMG load fail");
                }
            }
        });
        btnLoad.setBounds(589, 11, 65, 41);
        panel_1.add(btnLoad);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSave.setBounds(661, 11, 65, 41);
        panel_1.add(btnSave);

        JButton btnFont = new JButton("Select font");
        btnFont.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnFont.setBounds(10, 11, 113, 41);
        panel_1.add(btnFont);

        JLabel lblTransparency = new JLabel("Transparency");
        lblTransparency.setBounds(249, 3, 89, 14);
        panel_1.add(lblTransparency);

        textField_1 = new JTextField();
        lblTransparency.setLabelFor(textField_1);
        textField_1.setToolTipText("float value 0...1");
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setText("0.4");
        textField_1.setBounds(334, 0, 46, 20);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 76, 746, 419);
        contentPane.add(scrollPane);

        scrollPane.setViewportView(picturePanel);
        picturePanel.setBackground(Color.ORANGE);
    }
}
