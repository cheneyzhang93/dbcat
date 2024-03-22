package cc.dbcat.dev.ui.image;

import cc.dbcat.dev.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageProvider {

    public static BufferedImage logo() {
        try {
            return ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/image/favicon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon icon(String path) {
        try {
            BufferedImage originalIconImage = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream(path)));
            // 调整图标大小
            int scaledWidth = 36; // 设置你想要的宽度
            int scaledHeight = 36; // 设置你想要的高度
            Image scaledIconImage = originalIconImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledIconImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
