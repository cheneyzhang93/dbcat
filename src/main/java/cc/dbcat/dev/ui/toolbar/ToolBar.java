package cc.dbcat.dev.ui.toolbar;

import lombok.Data;

import java.util.List;

@Data
public class ToolBar {
    /**
     * 版本
     */
    private String version;
    /**
     * 工具栏
     */
    private List<Tool> tools;
}
