package cc.dbcat.dev.ui.menubar;

import cc.dbcat.dev.Main;
import cc.dbcat.dev.ui.Accelerator;
import cc.dbcat.dev.ui.Type;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 菜单DSL解释器,
 * 一个MenuBar为单位定义DSL,并且对文件进行版本管理,格式为 MenuDSL_v(x), (x)为具体版本号
 */
@Getter
@AllArgsConstructor
public class MenuDSLInterpreter {

    private String version;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private InputStream loadMenuResource() {
        return Main.class.getResourceAsStream("/dsl/menu/MenuDSL_" + version + ".json");
    }

    private ActionListener createInstance(String clazzName) {
        try {
            Class<?> listenerClass = Class.forName(clazzName);
            try {
                return (ActionListener) listenerClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Menu createMenu(JsonNode node) {
        Menu menu = new Menu();
        menu.setKey(node.get("key").asText());
        menu.setName(node.get("name").asText());
        menu.setType(Type.getType(node.get("type") != null ? node.get("type").asText() : Type.TEXT.name()));
        menu.setDisabled(node.get("disabled") != null && node.get("disabled").asBoolean());
        menu.setSelected(node.get("selected") != null && node.get("selected").asBoolean());
        menu.setSeparator(node.get("separator") != null && node.get("separator").asBoolean());
        menu.setAccelerator(node.get("accelerator") != null ? Accelerator.getKey(node.get("accelerator").asText()) : null);
        if (node.get("accelerator") != null) {
            if (node.get("accelerator").isArray()) {
                for (JsonNode jsonNode : node.get("accelerator")) {
                    if (jsonNode != null && !jsonNode.isEmpty()) {
                        // 类的全限定名
                        menu.getListeners().add(createInstance(jsonNode.asText()));
                    }
                }
            } else {
                if (node.get("accelerator") != null && !node.get("accelerator").isEmpty()) {
                    menu.getListeners().add(createInstance(node.get("accelerator").asText()));
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
        if (dynamicItems.isEmpty()) {
            return;
        }
        ButtonGroup radioGroup = new ButtonGroup();
        ButtonGroup checkboxGroup = new ButtonGroup();
        for (Menu dItem : dynamicItems) {
            if (dItem.getSeparator()) {
                parentJmenu.addSeparator();
            }
            if (dItem.getType().equals(Type.CHECKBOX)) {
                JCheckBoxMenuItem jItem = new JCheckBoxMenuItem();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                jItem.setAccelerator(dItem.getAccelerator());
                checkboxGroup.add(jItem);
                parentJmenu.add(jItem);
            } else if (dItem.getType().equals(Type.RADIO)) {
                JRadioButtonMenuItem jItem = new JRadioButtonMenuItem();
                jItem.setText(dItem.getName());
                jItem.setSelected(dItem.getSelected());
                jItem.setAccelerator(dItem.getAccelerator());
                radioGroup.add(jItem);
                parentJmenu.add(jItem);
            } else if (dItem.getType().equals(Type.TEXT) && dItem.getItems().isEmpty()) {
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
