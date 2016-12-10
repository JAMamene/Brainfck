package level2.constants;


import java.awt.*;
import java.util.Optional;

public interface Visualisable {

    Optional<String> getCode(Languages l);

    Color getColor();

    char getShortcut();
}
