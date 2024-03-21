package cc.dbcat.dev.ui.menubar;

import cc.dbcat.dev.Main;
import cc.dbcat.dev.util.ClazzUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单DSL解释器,
 * 一个MenuBar为单位定义DSL,并且对文件进行版本管理,格式为 MenuDSL_v(x), (x)为具体版本号
 */
@Getter
@AllArgsConstructor
public class MenuBarDSLInterpreter {

    private String version;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private InputStream loadMenuResource() {
        return Main.class.getResourceAsStream("/dsl/menubar/MenubarDSL_" + version + ".json");
    }

    private Menu createMenu(JsonNode node) {
        Menu menu = new Menu();
        menu.setKey(node.get("key").asText());
        menu.setName(node.get("name").asText());
        menu.setMenuType(MenuType.getType(node.get("type") != null ? node.get("type").asText() : MenuType.TEXT.name()));
        menu.setDisabled(node.get("disabled") != null && node.get("disabled").asBoolean());
        menu.setSelected(node.get("selected") != null && node.get("selected").asBoolean());
        menu.setSeparator(node.get("separator") != null && node.get("separator").asBoolean());
        menu.setAccelerator(node.get("accelerator") != null ? Accelerator.getKey(node.get("accelerator").asText()) : null);
        if (node.get("listeners") != null) {
            if (menu.getListeners() == null) {
                menu.setListeners(new ArrayList<>(1));
            }
            if (node.get("listeners").isArray()) {
                for (JsonNode jsonNode : node.get("listeners")) {
                    if (jsonNode != null && !jsonNode.isEmpty()) {
                        // 类的全限定名
                        menu.getListeners().add(ClazzUtil.createActionListenerInstance(jsonNode.asText()));
                    }
                }
            } else {
                if (node.get("listeners") != null && !node.get("listeners").isEmpty()) {
                    menu.getListeners().add(ClazzUtil.createActionListenerInstance(node.get("listeners").asText()));
                }
            }
        }
        return menu;
    }

    private void parsDp(Menu parentMenu, JsonNode itemNodes) {
        if (itemNodes == null || (itemNodes.isArray() && itemNodes.isEmpty())) {
            return;
        }
        for (JsonNode node : itemNodes) {
            Menu item = createMenu(node);
            parsDp(item, node.get("items"));
            if (parentMenu.getItems() == null) {
                parentMenu.setItems(new ArrayList<>(5));
            }
            parentMenu.getItems().add(item);
        }
    }

    private MenuBar pars(InputStream inputStream) throws IOException {
        MenuBar menuBar = new MenuBar();
        JsonNode rootNode = objectMapper.readTree(inputStream);
        menuBar.setVersion(rootNode.get("version").asText());
        JsonNode itemNodes = rootNode.get("menus");
        if (itemNodes != null && itemNodes.isArray() && !itemNodes.isEmpty()) {
            for (JsonNode node : itemNodes) {
                Menu item = createMenu(node);
                parsDp(item, node.get("items"));
                menuBar.getMenus().add(item);
            }
        }
        return menuBar;
    }

    public MenuBar getInitDynamicMenuBar() {
        try {
            return pars(loadMenuResource());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dynamicMenuDp(List<Menu> dynamicItems, JMenu parentJmenu) {
        if (dynamicItems == null) {
            return;
        }
        ButtonGroup radioGroup = new ButtonGroup();
        ButtonGroup checkboxGroup = new ButtonGroup();
        for (Menu dItem : dynamicItems) {
            if (dItem.getSeparator()) {
                parentJmenu.addSeparator();
            }
            if (dItem.getMenuType().equals(MenuType.CHECKBOX)) {
                JCheckBoxMenuItem jItem = new JCheckBoxMenuItem();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                jItem.setAccelerator(dItem.getAccelerator());
                checkboxGroup.add(jItem);
                parentJmenu.add(jItem);
            } else if (dItem.getMenuType().equals(MenuType.RADIO)) {
                JRadioButtonMenuItem jItem = new JRadioButtonMenuItem();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                jItem.setAccelerator(dItem.getAccelerator());
                radioGroup.add(jItem);
                parentJmenu.add(jItem);
            } else if (dItem.getMenuType().equals(MenuType.TEXT) && dItem.getItems() == null) {
                JMenuItem jItem = new JMenuItem();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                jItem.setAccelerator(dItem.getAccelerator());
                parentJmenu.add(jItem);
            } else {
                JMenu jItem = new JMenu();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                parentJmenu.add(jItem);
                dynamicMenuDp(dItem.getItems(), jItem);
            }
        }
    }

    public JMenuBar getInitJMenuBar() {
        MenuBar menuBar = getInitDynamicMenuBar();
        JMenuBar jMenuBar = new JMenuBar();
        for (Menu dItem : menuBar.getMenus()) {
            JMenu jItem = new JMenu();
            jItem.setText(dItem.getName());
            dynamicMenuDp(dItem.getItems(), jItem);
            jMenuBar.add(jItem);
        }
        return jMenuBar;
    }


}
