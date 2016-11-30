package level2.test.unitary;

import level2.command.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * test sur l'interpreteur d'agument
 */
public class ArgsTest {

    @Test
    public void test0() {
        String args[] = {"-p", "fichier", "--rewrite"};
        ArgsCheck check = new ArgsCheck(args);

        assertTrue(check.nextAction() instanceof RewriteCommand);

    }

    @Test
    public void test1() {
        String args[] = {"-p", "fichier", "--rewrite", "--translate", "--check"};
        ArgsCheck check = new ArgsCheck(args);
        while(check.hasActions()) {
            Command c = check.nextAction();
            assertTrue( c instanceof RewriteCommand || c instanceof CheckCommand ||c instanceof TranslateCommand);
        }
    }

    @Test
    public void test2() {
        String args[] = {"-p"};
        ArgsCheck check = new ArgsCheck(args);

    }


}


