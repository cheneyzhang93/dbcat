import cc.dbcat.dev.Main;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Popup Menu Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // 创建一个按钮，点击时显示弹出菜单
            JButton button = new JButton("Show Popup Menu");
            button.addActionListener(new ActionListener() {
                private JPopupMenu popupMenu;
                private JMenuItem menuItemToRemove;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 如果弹出菜单尚未创建，则创建它
                    if (popupMenu == null) {
                        popupMenu = new JPopupMenu();
                        // 添加一些菜单项
                        popupMenu.add(new JMenuItem("Option 1"));
                        popupMenu.add(new JMenuItem("Option 2"));
                        menuItemToRemove = new JMenuItem("Option to Remove");
                        popupMenu.add(menuItemToRemove);

                        // 添加一个用于移除菜单项的监听器
                        popupMenu.addPopupMenuListener(new PopupMenuListener() {
                            @Override
                            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                                // 在弹出菜单显示之前，你可以在这里添加逻辑来动态地移除菜单项
                                // 例如，基于某些条件移除菜单项
                                // popupMenu.remove(menuItemToRemove);
                            }

                            @Override
                            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

                            @Override
                            public void popupMenuCanceled(PopupMenuEvent e) {}
                        });
                    }

                    // 显示弹出菜单
                    popupMenu.show(button, 0, button.getHeight());
                }
            });

            frame.add(button, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void checkButtonVisibility(JToolBar toolbar) {
        Rectangle toolbarBounds = toolbar.getBounds();
        for (Component comp : toolbar.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                Rectangle buttonBounds = button.getBounds();
                if (!toolbarBounds.contains(buttonBounds)) {
                    System.out.println("Button " + button.getText() + " is not fully visible.");
                } else {
                    System.out.println("Button " + button.getText() + " is visible.");
                }
            }
        }
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
