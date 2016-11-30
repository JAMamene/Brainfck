package level2.constants;

import level2.exceptions.ExecuteException;
import level2.exceptions.WrongFile;
import level2.interpreter.Bfck;

import java.awt.*;
import java.util.Scanner;

import static level2.constants.Sizes.*;

/**
 * enum for the correspondence between the different way of writing instructions
 */
public enum InstructionEnum implements Executable {
    INCR('+', new Color(0xffffff)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCell() == MAXDATASIZE.get())
                throw new ExecuteException("cell-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.setCase((byte) (bfck.getCell() + 1));
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataWrite();
        }
    },
    DECR('-', new Color(0x4b0082)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCell() == MINDATASIZE.get())
                throw new ExecuteException("cell-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.setCase((byte) (bfck.getCell() - 1));
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataWrite();
        }
    },
    LEFT('<', new Color(0x9400D3)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MINMEMORYSIZE.get())
                throw new ExecuteException("memory-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(-1);
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataMove();
        }
    },
    RIGHT('>', new Color(0x0000ff)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MAXMEMORYSIZE.get())
                throw new ExecuteException("memory-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(1);
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataMove();
        }
    },
    OUT('.', new Color(0x00ff00)) {
        @Override
        public void exec(Bfck bfck) {
            System.out.print((char) (bfck.getMemoryAt(bfck.getPointer()) + MASK.get()));
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataRead();
        }
    },
    IN(',', new Color(0xffff00)) {
        @Override
        public void exec(Bfck bfck) {
            Byte in;
            if (bfck.getIn() == null) {
                Scanner s = new Scanner(System.in);
                in = (byte) s.next().charAt(0);
            } else {
                if (bfck.getIn().length() <= bfck.getReadId()) throw new WrongFile("unexpected-eof");
                in = (byte) bfck.getIn().charAt(bfck.getReadId());
                bfck.incrReadId();
            }
            if (in < MINDATASIZE.get() + MASK.get() || in > MAXDATASIZE.get() + MASK.get())
                throw new ExecuteException("invalid-input", in, bfck.getInstruction());
            bfck.setCase((byte) (in - MASK.get()));
            bfck.incrementInstructions();
            if(Metrics.isOn())Metrics.incrDataWrite();
        }
    },
    JUMP('[', new Color(0xff7f00)) {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() != -MASK.get()) {
                bfck.incrementInstructions();
            } else {
                bfck.setInstruction(bfck.getJumpTable().get(bfck.getInstruction()));
            }
            if(Metrics.isOn())Metrics.incrDataRead();
        }
    },
    BACK(']', new Color(0xff0000)) {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() == -MASK.get()) {
                bfck.incrementInstructions();
            } else {
                bfck.setInstruction(bfck.getJumpTable().get(bfck.getInstruction()));
            }
            if(Metrics.isOn())Metrics.incrDataRead();
        }
    };

    private char shortcut;
    private Color color;

    InstructionEnum(char shortcut, Color color) {
        this.shortcut = shortcut;
        this.color = color;
    }

    public char getShortcut() {
        return shortcut;
    }

    public Color getColor() {
        return color;
    }
}
