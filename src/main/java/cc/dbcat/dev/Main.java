package cc.dbcat.dev;

import cc.dbcat.dev.ui.MainUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        JFrame jFrame = mainUI.init();
        jFrame.setVisible(true);
    }
}