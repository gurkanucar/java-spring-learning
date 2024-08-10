package org.gucardev.awss3fileservice;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class WatermarkService {

    public InputStream addWatermarkToPdf(InputStream inputPdfStream) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Create a temporary file for the downloaded PDF
        File tempFile = File.createTempFile("temp", ".pdf");
        try (OutputStream tempFileOutputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputPdfStream.read(buffer)) != -1) {
                tempFileOutputStream.write(buffer, 0, bytesRead);
            }
        }

        // Add watermark to the PDF
        PdfReader reader = new PdfReader(new FileInputStream(tempFile));
        int n = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, outputStream);

        // Generate watermark image with higher resolution
        int width = 350;
        int height = 350;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Set font size and type
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.setColor(new java.awt.Color(128, 128, 128, 64)); // Gray with 25% opacity
        g2d.rotate(Math.toRadians(-45), width / 2.0, height / 2.0);
        g2d.drawString("WATERMARK", 15, height / 2);
        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        Image watermarkImage = Image.getInstance(baos.toByteArray());
        watermarkImage.scaleAbsolute(150, 150);

        for (int i = 1; i <= n; i++) {
            PdfContentByte over = stamper.getOverContent(i);

            float interval = 150;
            for (float x = 0; x < over.getPdfDocument().getPageSize().getWidth(); x += interval) {
                for (float y = 0; y < over.getPdfDocument().getPageSize().getHeight(); y += interval) {
                    watermarkImage.setAbsolutePosition(x, y);
                    over.addImage(watermarkImage);
                }
            }
        }
        stamper.close();
        reader.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
