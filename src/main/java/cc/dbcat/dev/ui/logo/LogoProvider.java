package cc.dbcat.dev.ui.logo;

import cc.dbcat.dev.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LogoProvider {

    public BufferedImage provider() {
        try {
            return ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/image/favicon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
