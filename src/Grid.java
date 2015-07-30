import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grid {

    // we will create the map from squares
    private Square[][] map;

    private int startX;
    private int startY;

    private int endX;
    private int endY;

    private int currentX;
    private int currentY;

    private int gridWidth;
    private int gridHeight;

    //private Coord currentSquare;

    // The quickest path
    List<Integer> shortPath = new ArrayList<Integer>();


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

    public void createMapWithValues(int width, int height, int[][] value) {
        map = new Square[width][height];
        this.gridWidth = width;
        this.gridHeight = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Square(i, j, value[i][j]);
                map[i][j].setHVal(findAbsoluteDistance(i, j));
            }

        }
    }

    // for debug purposes
    public void printMapValues() {
        for (int i = 0; i < this.gridHeight; i++) {
            for (int j = 0; j < this.gridWidth; j++) {
                System.out.print("X:" + j + ",");
                System.out.print("Y:" + i + ",");
                System.out.print("H:" + map[j][i].getHVal() + " "); //j and i flipped for proper output furmat
                //System.out.print("Val:" + map[i][j].getBlockOrGasVal() + " ");
            }
            System.out.println();

        }
    }

    // Find the distance (in moves) from the specified square to target ignoring blockages
    // Using the Manhattan Distance since no diagonal moves are permitted
    public int findAbsoluteDistance(int currentX, int currentY) {

        int dX = Math.abs(this.endX - currentX);
        int dY = Math.abs(this.endY - currentY);
        return dX + dY;
    }

    public int getTotalGas(List<Square> path) {
        int gas = 0;
        for (int i = 0; i < path.size() ; i++) {
            gas = gas + path.get(i).getBlockOrGasVal();
        }
        return gas;
    }

//    public List<Square> getNeighbors(Square square) {
//
//        // list to store neighboring squares
//        List<Square> neighbors = new ArrayList<Square>();
//
//        // left
//        if (map[square.getxPos() - 1][square.getyPos()] != null && map[square.getxPos() - 1][square.getyPos()].getBlockOrGasVal() != -1 ) {
//
//        }
//
//        return neighbors;
//    }

    public void findShortestPath() {

        List<Square> openList = new ArrayList<Square>();
        List<Square> closedList = new ArrayList<Square>();

        //int totalGas = 0; //stores the total gas collected, let's start it at 0

        // set the current node to the start node
        this.currentX = this.startX;
        this.currentY = this.startY;

        // add start (current) position to open list
        openList.add(map[startX][startY]);

        // loop while there are still places to go (if it's empty and target doesn't go in closed list, mission impossible)
        while (!closedList.contains(map[this.endX][this.endY])) {

            // sort the open list by hval
            Collections.sort(openList, Square.COMPARE_BY_HVAL);

            // set current square to lowest hval in open list (sorted so will be at position 0)
            this.currentX = openList.get(0).getxPos();
            this.currentY = openList.get(0).getyPos();

            // move lowest hval node (current) to the closed list
            closedList.add(openList.get(0));
            openList.remove(0);

            // check all sides
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    // we don't want to check diagonals so skip those
                    if (x == y) {
                        continue;
                    }
                    if (this.currentX == 0 && x == -1) {
                        continue;
                    }
                    if (this.currentY == 0 && y == -1) {
                        continue;
                    }
                    if (this.currentX == this.gridWidth - 1 && x == 1) {
                        continue;
                    }
                    if (this.currentY == this.gridHeight - 1 && y == 1) {
                        continue;
                    }

                    // if the neighboring square square is walkable
                    if (map[this.currentX + x][this.currentY + y].getBlockOrGasVal() != -1 && !closedList.contains(map[this.currentX + x][this.currentY + y])) {

                        // if the square is not already in the open list
                        if (!openList.contains(map[this.currentX + x][this.currentY + y])) {
                            // add the square to the open list
                            openList.add(map[this.currentX + x][this.currentY + y]);
                            // make the current square the parent of this square
                            map[this.currentX + x][this.currentY + y].setParent(map[this.currentX][this.currentY]);
                        }
                        // if the square is already on the open list
                        if (openList.contains(map[this.currentX + x][this.currentY + y])) {

                            // check to see if the heuristic value is better
                            if (map[this.currentX + x][this.currentY + y].getHVal() < map[this.currentX][this.currentY].getHVal()) {
                                // make the current square the parent of this square
                                map[this.currentX + x][this.currentY + y].setParent(map[this.currentX][this.currentY]);
                            }
                        }

                    }
                }
            }
        }

        //debuging stuff
        System.out.println("foo");
        for (Square aClosedList : closedList) {
            System.out.print(aClosedList.getxPos() + ", ");
            System.out.println(aClosedList.getyPos());
        }
        System.out.println("Gas: " + getTotalGas(closedList));
    }

}
