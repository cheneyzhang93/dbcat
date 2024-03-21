package cc.dbcat.dev;

import cc.dbcat.dev.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        JFrame jFrame = mainFrame.init();
        jFrame.setVisible(true);
    }
}