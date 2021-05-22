package onlineClassbook.service.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Audit {

    private static Audit audit;

    private Audit() {}

    public static Audit getAudit() {
        if (audit == null)
            audit = new Audit();
        return audit;
    }

    public void writeToAudit(String action) throws IOException {
        writeToAudit(action, "src/onlineClassbook/csvFiles/audit/Audit.csv");
    }

    public void writeToAudit(String action, String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        if(file.length() == 0) {
            bw.write("Action, Timestamp");
            bw.newLine();
        }
        String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        bw.write(action + ", " + timeStamp);
        bw.newLine();
        bw.close();
    }
}
