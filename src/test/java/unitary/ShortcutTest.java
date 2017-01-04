package unitary;
import level4.instructions.Executable;
import level4.instructions.InstructionEnum;
import level4.reader.parser.Shortcut;
import level4.utils.Procedure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class ShortcutTest {
    Shortcut shortcut;

    @Before
    public void init(){
        shortcut= new Shortcut();
    }
    @Test
    public void ShortcutTest1(){
        List<Executable> instrucut= new ArrayList<>();
        instrucut.add(InstructionEnum.INCR);
        Procedure proc=new Procedure(instrucut);
        shortcut.addProcedure("ZERO",proc);
        assertTrue(shortcut.getExecutable("ZERO[]") instanceof Procedure);


    }


}
