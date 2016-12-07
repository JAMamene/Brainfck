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

    /**
     * set the program size
     *
     * @param progSize size of the program
     */
    public static void setProgSize(long progSize) {
        PROG_SIZE = progSize;
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

    /**
     * launch the execution time
     */
    public static void beginExecTime() {
        EXEC_TIME = System.currentTimeMillis();
    }

    /**
     * end the execution timer
     */
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

    /**
     * print all the data
     */
    public static String print() {
        StringBuilder res = new StringBuilder();
        res.append("PROG SIZE: " + PROG_SIZE + "\n");
        res.append("EXEC TIME: " + EXEC_TIME + "\n");
        res.append("EXEC MOVE: " + EXEC_MOVE + "\n");
        res.append("DATA MOVE: " + DATA_MOVE + "\n");
        res.append("DATA WRITE: " + DATA_WRITE + "\n");
        res.append("DATA READ: " + DATA_READ + "\n");
        return res.toString();
    }
}
