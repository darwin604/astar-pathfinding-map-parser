import java.util.Comparator;

public class Square {

    private int xPos;
    private int yPos;
    private int blockOrGasVal;

    // Coordinates of parent square for pathfinding trace-back
    // Initialized to -1 for debugging purposes
    private int parentX = -1;
    private int parentY = -1;

    private Square parent = null;

    private int hVal = -1;

    // Constructor with the three unchanging properties of the square
    public Square (int xPos, int yPos, int blockOrGasVal) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.blockOrGasVal = blockOrGasVal;
    }

    // The following four getters/setters are redundant but left in for debugging.

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    // Set and retrieve parent square info for pathfinding

    public Square getParent() {
        return parent;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    public void setParentX(int parentX) {
        this.parentX = parentX;
    }

    public int getParentX() {
        return parentX;
    }

    public void setParentY(int parentY) {
        this.parentY = parentY;
    }

    public int getParentY() {
        return parentY;
    }

    // The number value of the square

    public int getBlockOrGasVal() {
        return blockOrGasVal;
    }

    // Heuristic values for square
    public int getHVal() {
        return hVal;
    }

    public void setHVal(int hVal) {
        this.hVal = hVal;
    }

    public static Comparator<Square> COMPARE_BY_HVAL = new Comparator<Square>() {
        //@Override
        public int compare(Square o1, Square o2) {
            return Integer.compare(o1.getHVal(), o2.getHVal());
        }
    };
}
