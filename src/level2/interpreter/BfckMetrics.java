package level2.interpreter;

import level2.constants.Executable;
import level2.constants.Metrics;
import level2.constants.Trace;

import java.util.List;

public class BfckMetrics extends Bfck {

    public BfckMetrics(int progSize){
        super();
        Metrics.setProgSize(progSize);
    }
    @Override
    public void setCase(byte val){
        super.setCase(val);
        Metrics.incrDataWrite();
    }

    @Override
    public void incrCell(){
        Metrics.incrDataWrite();
        super.incrCell();
    }

    @Override
    public void decrCell(){
        Metrics.incrDataWrite();
        super.decrCell();
    }

    @Override
    public void addToPointer(int val){
        super.addToPointer(val);
        Metrics.incrDataMove();
    }

    @Override
    public byte getCell(){
        Metrics.incrDataRead();
        return super.getCell();
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(super.toString());
        res.append(Metrics.print());
        return res.toString();
    }
}
