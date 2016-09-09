
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * Created by James Ferris
 */
public class ChessGUI extends Application implements Observer {

    private Stage stage;
    private Chess model;
    private Button firstPress;
    private Stage pawnStage;


    private String selectedPieceToChangeTo;
    private Label label;

    @Override
    public void init(){
        // the init method is run before start.  the file name is extracted
        // here and then the model is created.
        this.firstPress = null;
        this.model = new Chess();
        this.model.addObserver(this);

    }

    /**
     * A private utility function for setting the background of a button to
     * an image in the resources subdirectory.
     *
     * @param button the button control
     * @param bgImgName the name of the image file
     */
    private void setButtonBackground(Button button, String bgImgName) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image( getClass().getResource("images/" + bgImgName).toExternalForm()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        button.setBackground(background);
    }

    private BorderPane chooseAnotherPiece(String color, Coordinate first, Coordinate second){
        BorderPane bp = new BorderPane();

        Label label = new Label("Choose a chess piece to switch your pawn to");
        label.setPadding(new Insets(10));

        bp.setTop(label);

        FlowPane flow = new FlowPane();

        for (int i = 0; i < 4; i++){
            Button b = new Button();
            switch (i){
                case 0:
                    Image knight = new Image(getClass().getResourceAsStream("images/" + color + "Knight.png"));
                    ImageView knightIcon = new ImageView(knight);
                    b.setGraphic(knightIcon);
                    b.setOnAction((event) -> {
                        selectedPieceToChangeTo = "k";
                    });
                    flow.getChildren().add(b);
                    break;
                case 1:
                    Image queen = new Image(getClass().getResourceAsStream("images/" + color + "Queen.png"));
                    ImageView queenIcon = new ImageView(queen);
                    b.setGraphic(queenIcon);
                    b.setOnAction((event) -> {
                        selectedPieceToChangeTo = "q";
                    });
                    flow.getChildren().add(b);
                    break;
                case 2:
                    Image rook = new Image(getClass().getResourceAsStream("images/" + color + "Rook.png"));
                    ImageView rookIcon = new ImageView(rook);
                    b.setGraphic(rookIcon);
                    b.setOnAction((event) -> {
                        selectedPieceToChangeTo = "r";
                    });
                    flow.getChildren().add(b);
                    break;
                case 3:
                    Image bishop = new Image(getClass().getResourceAsStream("images/" + color + "Bishop.png"));
                    ImageView bishopIcon = new ImageView(bishop);
                    b.setGraphic(bishopIcon);
                    b.setOnAction((event) -> {
                        selectedPieceToChangeTo = "b";
                    });
                    flow.getChildren().add(b);
                    break;
            }
        }

        flow.setPadding(new Insets(10));

        Button accept = new Button("Make Change");
        accept.setOnAction((event) ->{
            if (selectedPieceToChangeTo == null){
                label.setText("You Must Select a New Piece");
            }
            else{
                Move mueve = new Move(first, second);
                String piece = selectedPieceToChangeTo;
                selectedPieceToChangeTo = null;
                pawnStage.close();
                if (firstPress != null){
                    firstPress.setEffect(null);
                    firstPress = null;
                }
                mueve.createNewPiece(color, piece);
                model.announceChange();
            }
        });

        bp.setCenter(flow);

        accept.setPadding(new Insets(10));

        bp.setBottom(accept);
        return bp;

    }

    /**
     * initialize the buttons for the board
     */
    private GridPane makeGrid() {
        GridPane grid = new GridPane();

        boolean black = false;
        for (int r = 0; r < Chess.dimmension; r++){
            black = !black;
            for (int c = 0; c < Chess.dimmension; c++){

                Button b = new Button();
                b.setMinSize(60, 60);
                b.setMaxSize(60, 60);

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (firstPress == null){
                            Button b = (Button)event.getSource();
                            firstPress = b;
                            b.setEffect(new InnerShadow());
                        }
                        else{
                            Button b = (Button)event.getSource();
                            Coordinate first = new Coordinate(grid.getRowIndex(firstPress), grid.getColumnIndex(firstPress));
                            Coordinate second = new Coordinate(grid.getRowIndex(b), grid.getColumnIndex(b));

                            if (Chess.get(first).getColor().equals("w") && second.getRow() == 7 && Chess.get(first) instanceof Pawn){
                                pawnStage = new Stage();
                                pawnStage.setScene(new Scene(chooseAnotherPiece("w", first, second)));
                                pawnStage.show();
                                return;
                            }

                            if (Chess.get(first).getColor().equals("b") && second.getRow() == 0 && Chess.get(first) instanceof Pawn){
                                pawnStage = new Stage();
                                pawnStage.setScene(new Scene(chooseAnotherPiece("b", first, second)));
                                pawnStage.show();
                                return;
                            }


                            model.play(first, second);
                            firstPress.setEffect(null);
                            firstPress = null;
                        }

                    }
                });

                if (black){
                    setButtonBackground(b, "blackSquare.png");
                    black = !black;
                }

                else{
                    setButtonBackground(b, "whiteSquare.png");
                    black = !black;
                }


                Coordinate cord = new Coordinate(r, c);
                String str = Chess.get(cord).toString();
                String color = Chess.get(cord).getColor();

                switch (str){

                    case Piece.PAWN:
                        Image pawn = new Image(getClass().getResourceAsStream("images/" + color + "Pawn.png"));
                        ImageView pawnIcon = new ImageView(pawn);
                        b.setGraphic(pawnIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.BISHOP:
                        Image bishop = new Image(getClass().getResourceAsStream("images/" + color + "Bishop.png"));
                        ImageView bishopIcon = new ImageView(bishop);
                        b.setGraphic(bishopIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.KING:
                        Image king = new Image(getClass().getResourceAsStream("images/" + color + "King.png"));
                        ImageView kingIcon = new ImageView(king);
                        b.setGraphic(kingIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.KNIGHT:
                        Image knight = new Image(getClass().getResourceAsStream("images/" + color + "Knight.png"));
                        ImageView knightIcon = new ImageView(knight);
                        b.setGraphic(knightIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.QUEEN:
                        Image queen = new Image(getClass().getResourceAsStream("images/" + color + "Queen.png"));
                        ImageView queenIcon = new ImageView(queen);
                        b.setGraphic(queenIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.ROOK:
                        Image rook = new Image(getClass().getResourceAsStream("images/" + color + "Rook.png"));
                        ImageView rookIcon = new ImageView(rook);
                        b.setGraphic(rookIcon);
                        grid.add(b, c, r);
                        break;
                    case Piece.EMPTY:
                        grid.add(b, c, r);
                        break;

                }
            }
        }

        grid.setVgap(0);
        grid.setHgap(0);

        return grid;
    }


    public Scene makeScene(){
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(makeGrid());

        Scene scene = new Scene(borderPane);

        return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        this.label = new Label("Make a move");



        primaryStage.setScene(makeScene());
        primaryStage.setTitle("Chess");
        primaryStage.show();
    }

    private Scene weHaveAWinner(){

        BorderPane bp = new BorderPane();
        Label label = new Label(this.model.getWinner());
        label.setPadding(new Insets(10));

        bp.setTop(label);

        return new Scene(bp);
    }

    @Override
    public void update(Observable o, Object arg){

        if (this.model.getWinner().length() > 0){
            this.pawnStage.setScene(weHaveAWinner());
            this.pawnStage.show();
        }

        this.stage.setScene(makeScene());



    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
