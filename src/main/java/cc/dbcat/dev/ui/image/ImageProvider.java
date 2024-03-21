package cc.dbcat.dev.ui.image;

import cc.dbcat.dev.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
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
            return new ImageIcon(ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
