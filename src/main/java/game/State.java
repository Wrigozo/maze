package game;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *vdfs
 */
public class State {

    //1.fel 2.jobbra 3.le 4.balra
    /**
     * Egy {@code int[][][]} tömb, mely labirintus mátrixának megfelelő koordinátáin tárolja, hogy hol vannak falak, a
     * következő sorrendben: fent, jobbra, lent és balra. Az 0-ás érték jelentése, hogy van fal, a 1-es pedig, hogy nincs.
     */
    public static int[][][] canStep = {
            {{0, 1, 0, 0}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}},
            {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 0}},
            {{1, 0, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}},
            {{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 0, 0, 1}}
    };
    private static Logger logger = LoggerFactory.getLogger(State.class);

    /**
     * Egy {@code int} érték, játékos x koordinátáját tárolja..
     */
    public int actualPosX;
    /**
     * Egy {@code int} érték, játékos y koordinátáját tárolja..
     */
    public int actualPosY;
    /**
     * Egy {@code int[]} érték, mely a szörny lehetséges lépéseit tárolja a szörny koordinátáinak megfelelően,
     * a következő sorrendben fent, jobbra, lent és balra. Az 1-es érték jelentése, hogy léphet a megfelelő irányba,
     * a 0-ás pedig, hogy nem.
     */
    public int[] lepesekEnemy = new int[4];
    /**
     * Egy {@code int[]} érték, mely a játékos lehetséges lépéseit tárolja a játékos koordinátáinak megfelelően,
     * a következő sorrendben fent, jobbra, lent és balra. Az 1-es érték jelentése, hogy léphet a megfelelő irányba,
     * a 0-ás pedig, hogy nem.
     */
    private int[] lepesekPlayer;
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a játékos vízszintes irányú lépéssel léphet.
     */
    public List<String> ishorizontalPlayer;
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a játékos függőleges irányú lépéssel léphet.
     */
    public List<String> isVerticalPlayer;
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a szörny vízszintes irányú lépéssel léphet.
     */
    public List<String> ishorizontalEnemy;
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a szörny függőleges irányú lépéssel léphet.
     */
    public List<String> isVerticalEnemy;
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely tárolja azoknak a gomboknak a nevét,hogy ahova léphet a játékos.
     */
    public List<String> enableButtonsPlayer = new ArrayList<>();
    /**
     * Egy {@link List}&lt;{@link String}&gt; típusú objektum, mely tárolja azoknak a gomboknak a nevét,hogy ahova léphet a szörny.
     */
    public List<String> enableButtonsEnemy = new ArrayList<>();



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


    public int[] enemylepes(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY) {
        int[] s = new int[2];
        this.lepesekEnemy = canStep[enemyPosX][enemyPosY];
        this.ishorizontalEnemy = new ArrayList<>();
        this.isVerticalEnemy = new ArrayList<>();
        EnableButtons(enemyPosX, enemyPosY, ishorizontalEnemy, isVerticalEnemy, lepesekEnemy, enableButtonsEnemy);
        checkDirection(enemyPosX, enemyPosY, playerPosX, playerPosY, s, ishorizontalEnemy, isVerticalEnemy, lepesekEnemy);

        return s;
    }

    public void setEnableButtons(int actualX, int actualY) {
        State state = new State(actualX, actualY);
        this.ishorizontalPlayer = new ArrayList<>();
        this.isVerticalPlayer = new ArrayList<>();
        EnableButtons(actualX, actualY, ishorizontalPlayer, isVerticalPlayer, state.lepesekPlayer, enableButtonsPlayer);
    }


    private void EnableButtons(int posX, int posY, List<String> isHorizontal, List<String> isVertical, int[] lepesek, List<String> enableButtons) {
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


    private void checkDirection(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY, int[] s, List<String> isHorizontal, List<String> isVertical, int[] lepesek) {
        if (isHorizontal.size() != 0) {

            if ((enemyPosY - playerPosY) > 0 && lepesek[3] == 1)
                enemyPosY = balra(enemyPosY);
            else if ((enemyPosY - playerPosY) < 0 && lepesek[1] == 1)
                enemyPosY = jobbra(enemyPosY);

            else if (isVertical.size() != 0) {
                if ((enemyPosX - playerPosX) > 0 && lepesek[0] == 1) {
                    enemyPosX = fel(enemyPosX);
                } else if ((enemyPosX - playerPosX) < 0 && lepesek[2] == 1)
                    enemyPosX = le(enemyPosX);
            }
            logger.info("Enemy position: (" + enemyPosX + ", " + enemyPosY + ");");
            s[0] = enemyPosX;
            s[1] = enemyPosY;

        } else if (isVertical.size() != 0) {
            if ((enemyPosX - playerPosX) > 0 && lepesek[0] == 1)
                enemyPosX -= 1;
            else if ((enemyPosX - playerPosX) < 0 && lepesek[2] == 1)
                enemyPosX += 1;

            else if (this.ishorizontalEnemy.size() != 0) {
                if ((enemyPosY - playerPosY) > 0 && lepesek[0] == 1) {
                    enemyPosY -= 1;
                } else if ((enemyPosY - playerPosY) < 0 && lepesek[2] == 1)
                    enemyPosY += 1;
            }
            s[0] = enemyPosX;
            s[1] = enemyPosY;

        }
    }


    private int fel(int x) {
        return x - 1;
    }

    private int jobbra(int y) {
        return y + 1;
    }

    private int le(int x) {
        return x + 1;
    }

    private int balra(int y) {
        return y - 1;
    }


}

