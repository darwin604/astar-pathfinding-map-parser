import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridParser {

    public List<Grid> GetGrids(String input) throws IOException {

        int caseNum = 0;
        int totalCases;
        List<Grid> grids = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String curLine = reader.readLine();

        totalCases = Integer.parseInt(curLine); //Find total amount of test cases

        //Populate the grid list with grids from the sample file
        while (caseNum < totalCases) {

            Grid grid = new Grid();

            curLine = reader.readLine();
            String[] gridSize = curLine.split(" ");

            int width = Integer.parseInt(gridSize[0]);
            int height = Integer.parseInt(gridSize[1]);

            grid.createMapWithHVals(width, height);

            curLine = reader.readLine();
            String[] startEndCoords = curLine.split(" ");
            grid.setStartX(Integer.parseInt(startEndCoords[0]));
            grid.setStartY(Integer.parseInt(startEndCoords[1]));
            grid.setEndX(Integer.parseInt(startEndCoords[2]));
            grid.setEndY(Integer.parseInt(startEndCoords[3]));

            //Set the gas / "blocked" values for each node
            for (int x = 0; x < width; x++) {
                curLine = reader.readLine();
                String[] cellrow = curLine.split(" ");
                for (int y = 0; y < cellrow.length; y++) {
                    grid.setGasValue(x, y, Integer.parseInt(cellrow[y]));
                }
            }
            //System.out.println("Case: " + caseNum); //debug line

            grids.add(grid);

            caseNum++;

        }
        reader.close();
        return grids;
    }
}
