import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

    // We will create the driving map from a 2D array of Square objects
    private Square[][] map;

    private int startX;
    private int startY;

    private int endX;
    private int endY;

    private int gridWidth;
    private int gridHeight;

    // Accessors left in for debugging (safe to remove most)

    public void setGasValue(int x, int y, int value){
        map[x][y].setBlockOrGasVal(value);
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    // Creates "map" and adds heuristic values + gas / no-go values
    public void createMapWithHVals(int width, int height) {
        map = new Square[width][height];
        gridWidth = width;
        gridHeight = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Square(i, j);
                map[i][j].setHVal(findAbsoluteDistance(i, j));
            }

        }
    }

    // For debug purposes - not called @ runtime
    public void printMapValues() {
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                System.out.print("X:" + j + ",");
                System.out.print("Y:" + i + ",");
                System.out.print("H:" + map[j][i].getHVal() + " ");
                //System.out.print("Val:" + map[i][j].getBlockOrGasVal() + " ");
            }
            System.out.println();

        }
    }

    // Find the distance (in moves) from the specified square to target ignoring blockages
    // Using the Manhattan Distance since no diagonal moves are permitted
    public int findAbsoluteDistance(int currentX, int currentY) {

        int dX = Math.abs(endX - currentX);
        int dY = Math.abs(endY - currentY);
        return dX + dY;
    }

    private List<Square> getShortestPath(List<Square> cl) {
        List<Square> path = new ArrayList<>();

        int i = cl.size() - 1;
        while (cl.get(i).getParent() != null) {
            path.add(cl.get(i));
            i = cl.indexOf(cl.get(i).getParent());
        }
        path.add(cl.get(0)); //first node will always be the origin square, add it to the list

        return path;
    }

    private int getTotalGas(List<Square> path) {
        int gas = 0;

        for (Square aPath : path) {
            gas = gas + aPath.getBlockOrGasVal();
        }

        return gas;
    }

    // Calculates the shortest path and returns the gas value collected along the way
    // Reference used: http://www.policyalmanac.org/games/aStarTutorial.htm
    public int driveAndGasUp() {

        List<Square> openList = new ArrayList<>();
        List<Square> closedList = new ArrayList<>();
        List<Square> shortestPath;

        // Set the current node to the start node
        int currentX;
        int currentY;

        // Add start (current) position to open list
        openList.add(map[startX][startY]);

        // loop while there are still places to go (if it's empty and target doesn't go in closed list, mission impossible)
        while (!closedList.contains(map[endX][endY])) {

            if (openList.isEmpty()) {
                //System.out.println("Mission Impossible."); //debug
                return -1;
            }

            // sort the open list by hval
            Collections.sort(openList, Square.COMPARE_BY_HVAL);

            // set current square to lowest hval in open list (sorted so will be at position 0 in list)
            currentX = openList.get(0).getxPos();
            currentY = openList.get(0).getyPos();

            // move lowest hval node (current) to the closed list
            closedList.add(openList.get(0));
            openList.remove(0);

            // check all sides
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    // we don't want to check diagonals so skip those
                    if (Math.abs(x) == Math.abs(y)) {
                        continue;
                    }
                    // make sure we're not checking outside of our array bounds
                    if (currentX == 0 && x == -1) {
                        continue;
                    }
                    if (currentY == 0 && y == -1) {
                        continue;
                    }
                    if (currentX == gridWidth - 1 && x == 1) {
                        continue;
                    }
                    if (currentY == gridHeight - 1 && y == 1) {
                        continue;
                    }

                    // if the neighboring square square is walkable
                    if (map[currentX + x][currentY + y].getBlockOrGasVal() != -1 && !closedList.contains(map[currentX + x][currentY + y])) {

                        // if the square is not already in the open list
                        if (!openList.contains(map[currentX + x][currentY + y])) {
                            // add the square to the open list
                            openList.add(map[currentX + x][currentY + y]);
                            // make the current square the parent of this square
                            map[currentX + x][currentY + y].setParent(map[currentX][currentY]);
                        }
                        // if the square is already on the open list
                        if (openList.contains(map[currentX + x][currentY + y])) {

                            // check to see if the heuristic value is better
                            if (map[currentX + x][currentY + y].getHVal() < map[currentX][currentY].getHVal()) {
                                // make the current square the parent of this square
                                map[currentX + x][currentY + y].setParent(map[currentX][currentY]);
                            }
                        }

                    }
                }
            }
        }

//====== Debugging stuff START ==========================================
//        System.out.println("foo");
//
//        for (Square aClosedList : closedList) {
//            System.out.print(aClosedList.getxPos() + ", ");
//            System.out.println(aClosedList.getyPos());
//        }
//
//        totalGas = getTotalGas(closedList);
//        System.out.println("Gas from traceback (proper): " + totalGas);
//====== Debugging stuff END ============================================

        shortestPath = getShortestPath(closedList);

        return getTotalGas(shortestPath);
    }

}
