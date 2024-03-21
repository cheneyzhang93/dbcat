package cc.dbcat.dev.ui.menubar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * 快捷键映射
 *  @see java.awt.event.KeyEvent
 */
@Getter
@AllArgsConstructor
public enum Accelerator {
    NUL,
    COPY,
    PASTE,
    CUT,
    HELP;

    public static KeyStroke getKey(String name) {
        return switch (name) {
            case "COPY", "copy" -> KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
            case "PASTE", "paste" -> KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
            case "CUT", "cut" -> KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);
            case "help", "cHELPt" -> KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
            default -> null;
        };

    }

}
