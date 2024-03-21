package cc.dbcat.dev.ui;

import cc.dbcat.dev.Main;
import cc.dbcat.dev.ui.logo.LogoProvider;
import cc.dbcat.dev.ui.menu.MenuBarProvider;
import cc.dbcat.dev.ui.menu.MenuDSLInterpreter;
import cc.dbcat.dev.ui.theme.ThemeProvider;
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
        ThemeProvider themeProvider = new ThemeProvider();
        themeProvider.provider();
        // 创建 JFrame 实例
        JFrame frame = new JFrame("DBcat " + version);
        // logo
        LogoProvider logoProvider = new LogoProvider();
        frame.setIconImage(logoProvider.provider());
        // 窗口设置
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());
        // 菜单栏
        MenuBarProvider menuBarProvider = new MenuBarProvider();
        frame.setJMenuBar(menuBarProvider.provider(version));


        return frame;
    }

}
