package cc.dbcat.dev.ui.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolbarProvider {

    public JToolBar provider(String version) {
        ToolBarDSLInterpreter toolBarDSLInterpreter = new ToolBarDSLInterpreter(version);

        return toolBarDSLInterpreter.getInitJToolBar();
    }
}
