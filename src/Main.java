public class Main {

    public static void main(String[] args) {

        Grid testGrid = new Grid();

        int width = 5;
        int height = 4;

        int[][] valueArray = new int[width][height];


        //=== Everything below this line is debugging code (although it does work) ===

        int cellVal = 5;

        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height; j++) {
                valueArray[i][j] = cellVal;
                if (i == 2 && j == 3) {
                    valueArray[i][j] = -1;
                }


            }

        }

        testGrid.setStartX(0);
        testGrid.setStartY(0);
        testGrid.setEndX(4);
        testGrid.setEndY(3);
        testGrid.createMapWithValues(width, height, valueArray);
        testGrid.printMapValues();

        System.out.println(testGrid.driveAndGasUp());

    }
}
