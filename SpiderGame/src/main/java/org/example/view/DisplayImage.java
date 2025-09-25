package org.example.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayImage extends JPanel {

    private String imagePath;
    private BufferedImage originalImage;

    public DisplayImage(String imagePath, int width, int height) {
        super();
        this.imagePath = imagePath;
        try {
            this.originalImage = ImageIO.read(new File(this.imagePath));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        setPreferredSize(new Dimension(width, height));
    }

    // Multi-step scaling method
    private BufferedImage multiStepScale(BufferedImage img, int targetWidth, int targetHeight) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage tempImg = img;

        // Scale in steps until we reach target size
        while (w < targetWidth || h < targetHeight) {
            w = Math.min(w * 2, targetWidth);
            h = Math.min(h * 2, targetHeight);

            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = tmp.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(tempImg, 0, 0, w, h, null);
            g2d.dispose();

            tempImg = tmp;
        }
        return tempImg;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (originalImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // High-quality rendering hints
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Use multi-step scaling for better sharpness
            BufferedImage scaledImage = multiStepScale(originalImage, getWidth(), getHeight());
            g2d.drawImage(scaledImage, 0, 0, getWidth(), getHeight(), this);

            g2d.dispose();
        }
    }
}
