import javafx.beans.InvalidationListener;
import java.util.Observable;

/**
 * Created by James Ferris on 4/12/2016.
 */
public class Chess extends Observable{

    public static Player playerWhite;
    public static Player playerBlack;
    private static Piece[][] board;
    public static final int dimmension = 8;
    private String winner = "";

    public Chess(){

        board = new Piece[dimmension][dimmension];

        board[0][0] = new Rook("w", 0, 0);
        board[0][7] = new Rook("w", 0, 7);;
        board[7][0] = new Rook("b", 7, 0);;
        board[7][7] = new Rook("b", 7, 7);;

        board[0][1] = new Knight("w", 0, 1);
        board[0][6] = new Knight("w", 0, 6);
        board[7][1] = new Knight("b", 7, 1);
        board[7][6] = new Knight("b", 7, 6);

        board[0][2] = new Bishop("w", 0, 2);
        board[0][5] = new Bishop("w", 0, 5);
        board[7][2] = new Bishop("b", 7, 2);
        board[7][5] = new Bishop("b", 7, 5);

        board[0][3] = new Queen("w", 0, 3);
        board[7][3] = new Queen("b", 7, 3);

        board[0][4] = new King("w", 0, 4);
        board[7][4] = new King("b", 7, 4);

        for (int pos = 0; pos < dimmension; pos++){
            board[1][pos] = new Pawn("w", 1, pos);
            board[6][pos] = new Pawn("b", 6, pos);
        }


        for (int r = 2; r < 6; r++){
            for (int c = 0; c < dimmension; c++){
                board[r][c] = new Empty(r, c);
            }
        }

        playerWhite = new Player("w");
        playerBlack = new Player("b");
    }

    public static Piece get(Coordinate c){
        return board[c.getRow()][c.getCol()];
    }

    public static void set(int row, int col, Piece piece){
        board[row][col] = piece;
    }


    public static void update(){
        playerWhite.update();
        playerBlack.update();

    }

    @Override
    public String toString(){
        String topLine = " ---------------\n|";
        String bottomLine = " ---------------\n";
        String ret = "";



        ret += topLine;
        for (int r = 0; r < dimmension; r++){
            if (r != 0){
                ret += "|";
            }
            for (int c = 0; c < dimmension; c++){
                if (c == dimmension-1){
                    ret += board[r][c].toString();
                    ret += "|";
                    ret += "\n";
                }
                else{
                    ret += board[r][c].toString();
                    ret += " ";
                }

            }
        }
        ret += bottomLine;

        return ret;
    }

    public String getWinner(){
        return this.winner;
    }

    /**
     * A utility method that indicates the model has changed and
     * notifies observers
     */
    public void announceChange() {
        setChanged();
        notifyObservers();
    }

    public void play(Coordinate first, Coordinate second){
        boolean canMove = false;

        for (Move m: playerBlack.getMoves()){
            if (m.getCurrentSpot().equals(first) && m.getDestination().equals(second)){
                canMove = true;
                break;
            }
        }
        if (canMove){
            Move attempt = new Move(first, second);
            attempt.move();

            if (!playerWhite.hasKing()){
                this.winner = "Player Black has Won!";
                announceChange();
                return;
            }

            //this is the method to allow the computer player to make a move. Currently just a random move.
            playerWhite.computerPlayerMove();

            if (!playerBlack.hasKing()){
                this.winner = "Player White has Won!";
                announceChange();
                return;
            }
        }


        announceChange();
    }

}
