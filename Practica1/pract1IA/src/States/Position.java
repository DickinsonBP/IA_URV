package States;

public class Position {

    private int row,column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean cmp(Position position) {
        return ((this.row == position.row) && (this.column == position.column));
    }
}
