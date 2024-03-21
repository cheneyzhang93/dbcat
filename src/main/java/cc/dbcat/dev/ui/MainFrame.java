package cc.dbcat.dev.ui;

import cc.dbcat.dev.Main;
import cc.dbcat.dev.ui.menu.MenuDSLInterpreter;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MainFrame {

    private static final String version = "v0.1";

    public JFrame init() {
        // 主题设置
        FlatIntelliJLaf.setup();
        System.setProperty("flatlaf.animation", "false");
        System.setProperty("flatlaf.menuBarEmbedded", "false");

        // 创建 JFrame 实例
        JFrame frame = new JFrame("DBcat " + version);
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/image/favicon.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        // 设置 JFrame 的布局管理器为 BorderLayout
        frame.setLayout(new BorderLayout());
        // 菜单
        MenuDSLInterpreter menuDSLInterpreter = new MenuDSLInterpreter(version);
        UIManager.put("MenuBar.itemMargins", new Insets(3, 15, 3, 15));
        UIManager.put("MenuItem.textNoAcceleratorGap", 60);
        UIManager.put("MenuItem.textAcceleratorGap", 60);
        frame.setJMenuBar(menuDSLInterpreter.getInitJMenuBar());


        return frame;
    }

}
