package level2.interpreter;

import level2.constants.Metrics;

public class MetricsDecorator extends MemoryDecorator{
    public MetricsDecorator(Memory bfck,long progsize){
        super(bfck);
        Metrics.setProgSize(progsize);
    }

    @Override
    public void setCase(byte val){
        super.setCase(val);
        Metrics.incrDataWrite();
        Metrics.incrExecMove();
    }

    @Override
    public void incrCell(){
        Metrics.incrDataWrite();
        super.incrCell();
        Metrics.incrExecMove();
    }

    @Override
    public void decrCell(){
        Metrics.incrDataWrite();
        super.decrCell();
        Metrics.incrExecMove();
    }

    @Override
    public void right(){
        super.right();
        Metrics.incrDataMove();
        Metrics.incrExecMove();
    }

    @Override
    public void left(){
        super.left();
        Metrics.incrDataMove();
        Metrics.incrExecMove();
    }

    @Override
    public byte getCell(){
        Metrics.incrDataRead();
        Metrics.incrExecMove();
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
