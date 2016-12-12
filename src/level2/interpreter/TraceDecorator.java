package level2.interpreter;

import level2.constants.Metrics;
import level2.constants.Trace;

public class TraceDecorator extends MemoryDecorator {
    public TraceDecorator(Memory bfck,String filename){
        super(bfck);
        Trace.init(filename);
    }

    @Override
    public void setCase(byte val){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        super.setCase(val);
    }

    @Override
    public void incrCell(){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        super.incrCell();
    }

    @Override
    public void decrCell(){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        super.decrCell();
    }

    @Override
    public void right(){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        super.right();
    }

    @Override
    public void left(){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        super.left();
    }

    @Override
    public byte getCell(){
        Trace.saveState(Interpreter.instruction,getPointer(),toDebugString());
        return super.getCell();
    }
}
