package cc.dbcat.dev.ui.theme;

import cc.dbcat.dev.ui.toolbar.ToolbarButtonColor;

import javax.swing.*;
import java.awt.*;

public class GlobalUI {
    public static void setting(JFrame frame) {
        // 全局UI
        // 菜单栏
        UIManager.put("MenuBar.itemMargins", new Insets(3, 15, 3, 20));
        UIManager.put("MenuItem.textNoAcceleratorGap", 60);
        UIManager.put("MenuItem.textAcceleratorGap", 60);
        // 工具栏
        UIManager.put("ToolBar.borderMargins", new Insets(1, 10, 1, 10));
        UIManager.put("Button.margin", new Insets(12, 5, 12, 5));
        UIManager.put("ToggleButton.margin", new Insets(12, 5, 12, 5));
        UIManager.put("Button.toolbar.hoverBackground", ToolbarButtonColor.HOVER_BACKGROUND);
        UIManager.put("ToggleButton.toolbar.hoverBackground", ToolbarButtonColor.HOVER_BACKGROUND);
        UIManager.put("Button.toolbar.pressedBackground", ToolbarButtonColor.PRESSED_BACKGROUND);
        UIManager.put("ToggleButton.toolbar.pressedBackground", ToolbarButtonColor.PRESSED_BACKGROUND);
        // 选项卡
        UIManager.put("TabbedPane.showTabSeparators", false);
        UIManager.put("TabbedPane.hasFullBorder", false);
        UIManager.put("TabbedPane.contentAreaColor", frame.getBackground());
        UIManager.put("TabbedPane.minimumTabWidth", 60);
        UIManager.put("TabbedPane.contentSeparatorHeight", 0);
    }
}
