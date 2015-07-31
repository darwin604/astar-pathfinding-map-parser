import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Logger {

    private PrintWriter writer;

    public Logger (String filename) throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new PrintWriter(filename, "UTF-8");
    }

    public void LogGas(int caseNum, int gas) {
        writer.print("Case #" + caseNum + ": ");

        if (gas == -1) {
            writer.println("Mission Impossible.");
        }
        else {
            writer.println(gas);
        }
    }

    public void CloseLogger() {
        writer.close();
    }

}
