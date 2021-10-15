package mc.user.service.utils;

import java.awt.image.BufferedImage;

public class CheckImage {
    private String imageKey;
    private BufferedImage image;

    public CheckImage(String imageKey, BufferedImage image) {
        this.imageKey = imageKey;
        this.image = image;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
