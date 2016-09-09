import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by James Ferris on 4/13/2016.
 */
public class Move {

    /** Helpful for calculating possible spots

     int topRow = (row - 1 < 0) ? row : (row - 1);
     int leftCol = (col - 1 < 0) ? col : (col - 1);
     int bottomRow = (row + 1 > numRows - 1) ? row : (row + 1);
     int rightCol = (col + 1 > numCols - 1) ? col : (col + 1);

     */

    private Coordinate currentSpot;
    private Coordinate destination;

    /**
     * Constructor for a move object
     *
     * @param current - the current location of the piece
     * @param destination - the destination location of the piece
     */
    public Move(Coordinate current, Coordinate destination){
        this.currentSpot = current;
        this.destination = destination;
    }

    /**
     * Method to move a piece on the board to a new location based on the coordinates saved in this instance
     */
    public void move(){

        if (Chess.get(currentSpot) instanceof Pawn){
            ((Pawn) Chess.get(currentSpot)).changeHasMoved();
        }

        Piece p = Chess.get(currentSpot);
        p.setCoordinates(destination);
        Chess.set(destination.getRow(), destination.getCol(), p);

        Chess.set(currentSpot.getRow(), currentSpot.getCol(), new Empty(currentSpot.getRow(), currentSpot.getCol()));
        Chess.update();
    }

    /**
     * Method to turn a pawn that has reached the end of the board into whatever new Piece the player chooses
     *
     * @param color - the color of the previous piece to be transferred to the new one
     */
    public void createNewPiece(String color, String piece){

        Chess.set(currentSpot.getRow(), currentSpot.getCol(), new Empty(currentSpot.getRow(), currentSpot.getCol()));

        switch (piece){
            case "r":
                Piece rook = new Rook(color, destination.getRow(), destination.getCol());
                Chess.set(destination.getRow(), destination.getCol(), rook);
                Chess.update();
                return;
            case "b":
                Piece bishop = new Bishop(color, destination.getRow(), destination.getCol());
                Chess.set(destination.getRow(), destination.getCol(), bishop);
                Chess.update();
                return;
            case "k":
                Piece knight = new Knight(color, destination.getRow(), destination.getCol());
                Chess.set(destination.getRow(), destination.getCol(), knight);
                Chess.update();
                return;
            case "q":
                Piece queen = new Queen(color, destination.getRow(), destination.getCol());
                Chess.set(destination.getRow(), destination.getCol(), queen);
                Chess.update();
                return;
        }
    }

    /**
     * generate all of the possible moves for a rook at it's current position
     * A Queen will also use this method
     *
     * @param cord - the coordinate of the current Rook
     * @return - A list of all of the valid moves it can make
     */
    public static ArrayList<Move> getValidMovesRook(Coordinate cord){
        ArrayList<Move> ret = new ArrayList<>();
        int row = cord.getRow();
        int col = cord.getCol();

        int topRow = (row - 1 < 0) ? row : (row - 1);
        int leftCol = (col - 1 < 0) ? col : (col - 1);
        int bottomRow = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
        int rightCol = (col + 1 > Chess.dimmension - 1) ? col : (col + 1);

        Coordinate c;

        //get all the spaces above the current location
        for (int r = topRow; r > -1; r--){
            c = new Coordinate(r, col);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
            }
        }

        //get all of the spaces below the current location
        for (int r = bottomRow; r < Chess.dimmension; r++){
            c = new Coordinate(r, col);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
            }
        }

        //get all of the spaces to the left of the current location
        for (int r = leftCol; r > -1; r--){
            c = new Coordinate(row, r);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
            }
        }

        //get all of the spaces to the right of the current location
        for (int r = rightCol; r < Chess.dimmension; r++){
            c = new Coordinate(row, r);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
            }
        }


        return ret;
    }

    /**
     * Generate all of the next possible moves for a Bishop at it's current position
     * A Queen will also use this method
     *
     * @param cord - the coordinates of the current bishop
     * @return - A list of all of the valid moves for the given bishop
     */
    public static ArrayList<Move> getValidMovesBishop(Coordinate cord){
        ArrayList<Move> ret = new ArrayList<>();

        int row = cord.getRow();
        int col = cord.getCol();

        int topRow = (row - 1 < 0) ? row : (row - 1);
        int leftCol = (col - 1 < 0) ? col : (col - 1);
        int bottomRow = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
        int rightCol = (col + 1 > Chess.dimmension - 1) ? col : (col + 1);

        Coordinate c;

        int r = topRow;
        int co = rightCol;
        while (r > -1 && co < Chess.dimmension){
            c = new Coordinate(r, co);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            else if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
                r -= 1;
                co += 1;
            }
        }

        r = topRow;
        co = leftCol;
        while (r > -1 && co > -1){
            c = new Coordinate(r, co);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            else if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
                r -= 1;
                co -= 1;
            }
        }

        r = bottomRow;
        co = rightCol;
        while (r < Chess.dimmension && co < Chess.dimmension){
            c = new Coordinate(r, co);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            else if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
                r += 1;
                co += 1;
            }
        }


        r = bottomRow;
        co = leftCol;
        while (r < Chess.dimmension && co > -1){
            c = new Coordinate(r, co);
            Piece p = Chess.get(c);
            if (!p.isEmpty() && p.getColor().equals(Chess.get(cord).getColor())){
                break;
            }
            else if (!p.isEmpty() && !p.getColor().equals(Chess.get(cord).getColor())){
                ret.add(new Move(cord, c));
                break;
            }
            else{
                ret.add(new Move(cord, c));
                r += 1;
                co -= 1;
            }
        }


        return ret;
    }

    /**
     * Generate all of the next moves for a given pawn
     *
     * @param cord - the coordinates for the given Pawn
     * @return - A list of all of the valid next possible moves
     */
    public static ArrayList<Move> getValidMovesPawn(Coordinate cord){
        int row = cord.getRow();
        int col = cord.getCol();

        ArrayList<Move> ret = new ArrayList<>();

        int nextPos, nextPosHaventMoved;

        if (Chess.get(cord).getColor().equals("w")){
            nextPos = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
            nextPosHaventMoved = (row + 2 > Chess.dimmension - 1) ? row : (row + 2);
            if (nextPos != row && Chess.get(new Coordinate(nextPos, col)).isEmpty()){
                ret.add(new Move(cord,new Coordinate(nextPos, col)));
            }
            if (nextPosHaventMoved != row && Chess.get(new Coordinate(nextPos, col)).isEmpty()){
                if (!((Pawn) Chess.get(cord)).hasMoved()){
                    ret.add(new Move(cord, new Coordinate(nextPosHaventMoved, col)));
                }
            }

            return ret;
        }

        else{
            nextPos = (row - 1 < 0) ? row : (row - 1);
            nextPosHaventMoved = (row - 2 < 0) ? row : (row - 2);
            if (nextPos != row && Chess.get(new Coordinate(nextPos, col)).isEmpty()){
                ret.add(new Move(cord,new Coordinate(nextPos, col)));
            }
            if (nextPosHaventMoved != row && Chess.get(new Coordinate(nextPos, col)).isEmpty()){
                if (!((Pawn) Chess.get(cord)).hasMoved()){
                    ret.add(new Move(cord, new Coordinate(nextPosHaventMoved, col)));
                }
            }

            return ret;
        }

    }

    /**
     * Generate a list of all the possible attacks for a given pawn
     *
     * @param cord - the coordinates of the given pawn
     * @return - A list containing the possible attack moves, or an empty list if no attacks available
     */
    public static ArrayList<Move> getValidAttacksPawn(Coordinate cord){
        ArrayList<Move> ret = new ArrayList<>();

        int row = cord.getRow();
        int col = cord.getCol();

        int topRow = (row - 1 < 0) ? row : (row - 1);
        int leftCol = (col - 1 < 0) ? col : (col - 1);
        int bottomRow = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
        int rightCol = (col + 1 > Chess.dimmension - 1) ? col : (col + 1);

        Coordinate TR = new Coordinate(topRow, rightCol);
        Coordinate TL = new Coordinate(topRow, leftCol);

        Coordinate BR = new Coordinate(bottomRow, rightCol);
        Coordinate BL = new Coordinate(bottomRow, leftCol);

        String color = Chess.get(cord).getColor();

        if (color.equals("w")){
            if (BR.getCol() != col && !Chess.get(BR).isEmpty() && !color.equals(Chess.get(BR).getColor())){
                ret.add(new Move(cord, BR));
            }
            if (BL.getCol() != col && !Chess.get(BL).isEmpty() && !color.equals(Chess.get(BL).getColor())){
                ret.add(new Move(cord, BL));
            }

            return ret;
        }

        else{
            if (TR.getCol() != col && !Chess.get(TR).isEmpty() && !color.equals(Chess.get(TR).getColor())){
                ret.add(new Move(cord, TR));
            }
            if (TL.getCol() != col && !Chess.get(TL).isEmpty() && !color.equals(Chess.get(TL).getColor())){
                ret.add(new Move(cord, TL));
            }

            return ret;
        }


    }

    public static ArrayList<Move> getValidKnightMoves(Coordinate cord){
        ArrayList<Move> ret = new ArrayList<>();

        int row = cord.getRow();
        int col = cord.getCol();

        int topRow2 = (row - 2 < 0) ? row : (row - 2);
        int leftCol2 = (col - 2 < 0) ? col : (col - 2);
        int bottomRow2 = (row + 2 > Chess.dimmension - 1) ? row : (row + 2);
        int rightCol2 = (col + 2 > Chess.dimmension - 1) ? col : (col + 2);
        int topRow1 = (row - 1 < 0) ? row : (row - 1);
        int leftCol1 = (col - 1 < 0) ? col : (col - 1);
        int bottomRow1 = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
        int rightCol1 = (col + 1 > Chess.dimmension - 1) ? col : (col + 1);

        String color = Chess.get(cord).getColor();
        Coordinate temp;

        if (topRow2 != row && rightCol1 != col){
            temp = new Coordinate(topRow2, rightCol1);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }

        }

        if (topRow1 != row && rightCol2 != col){
            temp = new Coordinate(topRow1, rightCol2);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        if (bottomRow1 != row && rightCol2 != col){
            temp = new Coordinate(bottomRow1, rightCol2);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        if (bottomRow2 != row && rightCol1 != col){
            temp = new Coordinate(bottomRow2, rightCol1);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }





        if (topRow2 != row && leftCol1 != col){
            temp = new Coordinate(topRow2, leftCol1);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        if (topRow1 != row && leftCol2 != col){
            temp = new Coordinate(topRow1, leftCol2);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        if (bottomRow1 != row && leftCol2 != col){
            temp = new Coordinate(bottomRow1, leftCol2);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        if (bottomRow2 != row && leftCol1 != col){
            temp = new Coordinate(bottomRow2, leftCol1);

            if (!Chess.get(temp).isEmpty()){
                if (!color.equals(Chess.get(temp).getColor())){
                    ret.add(new Move(cord, temp));
                }
            }
            else{
                ret.add(new Move(cord, temp));
            }
        }

        return ret;
    }

    public static ArrayList<Move> getValidKingMoves(Coordinate cord){
        ArrayList<Move> ret = new ArrayList<>();

        String color = Chess.get(cord).getColor();

        int row = cord.getRow();
        int col = cord.getCol();

        int topRow = (row - 1 < 0) ? row : (row - 1);
        int leftCol = (col - 1 < 0) ? col : (col - 1);
        int bottomRow = (row + 1 > Chess.dimmension - 1) ? row : (row + 1);
        int rightCol = (col + 1 > Chess.dimmension - 1) ? col : (col + 1);

        Coordinate temp;

        if (topRow != row){


            if (leftCol != col){
                temp = new Coordinate(topRow, leftCol);
                if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                    ret.add(new Move(cord, temp));
                }
            }

            if (rightCol != col){
                temp = new Coordinate(topRow, rightCol);
                if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                    ret.add(new Move(cord, temp));
                }

            }

            temp = new Coordinate(topRow, col);
            if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                ret.add(new Move(cord, temp));
            }

        }

        if (bottomRow != row){
            if (leftCol != col){
                temp = new Coordinate(bottomRow, leftCol);
                if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                    ret.add(new Move(cord, temp));
                }
            }

            if (rightCol != col){
                temp = new Coordinate(bottomRow, rightCol);
                if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                    ret.add(new Move(cord, temp));
                }
            }

            temp = new Coordinate(bottomRow, col);
            if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                ret.add(new Move(cord, temp));
            }

        }

        if (rightCol != col){
            temp = new Coordinate(row, rightCol);
            if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                ret.add(new Move(cord, temp));
            }
        }

        if (leftCol != col){
            temp = new Coordinate(row, leftCol);
            if (!Chess.get(temp).getColor().equals(color) || Chess.get(temp).isEmpty()){
                ret.add(new Move(cord, temp));
            }
        }

        ArrayList<Move> movesToRemove = new ArrayList<>();


        if (Chess.get(cord).getColor().equals("w")){
            for (Move m: Chess.playerBlack.getMoves()){
                for (Move move: ret){
                    if (move.destination.equals(m.destination)){
                        movesToRemove.add(move);
                    }
                }
            }

            for (Move m: movesToRemove){
                ret.remove(m);
            }
        }


        if (Chess.get(cord).getColor().equals("b")){
            for (Move m: Chess.playerWhite.getMoves()){
                for (Move move: ret){
                    if (move.destination.equals(m.destination)){
                        movesToRemove.add(move);
                    }
                }
            }

            for (Move m: movesToRemove){
                ret.remove(m);
            }
        }

        return ret;


    }

    public Coordinate getCurrentSpot(){
        return this.currentSpot;
    }

    public Coordinate getDestination(){
        return this.destination;
    }

    public String toString(){
        return "Start: " + this.currentSpot + ", end: " + this.destination;
    }

}
