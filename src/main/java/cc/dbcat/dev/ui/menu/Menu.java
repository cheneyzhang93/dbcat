package cc.dbcat.dev.ui.menu;

import lombok.Data;

import java.util.List;

/**
 * 菜单
 */
@Data
public class Menu {
    /**
     * 唯一值,所有菜单唯一
     */
    private String key;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 定义菜单组,每个菜单组同一个groupKey
     */
    private String groupKey;
    /**
     * 子菜单
     */
    private List<Menu> menuItems;
}
