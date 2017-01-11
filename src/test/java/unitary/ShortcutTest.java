package unitary;

import level4.exceptions.ArgumentException;
import level4.exceptions.ExecuteException;
import level4.instructions.Executable;
import level4.reader.parser.Shortcut;
import level4.utils.Function;
import level4.utils.Procedure;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static level4.instructions.InstructionEnum.INCR;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortcutTest {
    Shortcut shortcut;
    public final ExpectedException exception = ExpectedException.none();
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void init() {
        shortcut = new Shortcut();
    }

    @Test
    public void ShortcutTest1() {
        List<Executable> instrucut = new ArrayList<>();
        instrucut.add(INCR);
        Procedure proc = new Procedure(instrucut);
        shortcut.addProcedure("ZERO", proc);
        assertTrue(shortcut.getExecutable("ZERO[]").equals(proc));
    }

    @Test
    public void ShortcutTest2(){
        List<Executable> instru = Arrays.asList(INCR,INCR);
        Procedure proc = new Procedure(instru);
        shortcut.addProcedure("proc",proc);
        Procedure proc1 = new Procedure(proc,(short)150,(short)6,(short)4,(short)10);
        assertEquals(shortcut.getExecutable("proc[150,6,4,10]"),proc1);
    }

    @Test
    public void ShortcutTestException(){
        List<Executable> inst = Arrays.asList(INCR);
        Procedure proc = new Procedure(inst);
        shortcut.addProcedure("proc",proc);
        exception.expect(ArgumentException.class);
        exit.expectSystemExitWithStatus(5);
        shortcut.getExecutable("proc[1000000000000000000000]");
    }

    @Test
    public void ShortcutFunc(){
        List<Executable> instru = Arrays.asList(INCR,INCR);
        Function proc = new Function(instru);
        shortcut.addProcedure("proc",proc);
        Function proc1 = new Function(proc,(short)150,(short)65,(short)450,(short)100);
        assertEquals(shortcut.getExecutable("proc[150,65,450,100]"),proc1);
    }
}
