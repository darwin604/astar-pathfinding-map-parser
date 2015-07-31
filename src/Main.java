import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        runPathTests(args[0]); //Find the paths and log the output

    }

    private static void runPathTests(String input) throws IOException {
        Logger logger = new Logger("testout.dat");
        GridParser parser = new GridParser();
        List<Grid> grids = parser.GetGrids(input);

        for (int i = 0; i < grids.size(); i++) {
            logger.LogGas(i + 1, grids.get(i).driveAndGasUp() );
        }

        // Teardown
        logger.CloseLogger();
    }
}
