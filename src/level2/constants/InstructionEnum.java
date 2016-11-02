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
public enum InstructionEnum {
    INCR('+', new Color(0xffffff)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCell() == MAXDATASIZE.get())
                throw new ExecuteException("cell-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.setCase((byte) (bfck.getCell() + 1));
            bfck.addToInstruction(1);
        }
    },
    DECR('-', new Color(0x4b0082)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCell() == MINDATASIZE.get())
                throw new ExecuteException("cell-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.setCase((byte) (bfck.getCell() - 1));
            bfck.addToInstruction(1);
        }
    },
    LEFT('<', new Color(0x9400D3)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MINMEMORYSIZE.get())
                throw new ExecuteException("memory-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(-1);
            bfck.addToInstruction(1);
        }
    },
    RIGHT('>', new Color(0x0000ff)) {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MAXMEMORYSIZE.get())
                throw new ExecuteException("memory-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(1);
            bfck.addToInstruction(1);
        }
    },
    OUT('.', new Color(0x00ff00)) {
        @Override
        public void exec(Bfck bfck) {
            System.out.println(bfck.printableCell(bfck.getPointer()));
            bfck.addToInstruction(1);
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
            bfck.addToInstruction(1);
        }
    },
    JUMP('[', new Color(0xff7f00)) {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() != - MASK.get()) {
                bfck.addToInstruction(1);
            } else {
                for (int j = i; j < bfck.getInstructions().size(); j++) {
                    if (bfck.bound(i, j, true)) {
                        bfck.addToInstruction(j - i + 1);
                        break;
                    }
                }
            }

        }
    },
    BACK(']', new Color(0xff0000)) {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() ==  - MASK.get()) {
                bfck.addToInstruction(1);
            } else {
                for (int j = i; j > 0; j--)
                    if (bfck.bound(j, i, false)) {
                        bfck.addToInstruction(j - i + 1);
                        break;
                    }
            }
        }
    };

    public abstract void exec(Bfck bfck);

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
