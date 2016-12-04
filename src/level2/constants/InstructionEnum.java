package level2.constants;

import level2.exceptions.ExecuteException;
import level2.exceptions.FileException;
import level2.interpreter.Bfck;

import java.awt.*;
import java.util.Scanner;

import static level2.constants.Sizes.*;

/**
 * enum for the correspondence between the different way of writing instructions
 */
public enum InstructionEnum implements Executable {
    INCR('+', new Color(0xffffff),
            "++mem[i];",
            "++*mem;") {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCellCheck() == MAXDATASIZE.get())
                throw new ExecuteException("cell-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.incrCell();
            bfck.incrementInstructions();
        }
    },

    DECR('-', new Color(0x4b0082),
            "--mem[i];",
            "--*mem;") {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getCellCheck() == MINDATASIZE.get())
                throw new ExecuteException("cell-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.decrCell();
            bfck.incrementInstructions();
        }
    },

    LEFT('<', new Color(0x9400D3),
            "--i;",
            "--mem") {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MINMEMORYSIZE.get())
                throw new ExecuteException("memory-underflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(-1);
            bfck.incrementInstructions();
        }
    },

    RIGHT('>', new Color(0x0000ff),
            "++i;",
            "++mem;") {
        @Override
        public void exec(Bfck bfck) {
            if (bfck.getPointer() == MAXMEMORYSIZE.get())
                throw new ExecuteException("memory-overflow", bfck.getPointer(), bfck.getInstruction());
            bfck.addToPointer(1);
            bfck.incrementInstructions();
        }
    },

    OUT('.', new Color(0x00ff00),
            "System.out.print(mem[i]);",
            "printf(%c,*mem);") {
        @Override
        public void exec(Bfck bfck) {
            System.out.print((char) (bfck.getCell() + MASK.get()));
            bfck.incrementInstructions();
        }
    },

    IN(',', new Color(0xffff00),
            "mem[i] = reader.next().charAt(0);",
            "scanf(%c,mem);") {
        @Override
        public void exec(Bfck bfck) {
            Byte in;
            if (bfck.getIn() == null) {
                Scanner s = new Scanner(System.in);
                in = (byte) s.next().charAt(0);
            } else {
                if (bfck.getIn().length() <= bfck.getReadId()) throw new FileException("unexpected-eof");
                in = (byte) bfck.getIn().charAt(bfck.getReadId());
                bfck.incrReadId();
            }
            if (in < MINDATASIZE.get() + MASK.get() || in > MAXDATASIZE.get() + MASK.get())
                throw new ExecuteException("invalid-input", in, bfck.getInstruction());
            bfck.setCase((byte) (in - MASK.get()));
            bfck.incrementInstructions();
        }
    },

    JUMP('[', new Color(0xff7f00),
            "while (mem[i] != 0) {",
            "while (*mem) {") {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() != -MASK.get()) {
                bfck.incrementInstructions();
            } else {
                bfck.setInstruction(bfck.getJumpTable().get(bfck.getInstruction()));
            }
        }
    },

    BACK(']', new Color(0xff0000),
            "}",
            "}") {
        @Override
        public void exec(Bfck bfck) {
            int i = bfck.getInstruction();
            if (bfck.getCell() == -MASK.get()) {
                bfck.incrementInstructions();
            } else {
                bfck.setInstruction(bfck.getJumpTable().get(bfck.getInstruction()));
            }
        }
    };

    private char shortcut;
    private Color color;
    private String java;
    private String c;

    InstructionEnum(char shortcut, Color color, String java, String c) {
        this.shortcut = shortcut;
        this.color = color;
        this.java = java;
        this.c = c;
    }

    public char getShortcut() {
        return shortcut;
    }

    public Color getColor() {
        return color;
    }

    public String getJava() {
        return java;
    }

    public String getC() {
        return c;
    }
}
