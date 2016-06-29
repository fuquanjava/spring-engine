package captcha.impl;

import captcha.Captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


public class CaptchaFactory {
    private CaptchaFactory() {

    }

    public static Captcha genImage(int width, int height, int charCount) {
        // 在内存中创建图象
        if (width <= 0)
            width = 78;
        if (height <= 0)
            height = 32;
        if (charCount <= 0)
            charCount = 4;
        int charWidth = width / (charCount + 1);
        int charHeight = height - 8;
        int fontHeight = height - 2;

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
        graphics.setFont(font);

        graphics.setColor(Color.GRAY);
        graphics.drawRect(0, 0, width - 1, height - 1);

        graphics.setColor(Color.GRAY);
        for (int i = 0; i < 16; ++i) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int padX = random.nextInt(12);
            int padY = random.nextInt(12);
            graphics.drawLine(x, y, x + padX, y + padY);
        }
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};
        for (int i = 0; i < charCount; ++i) {
            String rand = String.valueOf(chars[random.nextInt(chars.length)]);
            int padding = (i == 0) ? 2 : 0;
            int red = random.nextInt(100);
            int green = random.nextInt(100);
            int blue = random.nextInt(100);
            graphics.setColor(new Color(red, green, blue));
            graphics.drawString(rand, (charWidth + 4) * i + padding, charHeight);
            str.append(rand);
        }

        graphics.dispose();
        return new Captcha(image, str.toString());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            CaptchaFactory.genImage(-1, -1, -1);
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
