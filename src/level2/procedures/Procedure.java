package level2.procedures;

import level2.constants.Executable;
import level2.constants.Sizes;
import level2.interpreter.Bfck;
import level2.interpreter.Interpreter;
import level2.interpreter.Memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static level2.constants.Sizes.MAXMEMORYSIZE;
import static level2.constants.Sizes.PROC_SIZE;

/**
 * Class modelizing a procedure
 */
public class Procedure implements IProcedure {
    Interpreter procedureInterpreter;

    public Procedure(List<Executable> instructions){
        procedureInterpreter = new Interpreter(instructions);
    }
    @Override
    public void exec(Memory bfck, Interpreter interpreter){
        int size = MAXMEMORYSIZE.get()-PROC_SIZE.get();
        short pointer = bfck.getPointer();
        bfck.setPointer((short) size);
        procedureInterpreter.handle(bfck);
        bfck.setPointer(pointer);
    }

}
