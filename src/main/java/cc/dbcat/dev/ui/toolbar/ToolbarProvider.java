package cc.dbcat.dev.ui.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolbarProvider {

    public JToolBar provider(String version) {
        ToolBarDSLInterpreter toolBarDSLInterpreter = new ToolBarDSLInterpreter(version);
        UIManager.put("ToolBar.borderMargins", new Insets(5, 5, 5, 15));
        UIManager.put("Button.margin", new Insets(5, 15, 5, 15));
        return toolBarDSLInterpreter.getInitJToolBar();
    }
}
