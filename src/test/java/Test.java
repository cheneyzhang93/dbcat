import cc.dbcat.dev.Main;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Test {

//    public static void main(String[] args) {
//        // 设置 FlatLaf 外观
//        try {
//            UIManager.setLookAndFeel(new FlatDarkLaf());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 创建主窗口
//        JFrame frame = new JFrame("FlatLaf Tree Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400);
//        frame.setLayout(new BorderLayout());
//
//        // 创建树形菜单
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
//        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Node 1");
//        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Node 2");
//        root.add(node1);
//        root.add(node2);
//        DefaultTreeModel treeModel = new DefaultTreeModel(root);
//        JTree tree = new JTree(treeModel);
//
//        // 创建滚动面板并添加树形菜单
//        JScrollPane scrollPane = new JScrollPane(tree);
//        scrollPane.setPreferredSize(new Dimension(200, frame.getHeight() - 50)); // 设置滚动面板的大小
//        frame.add(scrollPane, BorderLayout.WEST); // 将滚动面板添加到窗口的左侧
//
//        // 动态添加节点示例
//        JButton addNodeButton = new JButton("Add Node");
//        addNodeButton.addActionListener(e -> {
//            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Node");
//            root.add(newNode);
//            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(root); // 更新树模型
//        });
//        frame.add(addNodeButton, BorderLayout.SOUTH); // 将按钮添加到窗口的底部
//
//        // 显示窗口
//        frame.setVisible(true);
//    }

    public static void main(String[] args) {
        // 创建 JFrame
        JFrame frame = new JFrame("Tabbed Pane with Scroll Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // 创建 JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.add(tabbedPane, BorderLayout.CENTER);

        // 创建第一个选项卡的内容
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS)); // 使用垂直BoxLayout
        for (int i = 0; i < 20; i++) { // 添加一些组件作为示例
            panel1.add(new JLabel("Label " + (i + 1)));
        }
        JScrollPane scrollPane1 = new JScrollPane(panel1); // 创建滚动面板
        tabbedPane.addTab("Tab 1", scrollPane1); // 将滚动面板添加到第一个选项卡

        // 创建第二个选项卡的内容
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        for (int i = 0; i < 20; i++) {
            panel2.add(new JLabel("Label " + (i + 1)));
        }
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        tabbedPane.addTab("Tab 2", scrollPane2); // 将滚动面板添加到第二个选项卡
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
