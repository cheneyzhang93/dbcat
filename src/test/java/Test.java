import cc.dbcat.dev.Main;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame("Custom Toolbar Panel with Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel toolbarContainer = new JPanel();
        toolbarContainer.setLayout(new BoxLayout(toolbarContainer, BoxLayout.X_AXIS));
        toolbarContainer.setOpaque(false);
        toolbarContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Assuming we want to create 3 custom toolbar panels
        for (int i = 0; i < 3; i++) {
            // Create custom toolbar panel
            final JPanel customToolbar = createCustomToolbarPanel("Toolbar " + (i + 1),"/image/favicon.png");

            // Add a mouse listener to the panel to show the popup menu on click
            int finalI = i;
            int finalI1 = i;
            customToolbar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showPopup(e);
                    }
                }

                private void showPopup(MouseEvent e) {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem item1 = new JMenuItem("Option 1");
                    JMenuItem item2 = new JMenuItem("Option 2");
                    popupMenu.add(item1);
                    popupMenu.add(item2);

                    // Add action listener to menu items
                    item1.addActionListener(ae -> System.out.println("Option 1 selected on toolbar " + (finalI + 1)));

                    item2.addActionListener(ae -> System.out.println("Option 2 selected on toolbar " + (finalI1 + 1)));

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });

            toolbarContainer.add(customToolbar);
            toolbarContainer.add(Box.createHorizontalStrut(10)); // Add spacing
        }

        frame.add(toolbarContainer, BorderLayout.NORTH);
        frame.setVisible(true);
    }


    private static JPanel createCustomToolbarPanel(String text, String imagePath) throws IOException {
        JPanel customToolbar = new JPanel();
        customToolbar.setLayout(new BorderLayout());
        customToolbar.setOpaque(false);
        customToolbar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        // 加载图片
        BufferedImage image = ImageIO.read(Main.class.getResource(imagePath));

        JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(image.getWidth(), -1, Image.SCALE_SMOOTH)));
        imageLabel.setVerticalAlignment(JLabel.CENTER); // 垂直居中图片
        imageLabel.setHorizontalAlignment(JLabel.LEFT); // 图片左对齐

        // 添加文本标签
        JLabel textLabel = new JLabel(text);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);

        // 创建一个面板来组合图片和文本，并设置布局
        JPanel contentPanel = new JPanel(new BorderLayout(5, 0)); // 5像素的水平间距
        contentPanel.add(imageLabel, BorderLayout.WEST); // 图片放在西边（左边）
        contentPanel.add(textLabel, BorderLayout.CENTER); // 文本放在中间
        contentPanel.setOpaque(false); // 透明背景

        customToolbar.add(contentPanel, BorderLayout.CENTER);

        return customToolbar;
    }
}
