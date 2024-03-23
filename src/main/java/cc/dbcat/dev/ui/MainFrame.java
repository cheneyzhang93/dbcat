package cc.dbcat.dev.ui;

import cc.dbcat.dev.ui.workcenter.footer.FooterProvider;
import cc.dbcat.dev.ui.theme.GlobalUI;
import cc.dbcat.dev.ui.workcenter.WorkCenterMainProvider;
import cc.dbcat.dev.ui.workcenter.detailbar.DetailbarProvider;
import cc.dbcat.dev.ui.image.ImageProvider;
import cc.dbcat.dev.ui.menubar.MenuBarProvider;
import cc.dbcat.dev.ui.workcenter.navbar.NavbarProvider;
import cc.dbcat.dev.ui.theme.ThemeProvider;
import cc.dbcat.dev.ui.toolbar.ToolbarProvider;

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
        // 设置全局样式
        GlobalUI.setting(frame);
        // logo
        frame.setIconImage(ImageProvider.logo());
        // 窗口设置
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());
        // 顶部菜单栏
        MenuBarProvider menuBarProvider = new MenuBarProvider();
        frame.setJMenuBar(menuBarProvider.provider(version));
        // 顶部工具栏
        ToolbarProvider toolbarProvider = new ToolbarProvider();
        frame.add(toolbarProvider.provider(version), BorderLayout.NORTH);
        // 尾部工具栏
        FooterProvider footerProvider = new FooterProvider();
        frame.add(footerProvider.provider(), BorderLayout.SOUTH);
        // 数据库导航栏
        NavbarProvider navbarProvider = new NavbarProvider();
        frame.add(navbarProvider.provider(), BorderLayout.WEST);
        // 主工作区
        WorkCenterMainProvider workCenterMainProvider = new WorkCenterMainProvider();
        frame.add(workCenterMainProvider.provider(), BorderLayout.CENTER);
        // 详情面板
        DetailbarProvider detailbarProvider = new DetailbarProvider();
        frame.add(detailbarProvider.provider(), BorderLayout.EAST);

        return frame;
    }

}
