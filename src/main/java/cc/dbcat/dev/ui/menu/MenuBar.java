package cc.dbcat.dev.ui.menu;

import lombok.Data;

import java.util.List;

/**
 * 菜单容器
 */
@Data
public class MenuBar {

    /**
     * 版本
     */
    private String version;
    /**
     * 按钮组
     */
    private List<Menu> menus;
}
