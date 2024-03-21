package cc.dbcat.dev.ui.toolbar;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToolType {

    BUTTON,
    MENU,
    MENU_ITEM;

    public static ToolType getType(String name) {
        for (ToolType toolType : ToolType.values()) {
            if (toolType.name().equalsIgnoreCase(name)) {
                return toolType;
            }
        }
        return null;
    }

}
