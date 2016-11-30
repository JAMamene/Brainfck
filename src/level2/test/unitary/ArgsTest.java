package level2.test.unitary;

import level2.command.*;
import level2.exceptions.WrongFile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        exception.expect(WrongFile.class);
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);

    }

    @Test
    public void test3(){
        String args[] = {"-p", "fichier","zez"};
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test4(){
        String args[] = {"-p", "fichier","-i"};
        exception.expect(WrongFile.class);
        exit.expectSystemExitWithStatus(3);
        ArgsCheck check = new ArgsCheck(args);
    }

    @Test
    public void test5(){
        String args[] = {"-p","fichier","--rewrite","--translate","--check","--trace","--showMetrics"};
        ArgsCheck check = new ArgsCheck(args);
        while(check.hasActions()) {
            Command c = check.nextAction();
            assertTrue( c instanceof RewriteCommand || c instanceof CheckCommand ||c instanceof TranslateCommand);
        }
        assertTrue(check.getMetrics());
        assertTrue(check.getTrace());
    }

}


