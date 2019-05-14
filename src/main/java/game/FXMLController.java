package game;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import database.gamer.Gamer;
import database.jpa.DBTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;


public class FXMLController  {
    @FXML
    private AnchorPane start;
    @FXML
    private AnchorPane game;
    @FXML
    private AnchorPane RankList;
    @FXML
    private AnchorPane WIN;
    @FXML
    private TableColumn ids;
    @FXML
    private TableColumn names;
    @FXML
    private TableColumn scores;
    @FXML
    private TableView scoreboard;
    @FXML
    private GridPane buttons;
    @FXML
    private Label label;
    @FXML
    private Circle player;
    @FXML
    private Circle enemy;

    @FXML
    private Button Ranglista;
    @FXML
    private Button exit;
    @FXML
    private TextField name;
    @FXML
    private Button newgame;
    @FXML
    private Label winLabel;
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

    private Gamer gamer=new Gamer();
    private String s;
    private Button [][] btns=new Button[6][6];
    static DBTools valami= new DBTools();
    private boolean win;
    @FXML
    private void B_startClick(ActionEvent event) {
        /*gamepane.setVisible(true);
        start.setVisible(false);
        play.begin();*/
    }

    List<String> operators=new ArrayList<>();


    @FXML
    private void OKButtonAction(ActionEvent event) {
        start.setVisible(false);
        gamer.setName(name.getText());
        valami.addGamer(gamer);
        valami.updateGamer(gamer);




        System.out.println("You clicked OK!");
        game.setVisible(true);

    }
    @FXML
    private void getUser(){
        s=name.getText();
    }

    State state;



    @FXML
    private void mazeButtonAction(ActionEvent event) {
        if (true){//state.yourTurn) {
            Button btn = (Button) event.getSource();
            int toX = Character.getNumericValue(btn.getId().charAt(3));
            int toY = Character.getNumericValue(btn.getId().charAt(4));
            state=new State(buttons.getRowIndex(player),buttons.getColumnIndex(player));
            state.setEnableButtons(buttons.getRowIndex(player), buttons.getColumnIndex(player));
            operators=state.getEnableButtons();
            System.out.println(state.ishorizontal);
            System.out.println(state.isVertical);
            //kiíratja a használható operátorokat
            for (int i = 0; i < operators.size(); i++) {
                System.out.println(operators.get(i));
            }

            if (operators.contains(btn.getId())) {

                buttons.setRowIndex(player, toX);
                buttons.setColumnIndex(player, toY);
                int []coordinate=state.enemylepes(buttons.getRowIndex(enemy),  buttons.getColumnIndex(enemy),buttons.getRowIndex(player), buttons.getColumnIndex(player));
                buttons.setRowIndex(enemy, coordinate[0]);
                buttons.setColumnIndex(enemy, coordinate[1]);
                //System.out.println("You clicked btn" + toX + toY + "!");
                //System.out.println(player.getLayoutX() + " " + player.getLayoutY());
                /*
                state.yourTurn=false;
                state.enemy(toX, toY,  buttons.getRowIndex(enemy),  buttons.getColumnIndex(enemy));
                state.yourTurn=true;*/
            } else
                System.out.println("ide nem léphetsz");
        }
        else
            System.out.println("Nem te következel");
        if(buttons.getRowIndex(player)==buttons.getRowIndex(enemy)&& buttons.getColumnIndex(player)==buttons.getColumnIndex(enemy)){
            win=false;
            endgameView();
        }
        if(buttons.getRowIndex(player)==0&& buttons.getColumnIndex(player)==4) {
            win=true;
            endgameView();
        }

    }
    @FXML
    private void endgameView() {
        game.setVisible(false);
        if(win){
            winLabel.setText("Nyertél!");
        }
        else
            winLabel.setText("Vesztettél!");
        WIN.setVisible(true);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void RanglistaButtonAction(ActionEvent event) {
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
    public void initialize() {
        // TODO
        start.setVisible(true);
        buttons.setRowIndex(player,0);
        buttons.setColumnIndex(player,0);
        buttons.setRowIndex(enemy,2);
        buttons.setColumnIndex(enemy,4);
        game.setVisible(false);
        WIN.setVisible(false);
        RankList.setVisible(false);
        for(int i=0; i<6; i++){
            for (int j=0; j<6; j++){
                try
                {
                    Field f = this.getClass().getDeclaredField("btn" + Integer.toString(i) + Integer.toString(j));
                    btns[i][j]=(Button)f.get(this);

                }
                catch (NoSuchFieldException ex)
                {
                    System.out.println("no such field");

                }
                catch (IllegalAccessException ex2)
                {
                    System.out.println("illegal access");
                }

            }
        }
    }    
}
