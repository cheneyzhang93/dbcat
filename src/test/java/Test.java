import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Test {

    public static void main(String[] args){
        // 设置 flatlaf 外观，这里使用 FlatDarkLaf 作为示例，你也可以选择其他主题，如 FlatIntelliJLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // 创建 JFrame
        JFrame frame = new JFrame("FlatLaf ToolBar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 创建 JToolBar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // 设置工具栏不可浮动

        // 添加按钮到工具栏
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        toolBar.add(button1);
        toolBar.add(button2);

        // 将工具栏添加到 JFrame 的内容面板中
        frame.getContentPane().add(toolBar, BorderLayout.NORTH);

        // 显示 JFrame
        frame.setVisible(true);
    }

}
