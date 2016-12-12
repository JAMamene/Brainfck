package level2.test.unitary;

import level2.constants.Executable;
import level2.constants.InstructionEnum;
import level2.interpreter.Bfck;
import level2.interpreter.Interpreter;
import level2.interpreter.MetricsDecorator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MetricsDecoratorTest {
    @Test
    public void test1(){
        List<Executable> instructions = new ArrayList<>();
        instructions.add(InstructionEnum.RIGHT);
        instructions.add(InstructionEnum.RIGHT);
        Interpreter interpreter = new Interpreter(instructions,null,null,null);
        Bfck bfck = new Bfck();
        MetricsDecorator metrics = new MetricsDecorator(bfck,100);
        interpreter.handle(bfck);
        System.out.println(bfck.getPointer());
    }
}
