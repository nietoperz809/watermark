
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private float __alpha = 0.4f;
    private final Point __pos = new Point (10,120);
    private Color __col = new Color(0,0,0);
    private Font __font = new Font("Times New Roman", Font.PLAIN, 30);
    JTextField __text = new JTextField();
    private final JPanel contentPane;
    private final JTextField tf_Position;
    private final JTextField transparency;
    private BufferedImage theImage;

    private final JScrollPane scrollPane = new JScrollPane();

    private final JPanel picturePanel = new JPanel() {
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            // the rendering quality.
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            if (theImage != null)
                g2.drawImage(theImage, 0, 0, null);
            Tools.paintText(g2, __pos.x, __pos.y,
                    __font, __text.getText(), __col, __alpha);
        }
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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

        tf_Position = new JTextField();
        tf_Position.setToolTipText("X/Y separated by /");
        tf_Position.setHorizontalAlignment(SwingConstants.CENTER);
        tf_Position.setText("0/0");
        tf_Position.setBounds(193, 0, 46, 20);
        tf_Position.addActionListener(e -> {
            String t = tf_Position.getText();
            String[] spl = t.split("/");
            __pos.x = Integer.parseInt(spl[0]);
            __pos.y = Integer.parseInt(spl[1]);
            repaint();

        });
        panel_1.add(tf_Position);
        tf_Position.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Position");
        lblNewLabel_1.setLabelFor(tf_Position);
        lblNewLabel_1.setBounds(147, -1, 46, 22);
        panel_1.add(lblNewLabel_1);

        __text.setToolTipText("Watermark");
        __text.setText("Watermark to show");
        __text.setBounds(243, 32, 315, 20);
        __text.addActionListener(e -> repaint());

        panel_1.add(__text);
        __text.setColumns(10);

        JButton btnNewButton = new JButton("Color");
        btnNewButton.addActionListener(e -> {
            __col = JColorChooser.showDialog(null,
                    "Text Color", null);
            repaint();
        });
        btnNewButton.setBounds(147, 31, 89, 23);
        panel_1.add(btnNewButton);

        JButton btnLoad = new JButton("Load");
        btnLoad.addActionListener(e -> {
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
        });
        btnLoad.setBounds(589, 11, 65, 41);
        panel_1.add(btnLoad);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(picturePanel);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                Tools.saveImg(picturePanel,fileToSave.getAbsolutePath());
            }
        });
        btnSave.setBounds(661, 11, 65, 41);
        panel_1.add(btnSave);

        JButton btnFont = new JButton("Select font");
        btnFont.addActionListener(e -> {
            final FontChooser2 fc = new FontChooser2(null);
            //fc.adjustDisplay(ta.getFont());
            fc.setVisible(true);
            __font = fc.getSelectedFont();
            repaint();
        });
        btnFont.setBounds(10, 11, 113, 41);
        panel_1.add(btnFont);

        JLabel lblTransparency = new JLabel("Transparency");
        lblTransparency.setBounds(249, 3, 89, 14);
        panel_1.add(lblTransparency);

        transparency = new JTextField();
        lblTransparency.setLabelFor(transparency);
        transparency.setToolTipText("float value 0...1");
        transparency.setHorizontalAlignment(SwingConstants.CENTER);
        transparency.setText("0.4");
        transparency.setBounds(334, 0, 46, 20);
        transparency.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                __alpha = Float.parseFloat(transparency.getText());
                repaint();
            }
        });
        panel_1.add(transparency);
        transparency.setColumns(10);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(5, 76, 746, 419);
        contentPane.add(scrollPane);

        scrollPane.setViewportView(picturePanel);
        picturePanel.setBackground(Color.ORANGE);
    }
}
