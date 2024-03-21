package cc.dbcat.dev.ui.menubar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuType {
    TEXT,
    RADIO,
    CHECKBOX;

    public static MenuType getType(String name) {
        for (MenuType menuType : MenuType.values()) {
            if (menuType.name().equalsIgnoreCase(name)) {
                return menuType;
            }
        }
        return null;
    }
}
