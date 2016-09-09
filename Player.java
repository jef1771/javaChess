import java.util.ArrayList;
import java.util.Random;

/**
 * Created by James Ferris on 4/13/16.
 */
public class Player {

    //list of all the possible moves for a player
    private ArrayList<Move> moves;

    //list of all the pieces held by the player
    private ArrayList<Piece> pieces;

    //the color of the player
    private String color;

    //signifies whether or not this player is a computer player.
    private boolean isComputer;

    private boolean hasWon = false;

    /**
     * Constructor for a player
     *
     * @param color - the color of the player
     */
    public Player(String color){
        this.moves = new ArrayList<>();
        this.color = color;
        this.pieces = new ArrayList<>(16);
        this.isComputer = false;

        if (color.equals("w")){
            for (int r = 0; r < 2; r++){
                for (int c = 0; c < Chess.dimmension; c++){
                    Piece p = Chess.get(new Coordinate(r, c));
                    this.pieces.add(p);
                }
            }

            for (Piece p: pieces){
                if (p instanceof King){
                    continue;
                }
                for (Move m: p.getValidMoves()){
                    moves.add(m);
                }
            }
        }

        if (color.equals("b")){
            for (int r = 6; r < Chess.dimmension; r++){
                for (int c = 0; c < Chess.dimmension; c++){
                    Piece p = Chess.get(new Coordinate(r, c));
                    this.pieces.add(p);
                }
            }

            for (Piece p: pieces){
                if (p instanceof King){
                    continue;
                }
                for (Move m: p.getValidMoves()){
                    moves.add(m);
                }
            }
        }

        if (color.equals("w")){
            this.isComputer = true;
        }

    }


    public void changeHasWon(){
        this.hasWon = true;
    }

    /**
     * Getter method for the moves the player can currently make
     *
     * @return - the list of moves
     */
    public ArrayList<Move> getMoves(){
        return this.moves;
    }

    /**
     * Method called after a move is made, updates the pieces held and valid moves
     */
    public void update(){

        this.moves.clear();
        this.pieces.clear();

        for (int r = 0; r < Chess.dimmension; r++){
            for (int c = 0; c < Chess.dimmension; c++){
                if (Chess.get(new Coordinate(r, c)).getColor().equals(this.color)){
                    Piece p = Chess.get(new Coordinate(r, c));
                    this.pieces.add(p);
                }
            }
        }

        for (Piece p: pieces){
            for (Move m: p.getValidMoves()){
                moves.add(m);
            }
        }
    }

    public boolean hasKing(){
        for (Piece p: this.pieces){
            if (p instanceof King){
                return true;
            }
        }

        return false;
    }

    public void computerPlayerMove(){
        Random rand = new Random();
        int index = rand.nextInt(this.moves.size() - 1);
        this.moves.get(index).move();
    }
}
