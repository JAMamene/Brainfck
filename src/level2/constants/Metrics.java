package level2.constants;

public class Metrics {
    private static long PROG_SIZE = 0;
    private static double EXEC_TIME = System.currentTimeMillis();
    private static long EXEC_MOVE = 0;
    private static long DATA_MOVE = 0;
    private static long DATA_WRITE = 0;
    private static long DATA_READ = 0;

    public static long getProgSize() {
        return PROG_SIZE;
    }

    public static long getExecMove() {
        return EXEC_MOVE;
    }

    public static double getExecTime() {
        return EXEC_TIME;
    }

    public static long getDataMove() {
        return DATA_MOVE;
    }

    public static long getDataWrite() {
        return DATA_WRITE;
    }

    public static long getDataRead() {
        return DATA_READ;
    }

    public static void setProgSize(long progSize) {
        PROG_SIZE = progSize;
    }

    public void incrExecMove(){
        EXEC_MOVE++;
    }

    public void incrDataMove(){
        DATA_MOVE++;
    }

    public void incrDataWrite(){
        DATA_WRITE++;
    }

    public void incrDataRead(){
        DATA_READ++;
    }

    public void setExecTime(){
        EXEC_TIME = System.currentTimeMillis() - EXEC_TIME;
    }
}
