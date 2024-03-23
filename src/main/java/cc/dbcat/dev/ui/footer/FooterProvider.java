package cc.dbcat.dev.ui.footer;

import javax.swing.*;
import java.awt.*;

public class FooterProvider {

    public JToolBar provider() {
        JToolBar jToolBar = new JToolBar();
        jToolBar.add(new Label("footer"));
        return jToolBar;
    }

}
