package unitary;

import level4.instructions.Executable;
import level4.instructions.InstructionEnum;
import level4.interpreter.Bfck;
import level4.interpreter.Interpreter;
import level4.interpreter.MetricsDecorator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MetricsDecoratorTest {
    @Test
    public void test1() {
        List<Executable> instructions = new ArrayList<>();
        instructions.add(InstructionEnum.RIGHT);
        instructions.add(InstructionEnum.RIGHT);
        Interpreter interpreter = new Interpreter(instructions);
        Bfck bfck = new Bfck();
        MetricsDecorator metrics = new MetricsDecorator(bfck, 100);
        interpreter.handle(bfck);
        System.out.println(bfck.getPointer());
    }
}
