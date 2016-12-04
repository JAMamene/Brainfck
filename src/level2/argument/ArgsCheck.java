package level2.argument;

import level2.command.Command;

import java.util.ArrayList;
import java.util.List;

public class ArgsCheck {
    private String fileName;
    private String fileExtension;
    private String in;
    private String out;
    private Boolean trace=false;
    private boolean metrics = false;
    private List<Command> actions;
    private List<Command> passiveActions;
    private List<Command> stoppingActions;
    private int pointer ;
    private String[] args;

    /**
     * parsing the args array
     * if the parameter is supported it refresh an attribute
     * a file must be specified even if it doesn't exist else throw an exception
     */
    public ArgsCheck(String[] args) {
        if(args.length == 0) System.exit(3);
        this.args = args;
        passiveActions = new ArrayList<>();
        stoppingActions = new ArrayList<>();
        for(pointer = 0;pointer < args.length ; incrPointer()){
            for(ArgEnum arg : ArgEnum.values()){
                if(arg.getExpression().equals(args[pointer])) {
                    arg.exec(this);
                    break;
                }
                if(arg.getExpression().equals("???"))arg.exec(this);
            }
        }

    }



    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public boolean getMetrics(){return metrics;}

    public void setMetrics(boolean metrics) {
        this.metrics = metrics;
    }

    public boolean hasStoppingActions() {
        return !stoppingActions.isEmpty();
    }

    public boolean hasPassiveActions() {
        return !passiveActions.isEmpty();
    }

    public Command nextStoppingAction() {
        Command action = stoppingActions.get(0);
        stoppingActions.remove(action);
        return action;
    }

    public Command nextPassiveAction() {
        Command action = passiveActions.get(0);
        passiveActions.remove(action);
        return action;
    }

    public Boolean getTrace() {
            return trace;
    }

    public int getPointer() {
        return pointer;
    }

    public int incrPointer(){
        return pointer++;
    }

    public void addPassiveActions(Command action){
        passiveActions.add(action);
    }

    public void addStoppingActions(Command action){
        stoppingActions.add(action);
    }

    public String getArg() {
        return args[pointer];
    }

    public List<Command> getPassiveActions() {
        return passiveActions;
    }

    public List<Command> getStoppingActions() {
        return stoppingActions;
    }
}
