package level2.writer;

import level2.constants.Executable;
import level2.constants.Visualisable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static level2.constants.Sizes.SQUARE_SIZE;

/**
 * Created on 28/09/2016.
 * Polytech'Nice Sophia - SI3
 * <p>
 * A class that create an image from text instructions, implements bfWriter
 *
 * @author Julien Maureille
 */

public class ImageWriter implements BfWriter {

    /**
     * Method that create an image named program.bmp where each instruction is stored as a 3x3 square
     *
     * @param instructions List of shortcut instructions
     */
    public void WriteFile(List<Visualisable> instructions, String fileName) {

        // get the number of instructions
        int nbInstruct = instructions.size();

        // calculate the image width (width = height)
        int imgWidth = (int) Math.ceil(Math.sqrt(nbInstruct)) * SQUARE_SIZE.get();

        // create the image
        BufferedImage img = new BufferedImage(imgWidth, imgWidth, BufferedImage.TYPE_INT_RGB);

        // get Graphics of img
        Graphics2D g = img.createGraphics();

        // coordinates of the current square
        int x = 0;
        int y = 0;

        // for each instruction ...
        for (Visualisable i : instructions) {

            // if i am out of image bounds : carriage return
            if (x > imgWidth - SQUARE_SIZE.get()) {
                x = 0;
                y += SQUARE_SIZE.get();
            }

            // get the color linked to the current instruction
            g.setColor(i.getColor());

            // draw the square
            g.fillRect(x, y, SQUARE_SIZE.get(), SQUARE_SIZE.get());

            // move forward
            x += SQUARE_SIZE.get();
        }

        try {
            // create the bmp file

            // checks if the file has an extension, if it has one, remove it
            if ((fileName.lastIndexOf('.')) != -1) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            }

            //put the new extension at the end of the filename
            File outputFile = new File(fileName + ".bmp");

            // write generated image in the file
            ImageIO.write(img, "bmp", outputFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
