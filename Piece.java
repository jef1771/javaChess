
import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public abstract class Piece {
    public final static String EMPTY = ".";
    public final static String ROOK = "R";
    public final static String PAWN = "P";
    public final static String BISHOP = "B";
    public final static String KNIGHT = "N";
    public final static String KING = "K";
    public final static String QUEEN = "Q";


    private boolean isEmpty;
    private String color;
    private Coordinate coordinates;
    private boolean canTakeOther;

    /**
     * Constructor for a piece
     *
     * @param color - the color of the piece
     * @param row - the row of the piece
     * @param col - te column of the piece
     */
    public Piece(String color, int row, int col){
        this.color = color;
        this.canTakeOther = false;
        this.coordinates = new Coordinate(row, col);
        this.isEmpty = false;
    }

    /**
     * Constructor for an EMPTY piece object
     *
     * @param row - the row of the EMPTY piece
     * @param col - the column of the EMPTY piece
     */
    public Piece(int row, int col){
        this.isEmpty = true;
        this.coordinates = new Coordinate(row, col);
        this.color = "superLongSoThereIsNoConfusion";
    }

    /**
     * Getter method for coordinates
     *
     * @return - the current coordinates of the piece
     */
    public Coordinate getCoordinates(){
        return this.coordinates;
    }

    /**
     * Setter method for coordinates
     *
     * @param c - the new coordinate
     */
    public void setCoordinates(Coordinate c){
        this.coordinates = c;
    }

    /**
     * Getter method for the color of the piece
     *
     * @return the String of the color
     */
    public String getColor(){
        return this.color;
    }

    /**
     * Determine if the given piece is an EMPTY piece
     *
     * @return - true if empty, false otherwise
     */
    public boolean isEmpty(){
        return this.isEmpty;
    }

    /**
     * Determine if this piece can take another piece on the opponents team
     *
     * TODO - should implement this for choosing best move???
     *
     * @return - true if the piece can take another, false otherwise
     */
    public boolean canTakeOther(){
        return this.canTakeOther;
    }

    /**
     * Abstract method that must be implemented in subclasses to get the list of moves for a piece
     *
     * @return - the list of valid moves for a given piece
     */
    public abstract ArrayList<Move> getValidMoves();
}
