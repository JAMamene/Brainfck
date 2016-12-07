package level2.reader;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.exceptions.FileException;
import level2.exceptions.SyntaxException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static level2.constants.Sizes.SQUARE_SIZE;

/**
 * A class that reads from an image, implements bfReader
 *
 * @author Julien Maureille
 */
public class ImageReader implements BfReader {

    /**
     * A method that reads from an image
     */
    @Override
    public List<Executable> readFile(String fileName) throws FileException {
        BufferedImage img;

        List<Executable> instructions = new ArrayList<>();

        try {
            // load the image in memory
            img = ImageIO.read(new File(fileName));

            // Custom exception if the provided bmp is not square
            // or if its width/height is not a multiple of SQUARE_SIZE
            if ((img.getHeight() != img.getWidth()) || (img.getWidth() % SQUARE_SIZE.get() != 0))
                throw new FileException("dimension-image");

            // PROCESSING : For each colored square...

            for (int x = 0; x < img.getWidth(); x += SQUARE_SIZE.get()) {
                for (int y = 0; y < img.getHeight(); y += SQUARE_SIZE.get()) {

                    // get RGB values of the current colored square
                    Color color = new Color(img.getRGB(y, x)); // y then x because we read by lines and not by columns

                    Boolean found = false; // boolean to check if we

                    // Finding related instruction among the enum
                    for (InstructionEnum i : InstructionEnum.values()) {
                        if (color.equals(i.getColor())) {
                            instructions.add(i);
                            found = true;
                            break;
                        }
                    }
                    if ((!found) && (!color.equals(Color.BLACK))) {
                        throw new SyntaxException(x, y, color);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileException("missing-file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
    }
}
