package level4.interpreter;

import level4.constants.Trace;

public class TraceDecorator extends MemoryDecorator {
    Interpreter interpreter;

    public TraceDecorator(Memory bfck, String filename, Interpreter interpreter) {
        super(bfck);
        Trace.init(filename);
        this.interpreter = interpreter;
    }

    @Override
    public void setCase(byte val) {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        super.setCase(val);
    }

    @Override
    public void incrCell() {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        super.incrCell();
    }

    @Override
    public void decrCell() {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        super.decrCell();
    }

    @Override
    public void right() {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        super.right();
    }

    @Override
    public void left() {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        super.left();
    }

    @Override
    public byte getCell() {
        Trace.saveState(interpreter.getInstruction(), getPointer(), toDebugString());
        return super.getCell();
    }
}
