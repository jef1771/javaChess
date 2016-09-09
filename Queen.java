import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Queen extends Piece {

    /**
     * Constructor for a Queen, call to super
     *
     * @param color - the color of the queen
     * @param row - the row of the queen
     * @param col - the column of the queen
     */
    public Queen(String color, int row, int col){
        super(color, row, col);
    }


    /**
     * Specification in super class
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> ret = new ArrayList<>();

        for (Move m: Move.getValidMovesBishop(getCoordinates())){
            ret.add(m);
        }

        for (Move m: Move.getValidMovesRook(getCoordinates())){
            ret.add(m);
        }

        return ret;
    }


    @Override
    public String toString(){
        return QUEEN;
    }
}
