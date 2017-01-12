package unitary;

import level4.instructions.Executable;
import level4.instructions.InstructionEnum;
import level4.constants.Sizes;
import level4.interpreter.Bfck;
import level4.interpreter.Interpreter;
import level4.interpreter.Memory;
import level4.utils.Function;
import level4.utils.Procedure;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static level4.instructions.InstructionEnum.INCR;
import static level4.instructions.InstructionEnum.LEFT;
import static level4.instructions.InstructionEnum.RIGHT;
import static org.junit.Assert.assertEquals;

public class ProcedureTest {
    Interpreter interpreter;
    Memory bfck;

    @Before
    public void init() {
        interpreter = new Interpreter(new ArrayList<Executable>());
        bfck = new Bfck();
    }

    public void init(List<Executable> instructions) {
        interpreter = new Interpreter(instructions);
        bfck = new Bfck();

    }

    @Test
    public void procedureTest1() {
        List<Executable> instructions = new ArrayList<>();
        instructions.add(INCR);
        instructions.add(INCR);
        instructions.add(INCR);
        instructions.add(INCR);
        Procedure procedure = new Procedure(instructions);
        List<Executable> proc = new ArrayList<>();
        proc.add(procedure);
        init(proc);
        interpreter.handle(bfck);
        int functionPointer = Sizes.MAXMEMORYSIZE.get() - Sizes.PROC_SIZE.get();
        byte caseTest1 = bfck.getMemory()[functionPointer];
        init(instructions);
        interpreter.handle(bfck);
        byte caseTest2 = bfck.getMemory()[0];
        assertEquals(caseTest2, caseTest1);
    }

    @Test
    public void functionReturnValueTest() {
        List<Executable> instructions = new ArrayList<>();
        instructions.add(INCR);
        instructions.add(INCR);
        instructions.add(INCR);
        Function function = new Function(instructions);
        List<Executable> func = new ArrayList<>();
        func.add(function);
        init(func);
        interpreter.handle(bfck);
        byte caseTest1 = bfck.getMemory()[0];

        assertEquals(-124, caseTest1);
    }

    @Test
    public void functionParamTest() {
        List<Executable> instructions = new ArrayList<>();
        instructions.add(LEFT);
        instructions.add(LEFT);
        instructions.add(INCR);
        short param1 = 0;
        short param2 = 1;
        Function function = new Function(instructions);
        function = new Function(function,param1,param2);
        List<Executable> func = Arrays.asList(INCR,INCR,INCR,INCR,INCR,RIGHT,function);
        init(func);
        interpreter.handle(bfck);
        byte caseTest1 = bfck.getMemory()[1];

        assertEquals(6, caseTest1+Sizes.MASK.get());
    }
}
