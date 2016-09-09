/**
 * Class to represent a coordinate on a grid
 *
 * Created by James Ferris on 4/4/16.
 */
public class Coordinate {

    //private variables to hold positions
    private int row;
    private int col;

    /**
     * Constructor for a coordinate
     * @param r - the row
     * @param c - the column
     */
    public Coordinate(int r, int c){
        this.row = r;
        this.col = c;
    }

    /**
     * Getter method to get the row
     * @return - the row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Getter method to get the column
     * @return - the column
     */
    public int getCol(){
        return this.col;
    }

    /**
     * Method to increment the row
     */
    public void incrementRow(){
        this.row += 1;
    }

    /**
     * Method to increment the column
     */
    public void incrementCol(){
        this.col += 1;
    }

    /**
     * reset the column to 0. simulates the wrapping on a grid
     */
    public void wrapCol(){
        this.col = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (row != that.getRow()) return false;
        return col == that.getCol();

    }

    public String toString(){
        return "row: " + this.row + ", col: " + this.col;
    }
}
