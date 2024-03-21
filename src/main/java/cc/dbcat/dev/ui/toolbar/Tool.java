package cc.dbcat.dev.ui.toolbar;

import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

@Data
public class Tool {
    /**
     * 唯一值
     */
    private String key;
    /**
     * 图标
     */
    private ImageIcon icon;
    /**
     * 名称
     */
    private String name;
    /**
     * 工具类型
     */
    private ToolType toolType = ToolType.BUTTON;
    /**
     * 工具栏分割线
     */
    private Boolean separator = false;
    /**
     * 工具监听事件
     */
    private List<ActionListener> listeners;
    /**
     * 子工具项
     */
    private List<Tool> items;
}
