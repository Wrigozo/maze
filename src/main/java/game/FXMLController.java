package game;


import java.lang.reflect.Field;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import database.gamer.Gamer;
import database.jpa.DBTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;


public class FXMLController {


    private Gamer gamer = new Gamer();
    private String s;
    @FXML
    private Button[][] btns = new Button[6][6];
    static DBTools valami = new DBTools();
    private boolean win;
    private static Logger logger = LoggerFactory.getLogger(FXMLController.class);
    @FXML
    State state;

    @FXML
    private AnchorPane start;
    @FXML
    private TextField name;
    @FXML
    private AnchorPane game;
    @FXML
    private Circle player;
    @FXML
    private Circle enemy;
    @FXML
    private GridPane buttons;
    @FXML
    private AnchorPane RankList;
    @FXML
    private AnchorPane WIN;
    @FXML
    private Label winLabel;
    @FXML
    private TableColumn ids;
    @FXML
    private TableColumn names;
    @FXML
    private TableColumn scores;
    @FXML
    private TableView scoreboard;

    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private Button btn02;
    @FXML
    private Button btn03;
    @FXML
    private Button btn04;
    @FXML
    private Button btn05;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14;
    @FXML
    private Button btn15;
    @FXML
    private Button btn20;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;
    @FXML
    private Button btn24;
    @FXML
    private Button btn25;
    @FXML
    private Button btn30;
    @FXML
    private Button btn31;
    @FXML
    private Button btn32;
    @FXML
    private Button btn33;
    @FXML
    private Button btn34;
    @FXML
    private Button btn35;
    @FXML
    private Button btn40;
    @FXML
    private Button btn41;
    @FXML
    private Button btn42;
    @FXML
    private Button btn43;
    @FXML
    private Button btn44;
    @FXML
    private Button btn45;
    @FXML
    private Button btn50;
    @FXML
    private Button btn51;
    @FXML
    private Button btn52;
    @FXML
    private Button btn53;
    @FXML
    private Button btn54;
    @FXML
    private Button btn55;


    @FXML
    private void OKButtonAction(ActionEvent event) {
        start.setVisible(false);

        gamer.setName(name.getText());
        valami.addGamer(gamer);
        valami.updateGamer(gamer);
        logger.info("You clicked OK succesfully!");
        game.setVisible(true);
    }


    @FXML
    private void mazeButtonAction(ActionEvent event) {
        if (true) {//state.yourTurn) {
            Button btn = (Button) event.getSource();
            int toX = Character.getNumericValue(btn.getId().charAt(3));
            int toY = Character.getNumericValue(btn.getId().charAt(4));
            state = new State(buttons.getRowIndex(player), buttons.getColumnIndex(player));
            state.setEnableButtons(buttons.getRowIndex(player), buttons.getColumnIndex(player));


            if ( state.getEnableButtons().contains(btn.getId()) ) {
                setCoordinatesCircle(player, toX, toY);
                int[] enemyCoordinate = state.enemylepes(buttons.getRowIndex(enemy), buttons.getColumnIndex(enemy), buttons.getRowIndex(player), buttons.getColumnIndex(player));
                setCoordinatesCircle(enemy, enemyCoordinate[0], enemyCoordinate[1]);
                logger.info("You clicked btn" + toX, toY + "!");
            } else
                logger.warn("ide nem léphetsz");
        } else
            logger.error("Nem te következel");
        if (buttons.getRowIndex(player) == buttons.getRowIndex(enemy) && buttons.getColumnIndex(player) == buttons.getColumnIndex(enemy)) {
            win = false;
            endGameView();
        }
        if (buttons.getRowIndex(player) == 0 && buttons.getColumnIndex(player) == 4) {
            win = true;
            endGameView();
        }

    }

    @FXML
    private void endGameView() {
        game.setVisible(false);
        if (win) {
            winLabel.setText("Nyertél!");
        } else
            winLabel.setText("Vesztettél!");
        WIN.setVisible(true);
    }

    @FXML
    private void rankListButtonAction(ActionEvent event) {
        WIN.setVisible(false);

        ObservableList<Gamer> board = FXCollections.observableList(valami.getScoreboard());

        ids.setCellValueFactory(new PropertyValueFactory<>("id"));
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        scores.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreboard.setItems(board);
        RankList.setVisible(true);

    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        RankList.setVisible(false);
        WIN.setVisible(true);
    }

    @FXML
    private void generateButtons() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                try {
                    Field f = this.getClass().getDeclaredField("btn" + Integer.toString(i) + Integer.toString(j));
                    btns[i][j] = (Button) f.get(this);
                    logger.info("btn" + i+ j);

                } catch (NoSuchFieldException ex) {
                    logger.error("no such field");

                } catch (IllegalAccessException ex2) {
                    logger.error("illegal access");

                }

            }
        }
    }

    @FXML
    private void setCoordinatesCircle(Circle ball, int posX, int posY) {
        buttons.setRowIndex(ball, posX);
        buttons.setColumnIndex(ball, posY);

    }

    @FXML
    private void initialize() {
        // TODO
        start.setVisible(true);
        setCoordinatesCircle(player, 0, 0);
        setCoordinatesCircle(enemy, 2, 4);
        generateButtons();
        game.setVisible(false);
        WIN.setVisible(false);
        RankList.setVisible(false);
        logger.info("The game has been initialized successfully!");
    }
}
