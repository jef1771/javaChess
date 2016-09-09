import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Knight extends Piece {

    /**
     * Constructor for a King, call made to super
     *
     * @param color - the color of the King
     * @param row - the row of the King
     * @param col - the column of the King
     */
    public Knight(String color, int row, int col){
        super(color, row, col);
    }


    /**
     * Specification in super class
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> ret = Move.getValidKnightMoves(getCoordinates());
        return ret;
    }

    @Override
    public String toString(){
        return KNIGHT;
    }
}
