
import java.util.ArrayList;


/**
 * Created by James Ferris on 4/12/2016.
 */
public class Pawn extends Piece {

    //used to check if the pawn can move two spaces
    private boolean hasMoved;

    /**
     * Constructor, makes call to super class, Piece.
     *
     * @param color - the color of the pawn
     * @param row - the row of the pawn
     * @param col - the column of the pawn
     */
    public Pawn(String color, int row, int col){
        super(color, row, col);
        this.hasMoved = false;
    }

    /**
     * Method to determine if the pawn has moved
     *
     * @return - true if it has, false otherwise
     */
    public boolean hasMoved(){
        return this.hasMoved;
    }

    /**
     * Method to change hasMoved to true once a move was made
     */
    public void changeHasMoved(){
        this.hasMoved = true;
    }


    /**
     * Specification in super class
     */
    @Override
    public ArrayList<Move> getValidMoves(){
        ArrayList<Move> moves = Move.getValidMovesPawn(getCoordinates());

        for (Move m: Move.getValidAttacksPawn(getCoordinates())){
            moves.add(m);
        }

        return moves;
    }


    @Override
    public String toString(){
        return PAWN;
    }
}
