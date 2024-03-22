package cc.dbcat.dev.ui.toolbar;

import javax.swing.*;
import java.awt.*;

public class ToolbarProvider {

    public JToolBar provider(String version) {
        ToolBarDSLInterpreter toolBarDSLInterpreter = new ToolBarDSLInterpreter(version);
        UIManager.put("ToolBar.borderMargins", new Insets(1, 10, 1, 10));
        UIManager.put("Button.margin", new Insets(12, 5, 12, 5));
        UIManager.put("ToggleButton.margin", new Insets(12, 5, 12, 5));
        // 鼠标悬停
        UIManager.put("Button.toolbar.hoverBackground",  ToolbarButtonColor.HOVER_BACKGROUND);
        UIManager.put("ToggleButton.toolbar.hoverBackground",  ToolbarButtonColor.HOVER_BACKGROUND);
        // 摁下鼠标
        UIManager.put("Button.toolbar.pressedBackground", ToolbarButtonColor.PRESSED_BACKGROUND);
        UIManager.put("ToggleButton.toolbar.pressedBackground",  ToolbarButtonColor.PRESSED_BACKGROUND);

        return toolBarDSLInterpreter.getInitJToolBar();
    }
}
