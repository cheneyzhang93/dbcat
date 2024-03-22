package cc.dbcat.dev.ui.toolbar;

import cc.dbcat.dev.Main;
import cc.dbcat.dev.ui.image.ImageProvider;
import cc.dbcat.dev.util.ClazzUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具栏DSL解释器,
 * 一个ToolBar为单位定义DSL,并且对文件进行版本管理,格式为 ToolDSL_v(x), (x)为具体版本号
 */
@Getter
@AllArgsConstructor
public class ToolBarDSLInterpreter {

    private String version;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private InputStream loadToolResource() {
        return Main.class.getResourceAsStream("/dsl/toolbar/ToolbarDSL_" + version + ".json");
    }

    private Tool createTool(JsonNode node) {
        Tool tool = new Tool();
        tool.setKey(node.get("key").asText());
        tool.setName(node.get("name") != null ? node.get("name").asText() : "");
        if (node.get("icon") != null && !StringUtils.isEmpty(node.get("icon").asText())) {
            tool.setIcon(ImageProvider.icon(node.get("icon").asText()));
        }
        tool.setToolType(ToolType.getType(node.get("type") != null ? node.get("type").asText() : ToolType.BUTTON.name()));
        tool.setSeparator(node.get("separator") != null && node.get("separator").asBoolean());
        if (node.get("listeners") != null) {
            if (tool.getListeners() == null) {
                tool.setListeners(new ArrayList<>(1));
            }
            if (node.get("listeners").isArray()) {
                for (JsonNode jsonNode : node.get("listeners")) {
                    if (jsonNode != null && !jsonNode.isEmpty()) {
                        // 类的全限定名
                        tool.getListeners().add(ClazzUtil.createActionListenerInstance(jsonNode.asText()));
                    }
                }
            } else {
                if (node.get("listeners") != null && !node.get("listeners").isEmpty()) {
                    tool.getListeners().add(ClazzUtil.createActionListenerInstance(node.get("listeners").asText()));
                }
            }
        }
        return tool;
    }

    private void parsDp(Tool parentTool, JsonNode itemNodes) {
        if (itemNodes == null || (itemNodes.isArray() && itemNodes.isEmpty())) {
            return;
        }
        for (JsonNode node : itemNodes) {
            Tool item = createTool(node);
            parsDp(item, node.get("items"));
            if (parentTool.getItems() == null) {
                parentTool.setItems(new ArrayList<>(5));
            }
            parentTool.getItems().add(item);
        }
    }

    private ToolBar pars(InputStream inputStream) throws IOException {
        ToolBar toolBar = new ToolBar();
        JsonNode rootNode = objectMapper.readTree(inputStream);
        toolBar.setVersion(rootNode.get("version").asText());
        JsonNode itemNodes = rootNode.get("tools");
        if (itemNodes != null && itemNodes.isArray() && !itemNodes.isEmpty()) {
            for (JsonNode node : itemNodes) {
                Tool item = createTool(node);
                parsDp(item, node.get("items"));
                if (toolBar.getTools() == null) {
                    toolBar.setTools(new ArrayList<>(itemNodes.size()));
                }
                toolBar.getTools().add(item);
            }
        }
        return toolBar;
    }

    public ToolBar getInitDynamicMenuBar() {
        try {
            return pars(loadToolResource());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addDropDownButtonMouseListener(JToggleButton dropDownButton, JPopupMenu popupMenu) {
        dropDownButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dropDownButton.setBackground(ToolbarButtonColor.PRESSED_BACKGROUND);
                popupMenu.show(dropDownButton, 0, dropDownButton.getHeight());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dropDownButton.setSelected(false);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dropDownButton.setBackground(ToolbarButtonColor.PRESSED_BACKGROUND);
            }
        });
    }

    private void addPopupMenuListener(JToggleButton dropDownButton, JPopupMenu popupMenu) {
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                dropDownButton.setBackground(ToolbarButtonColor.DEFAULT_BACKGROUND);
            }
        });
    }

    public JToolBar getInitJToolBar() {
        ToolBar toolBar = getInitDynamicMenuBar();
        JToolBar jToolBar = new JToolBar();
        for (Tool dItem : toolBar.getTools()) {
            if (dItem.getSeparator()) {
                jToolBar.addSeparator();
            }
            if (dItem.getToolType().equals(ToolType.MENU)) {
                JPopupMenu popupMenu = new JPopupMenu();
                for (Tool tool : dItem.getItems()) {
                    JMenuItem jItem = new JMenuItem();
                    jItem.setText(tool.getName());
                    if (tool.getListeners() != null && !tool.getListeners().isEmpty()) {
                        for (ActionListener listener : tool.getListeners()) {
                            jItem.addActionListener(listener);
                        }
                    }
                    popupMenu.add(jItem);
                }
                JToggleButton dropDownButton = new JToggleButton();
                dropDownButton.setText(dItem.getName());
                dropDownButton.setIcon(dItem.getIcon());
                // 添加事件
                addDropDownButtonMouseListener(dropDownButton, popupMenu);
                addPopupMenuListener(dropDownButton, popupMenu);
                jToolBar.add(dropDownButton);
            } else if (dItem.getToolType().equals(ToolType.BUTTON)) {
                JButton jItem = new JButton();
                jItem.setText(dItem.getName());
                jItem.setIcon(dItem.getIcon());
                jToolBar.add(jItem);
            }
        }
        return jToolBar;
    }


}
