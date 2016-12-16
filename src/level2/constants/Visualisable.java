package level2.constants;


import java.awt.*;
import java.util.Optional;

/**
 * interface for the visualisable objects (instructions or optimized instructions)
 */
public interface Visualisable {

    /**
     * returns the code representation of a visualisable depending on the language selected
     *
     * @param l the language
     * @return a String to print (or an empty optional)
     */
    Optional<String> getCode(Languages l);

    /**
     * returns the color representation of a visualisable
     * @return the color
     */
    Color getColor();

    /**
     * retunrs the short representation of a visualisable
     * @return the short notation
     */
    char getShortcut();
}
