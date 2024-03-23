package cc.dbcat.dev.ui.menubar;

import javax.swing.*;

public class MenuBarProvider {

    public JMenuBar provider(String version){
        MenuBarDSLInterpreter menuBarDSLInterpreter = new MenuBarDSLInterpreter(version);
        return menuBarDSLInterpreter.getInitJMenuBar();
    }
}
