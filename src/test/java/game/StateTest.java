package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {
    State state=new State(0,0);
    int []testArray={0,0};

    @Test
    void getEnableButtonsPlayer() {
    }

    @Test
    void getEnableButtonsEnemy() {
    }

    @Test
    void enemylepes() {
        assertArrayEquals(testArray, state.enemylepes(0,0,0,0));
    }

    @Test
    void setEnableButtons() {
    }
}