package cc.dbcat.dev.ui.workcenter.navbar;

import cc.dbcat.dev.ui.toolbar.ToolbarButtonColor;

import javax.swing.*;
import java.awt.*;

public class NavbarProvider {

    public JPanel provider() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, panel.getPreferredSize().height));
        panel.add(new JLabel("数据库"));

        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return panel;
    }
}
