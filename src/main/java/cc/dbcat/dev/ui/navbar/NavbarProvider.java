package cc.dbcat.dev.ui.navbar;

import cc.dbcat.dev.ui.toolbar.ToolbarButtonColor;

import javax.swing.*;
import java.awt.*;

public class NavbarProvider {

    public JTabbedPane provider() {
        JTabbedPane tabbedPane = new JTabbedPane();
        Dimension prefSize = new Dimension(250, tabbedPane.getPreferredSize().height);
        tabbedPane.setPreferredSize(prefSize);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane1 = new JScrollPane(panel);
        tabbedPane.addTab("数据库", scrollPane1);

//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, tabbedPane);
//        splitPane.setDividerLocation(200); // 初始设置 JTabbedPane 的宽度
//        splitPane.setOneTouchExpandable(true); // 允许通过拖动分隔符来调整大小

        return tabbedPane;
    }
}
