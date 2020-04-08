package com.ssu.juliablack;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

class Main {

    public static final String DIRECTORY = "F:\\";

    public static CountDownLatch countDownLatch;

    public static BufferedImage image;

    public static BufferedImage watermark;

    public static void main(String[] args) {
        countDownLatch = new CountDownLatch(2);

        new Thread(Main::readImage).start();
        new Thread(Main::readWatermark).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BufferedImage bufferedImage = drawWatermark(image, watermark);

        try {
            ImageIO.write(bufferedImage, "png", new File(DIRECTORY, "resultImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readImage() {
        try {
            Main.image = ImageIO.read(new File(DIRECTORY, "image.png"));
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readWatermark() {
        try {
            Main.watermark = ImageIO.read(new File(DIRECTORY, "watermark.png"));
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage drawWatermark(BufferedImage image, BufferedImage watermark) {
        Graphics2D graphics = image.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        graphics.drawImage(
                watermark,
                image.getWidth() / 2 - watermark.getWidth() / 2,
                image.getHeight() / 2 - watermark.getHeight() / 2,
                null);
        return image;
    }
}