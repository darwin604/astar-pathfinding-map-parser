import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        runPathTests(); //Find the paths and log the output

    }

    private static void runPathTests() throws IOException {
        Logger logger = new Logger("testout.dat");
        GridParser parser = new GridParser();
        List<Grid> grids = parser.GetGrids("input-large.in");

        for (int i = 0; i < grids.size(); i++) {
            logger.LogGas(i + 1, grids.get(i).driveAndGasUp() );
        }

        // Teardown
        logger.CloseLogger();
    }
}
