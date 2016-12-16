package level2.test.unitary;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.constants.Sizes;
import level2.interpreter.Bfck;
import level2.interpreter.Interpreter;
import level2.interpreter.Memory;
import level2.procedures.Function;
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
    public void procedureTest1(){
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
        int functionPointer = Sizes.MAXMEMORYSIZE.get()-Sizes.PROC_SIZE.get();
        byte caseTest1 = bfck.getMemory()[functionPointer];
        init(instructions);
        interpreter.handle(bfck);
        byte caseTest2 = bfck.getMemory()[0];
        assertEquals(caseTest2,caseTest1);
    }

    @Test
    public void functionReturnValueTest(){
        List<Executable> instructions = new ArrayList<>();
        instructions.add(InstructionEnum.INCR);
        instructions.add(InstructionEnum.INCR);
        instructions.add(InstructionEnum.INCR);
        Function function = new Function(instructions);
        List<Executable> func = new ArrayList<>();
        func.add(function);
        init(func);
        interpreter.handle(bfck);
        byte caseTest1 = bfck.getMemory()[0];

        assertEquals(-124,caseTest1);
    }

    @Test public void functionParamTest(){
        List<Executable> instructions = new ArrayList<>();
        instructions.add(InstructionEnum.LEFT);
        instructions.add(InstructionEnum.LEFT);
        instructions.add(InstructionEnum.INCR);
        byte param1 = 5;
        byte param2 = 6;
        Function function = new Function(instructions,param1,param2);
        List<Executable> func = new ArrayList<>();
        func.add(function);
        init(func);
        interpreter.handle(bfck);
        int functionPointer = Sizes.MAXMEMORYSIZE.get()-Sizes.PROC_SIZE.get();
        byte caseTest1 = bfck.getMemory()[functionPointer];

        assertEquals(param1+1,caseTest1);
    }
}
