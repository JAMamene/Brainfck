package level2.test.unitary;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.interpreter.Bfck;
import level2.interpreter.Interpreter;
import level2.interpreter.Memory;
import level2.procedures.Procedure;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class ProcedureTest {
    Interpreter interpreter;
    Memory bfck;

    @Before
    public void init(){
        interpreter = new Interpreter(new ArrayList<Executable>());
        bfck = new Bfck();
    }

    public void init(List<Executable> instructions){
        interpreter = new Interpreter(instructions);
        bfck = new Bfck();

    }

    @Test
    public void test1(){
        List<Executable> instructions = new ArrayList<>();
        instructions.add(InstructionEnum.INCR);
        instructions.add(InstructionEnum.INCR);
        instructions.add(InstructionEnum.INCR);
        instructions.add(InstructionEnum.INCR);
        Procedure procedure = new Procedure(instructions);
        List<Executable> proc = new ArrayList<>();
        proc.add(procedure);
        init(proc);
        interpreter.handle(bfck);
        byte caseTest1 = bfck.getMemory()[29499];
        init(instructions);
        interpreter.handle(bfck);
        byte caseTest2 = bfck.getMemory()[0];
        assertEquals(caseTest2,caseTest1);
    }
}
