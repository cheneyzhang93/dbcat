package cc.dbcat.dev.ui.workcenter.detailbar;

import cc.dbcat.dev.ui.toolbar.ToolbarButtonColor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DetailbarProvider {

    public JPanel provider() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, panel.getPreferredSize().height));
        panel.add(new JLabel("详情区域"));

        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return panel;
    }
}
