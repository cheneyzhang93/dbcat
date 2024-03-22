package cc.dbcat.dev.ui.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolbarProvider {

    public JToolBar provider(String version) {
        ToolBarDSLInterpreter toolBarDSLInterpreter = new ToolBarDSLInterpreter(version);
        UIManager.put("ToolBar.borderMargins", new Insets(1, 10, 1, 10));
        UIManager.put("Button.margin", new Insets(12, 7, 12, 7));
        UIManager.put("ToggleButton.margin", new Insets(12, 7, 12, 7));
        return toolBarDSLInterpreter.getInitJToolBar();
    }
}
