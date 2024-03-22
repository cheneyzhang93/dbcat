package cc.dbcat.dev.ui;

import cc.dbcat.dev.ui.image.ImageProvider;
import cc.dbcat.dev.ui.menubar.MenuBarProvider;
import cc.dbcat.dev.ui.navbar.NavbarProvider;
import cc.dbcat.dev.ui.theme.ThemeProvider;
import cc.dbcat.dev.ui.toolbar.ToolbarProvider;
import cc.dbcat.dev.ui.workcenter.WorkCenterProvider;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private static final String version = "v0.1";

    public JFrame init() {
        // 主题设置
        ThemeProvider themeProvider = new ThemeProvider();
        themeProvider.provider();
        // 创建 JFrame 实例
        JFrame frame = new JFrame("DBcat " + version);
        // logo
        frame.setIconImage(ImageProvider.logo());
        // 窗口设置
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
        frame.setLayout(new BorderLayout());
        // 菜单栏
        MenuBarProvider menuBarProvider = new MenuBarProvider();
        frame.setJMenuBar(menuBarProvider.provider(version));
        // 工具栏
        ToolbarProvider toolbarProvider = new ToolbarProvider();
        JToolBar toolBar = toolbarProvider.provider(version);
        frame.add(toolBar, BorderLayout.NORTH);
        // 数据库导航栏
        NavbarProvider navbarProvider = new NavbarProvider();
        frame.add(navbarProvider.provider(), BorderLayout.WEST);
        // 工作区
//        WorkCenterProvider workCenterProvider = new WorkCenterProvider();
//        frame.add(workCenterProvider.provider(), BorderLayout.CENTER);
        return frame;
    }

}
