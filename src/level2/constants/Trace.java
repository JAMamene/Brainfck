package level2.constants;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trace {

    static Calendar cal;
    static int step =1;
    static BufferedWriter writer;

    public static void saveState(int execPtr, int dataPtr){
        System.out.println("ecriture");
        try {
            writer.write(step + " | " + execPtr + " | " + dataPtr + " | ");
            writer.flush();
        }
        catch (IOException e) {
            // do something
        }
        step++;

    }
    /**
     * print all the data into a log file and save the file
     */
    public static void init() {

        try{
            File file = new File("p.log");
            file.createNewFile();

            writer = new BufferedWriter(new FileWriter(file));
            writer.write("Brainf*ck interpreter launched at "+getCurrentTime());
            writer.newLine();
            writer.newLine();
            writer.write("Step | Exec pointer | Data pointer | Data snapshot");
            writer.newLine();
            writer.write("-----|--------------|--------------|--------------");
            writer.flush();

        } catch (Exception e) {
            // do something
        }

    }

    private static String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static void end(){
        try{
            writer.close();
        }
        catch (Exception e) {
        // do something
        }
    }
}
