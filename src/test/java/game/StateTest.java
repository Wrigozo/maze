package game;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import game.State;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {



    @Test
     boolean enemyLepesHelper(int fromX, int fromY, int toX, int toY,int actualPosXPlayer,int actualPosYPlayer) {
        State state=new State(actualPosXPlayer,actualPosYPlayer,fromX,fromY);
        int[] newCoordinatesOfTheEnemy = state.enemylepes(fromX, fromY,actualPosXPlayer,actualPosYPlayer);
        int[] newCoordinatesOfTheEnemy2 = state.enemylepes(newCoordinatesOfTheEnemy[0],newCoordinatesOfTheEnemy[1],actualPosXPlayer,actualPosYPlayer);
        return toX==newCoordinatesOfTheEnemy2[0]&&toY==newCoordinatesOfTheEnemy2[1];
    }
    @Test
    void enemylepes(){

        assertTrue(enemyLepesHelper(2,4,  1, 3, 0, 1));
        assertTrue(enemyLepesHelper(1,3,  1, 2, 1, 1));
        assertTrue(enemyLepesHelper(1,2,  1, 2, 1, 0));
        assertTrue(enemyLepesHelper(1,2,  2, 2, 2, 0));
        assertTrue(enemyLepesHelper(1,2,  2, 2, 5, 0));
        assertFalse(enemyLepesHelper(1,2,  2, 2, 0, 0));
    }


    @Test
    void setEnableButtons() {
    }

    @Test
    void isWin() {
        State state=new State(0,4,1,1);
        assertTrue(state.isWin(state.actualPosXPlayer,state.actualPosYPlayer));
        state=new State(3,4,1,1);
        assertFalse(state.isWin(state.actualPosXPlayer,state.actualPosYPlayer));

    }

    @Test
    void isDefeated() {
        State state=new State(0,4,0,4);
        assertTrue(state.isDefeated(state.actualPosXPlayer,state.actualPosYPlayer,state.actualPosXEnemy,state.actualPosYEnemy));
        state=new State(0,4,0,3);
        assertFalse(state.isDefeated(state.actualPosXPlayer,state.actualPosYPlayer,state.actualPosXEnemy,state.actualPosYEnemy));
    }

    @Test
    void getActualPosXPlayer() {
    }

    @Test
    void getActualPosYPlayer() {
    }

    @Test
    void getActualPosXEnemy() {
    }

    @Test
    void getActualPosYEnemy() {
    }

    @Test
    void getLepesekEnemy() {
    }

    @Test
    void getLepesekPlayer() {
    }

    @Test
    void getIshorizontalPlayer() {
    }

    @Test
    void getIsVerticalPlayer() {
    }

    @Test
    void getIshorizontalEnemy() {
    }

    @Test
    void getIsVerticalEnemy() {
    }

    @Test
    void getEnableButtonsPlayer() {
    }

    @Test
    void getEnableButtonsEnemy() {
    }
}