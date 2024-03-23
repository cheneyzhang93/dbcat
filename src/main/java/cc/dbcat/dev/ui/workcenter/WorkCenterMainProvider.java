package cc.dbcat.dev.ui.workcenter;

import javax.swing.*;
import java.awt.*;

public class WorkCenterMainProvider {

    public JTabbedPane provider() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel = new JPanel();
        JScrollPane databasePane = new JScrollPane(panel);
        databasePane.setBorder(null);
        tabbedPane.addTab("对象", databasePane);
        tabbedPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return tabbedPane;
    }
}
