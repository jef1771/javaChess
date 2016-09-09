import java.util.ArrayList;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Empty extends Piece {

    /**
     * Constructor for a empty, call made to super
     *
     * @param row - the row of the empty
     * @param col - the column of the empty
     */
    public Empty(int row, int col){
        super(row, col);
    }

    /**
     * Specification in super class, should never be called since empty piece, however
     * will return an empty list in case it is called
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> ret = new ArrayList<>();
        return ret;
    }


    @Override
    public String toString(){
        return EMPTY;
    }
}
