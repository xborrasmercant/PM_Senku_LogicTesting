public class Cell {
    private int colPos, rowPos, value;

    public Cell (int colPos, int rowPos, int value) {
        this.colPos = colPos;
        this.rowPos = rowPos;
        this.value = value;
    }

    // GETTERS and SETTERS
    public int getColPos() {
        return colPos;
    }
    public void setColPos(int colPos) {
        this.colPos = colPos;
    }
    public int getRowPos() {
        return rowPos;
    }
    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
