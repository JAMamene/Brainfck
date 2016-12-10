package level2.test.unitary;

import level2.argument.ArgsCheck;
import level2.exceptions.FileException;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

/**
 * test sur l'interpreteur d'agument
 */
public class ArgsTest {

    @org.junit.Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none(); //Cutom library 'system rule'
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void test0() {
        String args[] = {"-p", "fichier", "--rewrite"};
        ArgsCheck check = new ArgsCheck(args);


    }

    @Test
    public void test1() {
        String args[] = {"-p", "fichier", "--rewrite", "--translate", "--check"};
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test2() {
        String args[] = {"-p"};
        exception.expect(FileException.class);
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);

    }

    @Test
    public void test3() {
        String args[] = {"-p", "fichier", "zez"};
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test4() {
        String args[] = {"-p", "fichier", "-i"};
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test5() {
        String args[] = {"-p", "fichier", "--rewrite", "--translate", "--check", "--trace", "--showMetrics"};
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test6() {
        String args[] = {"-p", "fichier", "--rewrite", "--translate", "--check", "--trace", "--showMetrics", "-i", "in", "-o", "out"};
        ArgsCheck check = new ArgsCheck(args);
        System.out.println(check.getStoppingActions());
        System.out.println(check.getPassiveActions());
        System.out.println(check.getFileName());
        System.out.println(check.getIn());
        System.out.println(check.getOut());
    }

}


