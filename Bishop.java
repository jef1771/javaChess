import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Bishop extends Piece {

    /**
     * Constructor for a Bishop, call made to super
     *
     * @param color - the color of the bishop
     * @param row - the row of the bishop
     * @param col - the column of the bishop
     */
    public Bishop(String color, int row, int col){
        super(color, row, col);
    }

    /**
     * Specification in super class
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> ret = Move.getValidMovesBishop(getCoordinates());
        return ret;
    }


    @Override
    public String toString(){
        return BISHOP;
    }
}
