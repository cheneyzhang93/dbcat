package cc.dbcat.dev.ui.menubar;

import javax.swing.*;
import java.awt.*;

public class MenuBarProvider {

    public JMenuBar provider(String version){
        MenuBarDSLInterpreter menuBarDSLInterpreter = new MenuBarDSLInterpreter(version);
        UIManager.put("MenuBar.itemMargins", new Insets(5, 15, 5, 15));
        UIManager.put("MenuItem.textNoAcceleratorGap", 60);
        UIManager.put("MenuItem.textAcceleratorGap", 60);
        return menuBarDSLInterpreter.getInitJMenuBar();
    }
}