package level2.interpreter;

import java.util.Arrays;

import static level2.constants.Sizes.MASK;
import static level2.constants.Sizes.MAXMEMORYSIZE;
import static level2.constants.Sizes.MINDATASIZE;

public interface Memory {

    byte[] getMemory();
    byte getCell();
    byte getCellCheck();
    void incrCell();
    void decrCell();
    void left();
    void right();
    short getMemoryAt(short index);
    short getPointer();
    void setCase(byte val);
    String toDebugString();
}
