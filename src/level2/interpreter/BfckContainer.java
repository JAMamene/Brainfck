package level2.interpreter;

import level2.constants.Executable;
import level2.writer.BfWriter;
import level2.writer.ImageWriter;
import level2.writer.InstructionWriter;

import java.util.List;

public class BfckContainer {
    private Bfck bfck;
    private Interpreter interpreter;
    private BfWriter instructWriter;
    private BfWriter imgWriter;

    public BfckContainer(List<Executable> instructions,String filename,String filenameIn,String filenameOut){
        bfck = new Bfck();
        interpreter = new Interpreter(instructions,filename,filenameIn,filenameOut);
        instructWriter = new InstructionWriter();
        imgWriter = new ImageWriter();
    }

    public void check(){
        interpreter.check();
    }

    public void handle(){
        interpreter.handle(bfck);
    }

    public void print(){
        System.out.println(bfck);
    }

    public void rewrite(){
        instructWriter.WriteFile(interpreter.getVisualisableInstructions(),"");
    }

    public void setTrace(){
        interpreter.setTrace();
    }

    public void translate(){
        imgWriter.WriteFile(interpreter.getVisualisableInstructions(),interpreter.getFilename());
    }

    public void toMetrics(){
        bfck = new BfckMetrics(interpreter.getProgSize());
    }
}
