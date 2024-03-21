package cc.dbcat.dev.ui.menubar;

import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionListener;
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
     * 默认情况下是否不能点击
     */
    private Boolean disabled = false;
    /**
     * 默认情况下是否选中
     */
    private Boolean selected = false;
    /**
     * 默认类型为文本
     */
    private MenuType menuType = MenuType.TEXT;
    /**
     * 菜单分割线
     */
    private Boolean separator = false;
    /**
     * 快捷键
     */
    private KeyStroke accelerator;
    /**
     * 菜单监听事件
     */
    private List<ActionListener> listeners;
    /**
     * 子菜单
     */
    private List<Menu> items;
}
