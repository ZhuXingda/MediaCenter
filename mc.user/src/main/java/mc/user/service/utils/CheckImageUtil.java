package mc.user.service.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图形验证码生成
 */
public class CheckImageUtil {
    // 默认验证码字符集
    private static final char[] chars = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    // 默认字符数量
    private static final Integer SIZE = 4;
    // 默认干扰线数量
    private static final int LINES = 5;
    // 默认宽度
    private static final int WIDTH = 150;
    // 默认高度
    private static final int HEIGHT = 50;
    // 默认字体大小
    private static final int FONT_SIZE = 30;
    // 默认字体倾斜
    private static final boolean TILT = true;
    //背景色
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    /**
     * @return 生成随机验证码及图片
     */
    public static CheckImage createImage() {
        StringBuilder word = new StringBuilder();
        // 创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取图片画笔
        Graphics2D graphic = image.createGraphics();
        // 设置抗锯齿
        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置画笔颜色
        graphic.setColor(BACKGROUND_COLOR);
        // 绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 画随机字符
        Random ran = new Random();
        // 计算每个字符占的宽度，这里预留一个字符的位置用于左右边距
        int codeWidth = WIDTH / (SIZE + 1);
        // 字符所处的y轴的坐标
        int y = HEIGHT * 3 / 4;
        for (int i = 0; i < SIZE; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 初始化字体
            Font font = new Font(null, Font.ITALIC, FONT_SIZE);

            if (TILT) {
                // 随机一个倾斜的角度 -45到45度之间
                int theta = ran.nextInt(45);
                // 随机一个倾斜方向 左或者右
                theta = (ran.nextBoolean()) ? theta : -theta;
                AffineTransform affineTransform = new AffineTransform();
                affineTransform.rotate(Math.toRadians(theta), 0, 0);
                font = font.deriveFont(affineTransform);
            }
            // 设置字体大小
            graphic.setFont(font);
            // 计算当前字符绘制的X轴坐标
            int x = (i * codeWidth) + (codeWidth / 2);
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 记录字符
            word.append(chars[n]);
            // 画字符
            graphic.drawString(String.valueOf(chars[n]), x, y);
        }
        // 画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 返回验证码和图片
        return new CheckImage(word.toString(),image);
    }

    /**
     * 随机取色
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        return color;
    }

}

