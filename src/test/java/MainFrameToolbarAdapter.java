import cc.dbcat.dev.ui.toolbar.ToolbarProvider;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@Setter
@Getter
public class MainFrameToolbarAdapter extends ComponentAdapter {

    int toolbarComponentsCount;

    int toolbarComponentsContainsCount;

    private JToggleButton dropDownButton = new JToggleButton(">");

    private JPopupMenu popupMenu = new JPopupMenu();

    private JToolBar toolBar;

    private JFrame frame;

    public MainFrameToolbarAdapter(String version, JFrame frame, JToolBar toolBar) {
        this.frame = frame;
        this.toolBar = toolBar;
        // 创建下拉工具栏
        dropDownButton.addActionListener(actionEvent -> popupMenu.show(dropDownButton, 0, dropDownButton.getHeight()));
        dropDownButton.setVisible(false);
        toolBar.add(dropDownButton);
        // 创建一份收起的按钮,用于在窗口遮住工具栏时展示
        ToolbarProvider toolbarProvider = new ToolbarProvider();
        for (Component component : toolbarProvider.provider(version).getComponents()) {
            component.setVisible(false);
            popupMenu.add(component);
        }
    }

    private void process(Component toolbarComponent, AbstractButton toolbarComponentButton) {
        Rectangle buttonBounds = toolbarComponent.getBounds();
        Rectangle toolbarBounds = toolBar.getBounds();
//        if (toolbarBounds.contains(buttonBounds)) {
//            for (Component popupMenuComponent : popupMenu.getComponents()) {
//                if (popupMenuComponent.getName().equals(toolbarComponent.getName())) {
//                    popupMenuComponent.setVisible(false);
//                }
//            }
//        } else {
//            for (Component popupMenuComponent : popupMenu.getComponents()) {
//                if (popupMenuComponent.getName().equals(toolbarComponent.getName())) {
//                    popupMenuComponent.setVisible(true);
//                }
//            }
//
//        }
        if (popupMenu.getComponents() == null || popupMenu.getComponents().length == 0) {
            return;
        }
        int popupMenuVisibleComponentCount = 0;
        for (Component popupMenuComponent : popupMenu.getComponents()) {
            AbstractButton popupMenuButton;
            if (popupMenuComponent instanceof JButton jButton) {
                popupMenuButton = jButton;
            } else if (popupMenuComponent instanceof JToggleButton jToggleButton) {
                popupMenuButton = jToggleButton;
            } else {
                return;
            }
            if (popupMenuButton.getText().equals(toolbarComponentButton.getText())) {
                if (toolbarBounds.contains(buttonBounds)) {
                    popupMenuComponent.setVisible(false);
                    popupMenuVisibleComponentCount--;
                    if (popupMenuVisibleComponentCount == 0) {
                        dropDownButton.setVisible(false);
                    }
                } else {
                    popupMenuComponent.setVisible(true);
                    dropDownButton.setVisible(true);
                    popupMenuVisibleComponentCount++;
                }
                dropDownButton.add(popupMenu);
                toolBar.add(dropDownButton);
                // 可以选择重新验证和重绘工具栏以确保新按钮显示正确
                toolBar.revalidate();
                toolBar.repaint();
            }
        }


    }

    @Override
    public void componentResized(ComponentEvent e) {
        for (Component component : popupMenu.getComponents()) {
            // 创建收起按钮及弹出按钮层
            for (Component comp : toolBar.getComponents()) {
                if (comp instanceof JButton button) {
                    process(comp, button);
                } else if (comp instanceof JToggleButton toggleButton) {
                    process(comp, toggleButton);
                }
            }
        }

    }
}
