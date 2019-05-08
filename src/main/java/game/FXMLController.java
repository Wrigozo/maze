package game;

import java.lang.reflect.Field;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.layout.AnchorPane;

public class FXMLController {
    @FXML
    private AnchorPane start;
    @FXML
    private AnchorPane game;

    @FXML
    private GridPane buttons;
    @FXML
    private Label label;
    @FXML
    private Circle player;
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

    private Button [][] btns=new Button[6][6];
    @FXML
    private void B_startClick(ActionEvent event) {
        /*gamepane.setVisible(true);
        start.setVisible(false);
        play.begin();*/
    }
    @FXML
    private void OKButtonAction(ActionEvent event) {
        start.setVisible(false);

        System.out.println("You clicked OK!");
        game.setVisible(true);
    }
    @FXML
    private void mazeButtonAction(ActionEvent event) {
        Button btn=(Button)event.getSource();

        int x=Character.getNumericValue(btn.getId().charAt(3));
        int y=Character.getNumericValue(btn.getId().charAt(4));
        buttons.setRowIndex(player,x);
        buttons.setColumnIndex(player,y);
        System.out.println("You clicked btn"+x+y+"!");
        System.out.println(player.getLayoutX()+" "+player.getLayoutY());


    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    public void initialize() {
        // TODO
        start.setVisible(true);

        game.setVisible(false);
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
