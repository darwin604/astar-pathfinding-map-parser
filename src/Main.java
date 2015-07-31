import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Logger logger = new Logger("testout.dat");
        GridParser parser = new GridParser();
        List<Grid> grids = parser.GetGrids("input-large.in");

        System.out.println(grids.size());
        System.out.println("----");

        for (int i = 0; i < grids.size(); i++) {
            System.out.println(grids.get(i).driveAndGasUp());
        }

//        Grid testGrid = new Grid();

//        int width = 5;
//        int height = 4;
//
//        int[][] valueArray = new int[width][height];


        //=== Everything below this line is debugging code (although it does work) ===

//        int cellVal = 5;
//
//        for (int i = 0; i < width ; i++) {
//            for (int j = 0; j < height; j++) {
//                valueArray[i][j] = cellVal;
//                if (i == 2 && j == 3) {
//                    valueArray[i][j] = -1;
//                }
//
//
//            }
//
//        }
//
//        testGrid.setStartX(0);
//        testGrid.setStartY(0);
//        testGrid.setEndX(4);
//        testGrid.setEndY(3);
//        testGrid.createMapWithHVals(width, height);
//        testGrid.printMapValues();


        //System.out.println(testGrid.driveAndGasUp());


//        logger.LogGas(1,5);
//        logger.LogGas(2,10);
//        logger.LogGas(3, -1);
//        logger.LogGas(4, 20);

        // Teardown
        logger.CloseLogger();

    }
}
