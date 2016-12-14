package level2.procedures;

import level2.constants.Executable;
import level2.interpreter.Interpreter;
import level2.interpreter.Memory;

import java.util.List;

import static level2.constants.Sizes.MAXMEMORYSIZE;
import static level2.constants.Sizes.PROC_SIZE;

public class ParametrizedProcedure extends Procedure {
    byte[] parameters;

    public ParametrizedProcedure(List<Executable> instructions, byte... parameters){
        super(instructions);
        this.parameters = parameters;
    }

    @Override
    public void exec(Memory bfck, Interpreter interpreter){
        int size = MAXMEMORYSIZE.get()-PROC_SIZE.get();
        bfck.setPointer((short) size);
        for(int i = 0;i<parameters.length;i++){
            bfck.setCase(parameters[i]);
            bfck.right();
        }
        super.exec(bfck,interpreter);
    }

}
