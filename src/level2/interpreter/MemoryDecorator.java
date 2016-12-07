package level2.interpreter;

public abstract class MemoryDecorator implements Memory {
    Memory decoratedBfck;

    public MemoryDecorator(Memory bfck){
        this.decoratedBfck = bfck;
    }
    @Override
    public byte[] getMemory(){
        return decoratedBfck.getMemory();
    }

    @Override
    public short getMemoryAt(short index){
        return decoratedBfck.getMemoryAt(index);
    }
    @Override
    public short getPointer(){
        return decoratedBfck.getPointer();
    }
    @Override
    public byte getCell(){
        return decoratedBfck.getCell();
    }

    @Override
    public byte getCellCheck() {
        return decoratedBfck.getCellCheck();
    }

    @Override
    public void incrCell(){decoratedBfck.incrCell();}

    @Override
    public void decrCell(){decoratedBfck.decrCell();}

    @Override
    public void setCase(byte val){
        decoratedBfck.setCase(val);
    }

    @Override
    public void left(){
        decoratedBfck.left();
    }

    @Override
    public void right(){
        decoratedBfck.right();
    }

    @Override
    public String toString(){
        return decoratedBfck.toString();
    }

    @Override
    public String toDebugString(){
        return decoratedBfck.toDebugString();
    }
}
