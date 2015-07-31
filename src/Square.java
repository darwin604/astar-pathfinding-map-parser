import java.util.Comparator;

public class Square {

    private int xPos;
    private int yPos;
    private int blockOrGasVal;

    private Square parent = null;

    private int hVal = -1;

    // Constructor with the three unchanging properties of the square
    public Square (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // Accessors left in for debugging (safe to remove most)
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

    // Set and retrieve parent square info for pathfinding (some left in for debugging)

    public Square getParent() {
        return parent;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    // The number value of the square

    public void setBlockOrGasVal(int blockOrGasVal) {
        this.blockOrGasVal = blockOrGasVal;
    }

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

    // Add comparator to help with sorting the list of squares by their hval
    public static Comparator<Square> COMPARE_BY_HVAL = new Comparator<Square>() {
        public int compare(Square o1, Square o2) {
            return Integer.compare(o1.getHVal(), o2.getHVal());
        }
    };
}
