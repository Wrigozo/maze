package game;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class State {


    //1.fel 2.jobbra 3.le 4.balra
    public static int[][][] canStep = {
            {{0, 1, 0, 0}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}},
            {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 0}},
            {{1, 0, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}},
            {{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 0, 0, 1}}
    };
    private static Logger logger = LoggerFactory.getLogger(State.class);
    //a labda helyzete
    public int actualPosX;
    public int actualPosY;
    public int[] lepesekEnemy = new int[4];
    public List<String> ishorizontalPlayer;
    public List<String> isVerticalPlayer;
    public List<String> ishorizontalEnemy;
    public List<String> isVerticalEnemy;
    public List<String> enableButtonsPlayer = new ArrayList<>();
    public List<String> enableButtonsEnemy = new ArrayList<>();
    private int[] lepesekPlayer = new int[4];
    private int counter = 0;

    public State(int actualposX, int actualposY) {
        this.actualPosX = actualposX;
        this.actualPosY = actualposY;
        this.lepesekPlayer = canStep[actualPosX][actualPosY];


    }

    public List<String> getEnableButtonsPlayer() {
        return enableButtonsPlayer;
    }

    public List<String> getEnableButtonsEnemy() {
        return enableButtonsEnemy;
    }

    int fel(int x) {
        return x - 1;
    }

    int jobbra(int y) {
        return y + 1;
    }

    int le(int x) {
        return x + 1;
    }

    int balra(int y) {
        return y - 1;
    }


    public void setEnableButtons(int actualX, int actualY) {
        State state = new State(actualX, actualY);
        this.ishorizontalPlayer = new ArrayList<>();
        this.isVerticalPlayer = new ArrayList<>();
        EnableButtons(actualX, actualY, ishorizontalPlayer, isVerticalPlayer, state.lepesekPlayer, enableButtonsPlayer);
    }

    void EnableButtons(int posX, int posY, List<String> isHorizontal, List<String> isVertical, int[] lepesek, List<String> enableButtons) {
        int tmpX = posX;
        int tmpY = posY;
        for (int i = 0; i < lepesek.length; i++) {
            if (lepesek[i] != 0) {
                switch (i) { //megvizsgálja hanyadik indexű
                    case 0:
                        posX = fel(posX);
                        isVertical.add("btn" + posX + posY);
                        break;
                    case 1:
                        posY = jobbra(posY);
                        isHorizontal.add("btn" + posX + posY);
                        break;
                    case 2:
                        posX = le(posX);
                        isVertical.add("btn" + posX + posY);
                        break;
                    case 3:
                        posY = balra(posY);
                        isHorizontal.add("btn" + posX + posY);
                        break;
                }
                enableButtons.add("btn" + posX + posY);
                posX = tmpX;
                posY = tmpY;
            }
        }
    }


    int[] enemylepes(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY) {
        int[] s = new int[2];
        this.lepesekEnemy = canStep[enemyPosX][enemyPosY];
        this.ishorizontalEnemy = new ArrayList<>();
        this.isVerticalEnemy = new ArrayList<>();
        EnableButtons(enemyPosX, enemyPosY, ishorizontalEnemy, isVerticalEnemy, lepesekEnemy, enableButtonsEnemy);

        if (this.ishorizontalEnemy.size() != 0) {

            if ((enemyPosY - playerPosY) > 0 && lepesekEnemy[3] == 1)
                enemyPosY -= 1;
            else if ((enemyPosY - playerPosY) < 0 && lepesekEnemy[1] == 1)
                enemyPosY += 1;

            else if (this.isVerticalEnemy.size() != 0) {
                if ((enemyPosX - playerPosX) > 0 && lepesekEnemy[0] == 1) {
                    enemyPosX -= 1;
                } else if ((enemyPosX - playerPosX) < 0 && lepesekEnemy[2] == 1)
                    enemyPosX += 1;
            }


            logger.info("Enemy position: (" + enemyPosX + ", " + enemyPosY + ");");
            s[0] = enemyPosX;
            s[1] = enemyPosY;

        } else if (this.isVerticalEnemy.size() != 0) {
            if ((enemyPosX - playerPosX) > 0 && lepesekEnemy[0] == 1)
                enemyPosX -= 1;
            else if ((enemyPosX - playerPosX) < 0 && lepesekEnemy[2] == 1)
                enemyPosX += 1;


            else if (this.ishorizontalEnemy.size() != 0) {
                if ((enemyPosY - playerPosY) > 0 && lepesekEnemy[0] == 1) {
                    enemyPosY -= 1;
                } else if ((enemyPosY - playerPosY) < 0 && lepesekEnemy[2] == 1)
                    enemyPosY += 1;
            }


            s[0] = enemyPosX;
            s[1] = enemyPosY;


        }
        return s;
    }


}

