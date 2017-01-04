package level4.interpreter;

public interface Memory {

    byte[] getMemory();

    byte getCell();

    byte getCellCheck();

    void incrCell();

    void decrCell();

    void left();

    void right();

    byte getMemoryAt(short index);

    short getPointer();

    void setPointer(short val);

    void setCase(byte val);

    String toDebugString();
}
