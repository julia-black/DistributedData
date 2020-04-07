package com.ssu.juliablack;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

class Main {

    public static void main(String[] args) throws IOException {
        String directory = "F:\\";

        BufferedImage image = ImageIO.read(new File(directory, "image.png"));
        byte[] byteImage = imageToByteArray(image);

        BufferedImage watermark = ImageIO.read(new File(directory, "watermark.png"));
        byte[] byteWatermark = imageToByteArray(watermark);

        for (int i = 0; i < byteWatermark.length; i++) {
            byteImage[i] = byteWatermark[i];
        }

        int width = 1500;
        int height = 1500;

        DataBuffer buffer = new DataBufferByte(byteImage, byteImage.length);
        WritableRaster raster = Raster.createInterleavedRaster(buffer, width, height, 3 * width, 3, new int[]{0, 1, 2}, null);
        image.setData(raster);
        BufferedImage resultImage = new BufferedImage(image.getColorModel(), image.getRaster(), true, null);
        ImageIO.write(resultImage, "png", new File(directory, "resultImage.png"));
    }

    private static byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        ImageIO.write(image, "png", baos);
        baos.flush();
        String base64String = Base64.getEncoder().encodeToString(baos.toByteArray());
        baos.close();

        return Base64.getDecoder().decode(base64String);
    }
}