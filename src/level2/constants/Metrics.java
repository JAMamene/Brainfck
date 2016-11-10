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

    public static void incrExecMove() {
        EXEC_MOVE++;
    }

    public static void incrDataMove() {
        DATA_MOVE++;
    }

    public static void incrDataWrite() {
        DATA_WRITE++;
    }

    public static void incrDataRead() {
        DATA_READ++;
    }

    public static void beginExecTime() {
        EXEC_TIME = System.currentTimeMillis();
    }

    public static void endExecTime() {
        EXEC_TIME = System.currentTimeMillis() - EXEC_TIME;
    }

    public static void reset() {
        PROG_SIZE = 0;
        EXEC_TIME = System.currentTimeMillis();
        EXEC_MOVE = 0;
        DATA_MOVE = 0;
        DATA_WRITE = 0;
        DATA_READ = 0;
    } // For testing

    public static void print() {
        System.out.println("PROG SIZE: " + PROG_SIZE);
        System.out.println("EXEC TIME: " + EXEC_TIME);
        System.out.println("EXEC MOVE: " + EXEC_MOVE);
        System.out.println("DATA MOVE: " + DATA_MOVE);
        System.out.println("DATA WRITE: " + DATA_WRITE);
        System.out.println("DATA READ: " + DATA_READ);
    }
}
