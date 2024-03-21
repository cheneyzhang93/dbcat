package cc.dbcat.dev.ui.theme;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class ThemeProvider {

    public void provider() {
        FlatIntelliJLaf.setup();
        System.setProperty("flatlaf.animation", "false");
        System.setProperty("flatlaf.menuBarEmbedded", "false");
    }
}
