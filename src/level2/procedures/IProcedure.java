package level2.procedures;

import level2.constants.Executable;

import java.util.List;

/**
 * Created on 04/12/2016.
 * Polytech'Nice Sophia - SI3
 *
 * @author Julien Maureille
 */
public interface IProcedure {
    void declare(String name, List<Executable> body);

    List<Executable> getBody(String name);
}
