import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Rook extends Piece {

    /**
     * Constructor for a Rook, call made to super
     *
     * @param color - the color of the rook
     * @param row - the row of the rook
     * @param col - the column of the rook
     */
    public Rook(String color, int row, int col){
        super(color, row, col);
    }

    /**
     * Specification in super class
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> ret = Move.getValidMovesRook(getCoordinates());
        return ret;
    }


    @Override
    public String toString(){
        return ROOK;
    }
}
