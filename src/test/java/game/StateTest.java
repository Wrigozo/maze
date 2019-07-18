package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {



    @Test
     boolean enemyStepsHelper(int fromX, int fromY, int toX, int toY, int actualPosXPlayer, int actualPosYPlayer) {
        State state=new State(actualPosXPlayer,actualPosYPlayer,fromX,fromY);
        int[] newCoordinatesOfTheEnemy = state.enemySteps(fromX, fromY,actualPosXPlayer,actualPosYPlayer);
        int[] newCoordinatesOfTheEnemy2 = state.enemySteps(newCoordinatesOfTheEnemy[0],newCoordinatesOfTheEnemy[1],actualPosXPlayer,actualPosYPlayer);
        return toX==newCoordinatesOfTheEnemy2[0]&&toY==newCoordinatesOfTheEnemy2[1];
    }
    @Test
    void enemySteps(){

        assertTrue(enemyStepsHelper(2,4,  1, 3, 0, 1));
        assertTrue(enemyStepsHelper(1,3,  1, 2, 1, 1));
        assertTrue(enemyStepsHelper(1,2,  1, 2, 1, 0));
        assertTrue(enemyStepsHelper(1,2,  2, 2, 2, 0));
        assertTrue(enemyStepsHelper(1,2,  2, 2, 5, 0));
        assertFalse(enemyStepsHelper(1,2,  2, 2, 0, 0));
    }




    @Test
    void isWin() {
        State state=new State(0,4,1,1);
        assertTrue(state.isWin(state.getActualPosXPlayer(),state.getActualPosYPlayer()));
        state=new State(3,4,1,1);
        assertFalse(state.isWin(state.getActualPosXPlayer(),state.getActualPosYPlayer()));

    }

    @Test
    void isDefeated() {
        State state=new State(0,4,0,4);
        assertTrue(state.isDefeated(state.getActualPosXPlayer(),state.getActualPosYPlayer(),state.getActualPosXEnemy(),state.getActualPosYEnemy()));
        state=new State(0,4,0,3);
        assertFalse(state.isDefeated(state.getActualPosXPlayer(),state.getActualPosYPlayer(),state.getActualPosXEnemy(),state.getActualPosYEnemy()));
    }


}