package cc.dbcat.dev.ui.menubar;

import javax.swing.*;
import java.awt.*;

public class MenuBarProvider {

    public JMenuBar provider(String version){
        MenuDSLInterpreter menuDSLInterpreter = new MenuDSLInterpreter(version);
        UIManager.put("MenuBar.itemMargins", new Insets(3, 15, 3, 15));
        UIManager.put("MenuItem.textNoAcceleratorGap", 60);
        UIManager.put("MenuItem.textAcceleratorGap", 60);
        return menuDSLInterpreter.getInitJMenuBar();
    }
}
