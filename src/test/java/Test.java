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
        JFrame frame = new JFrame("Embedded Tabs Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // 创建主面板
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // 创建选项卡按钮面板
        JPanel tabButtonPanel = new JPanel();
        tabButtonPanel.setOpaque(false); // 设置面板透明
        tabButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // 创建选项卡按钮
        JButton tabButton1 = new JButton("Tab 1");
        JButton tabButton2 = new JButton("Tab 2");
        tabButtonPanel.add(tabButton1);
        tabButtonPanel.add(tabButton2);

        // 创建内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JLabel("Content for Tab 1"), BorderLayout.CENTER);

        // 将选项卡按钮面板和内容面板添加到主面板
        mainPanel.add(tabButtonPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // 选项卡按钮的事件处理
        tabButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll(); // 清除当前内容
                contentPanel.add(new JLabel("Content for Tab 1"), BorderLayout.CENTER); // 添加新内容
                contentPanel.revalidate(); // 重新验证布局
                contentPanel.repaint(); // 重绘面板
            }
        });

        tabButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll(); // 清除当前内容
                contentPanel.add(new JLabel("Content for Tab 2"), BorderLayout.CENTER); // 添加新内容
                contentPanel.revalidate(); // 重新验证布局
                contentPanel.repaint(); // 重绘面板
            }
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
