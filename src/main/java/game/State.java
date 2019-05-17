package game;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *A játék állapotát reprezentáló osztály.
 */
@Getter
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
     * Egy {@code int} érték, a játékos x koordinátáját tárolja..
     */
    public int actualPosXPlayer;
    /**
     * Egy {@code int} érték, a játékos y koordinátáját tárolja..
     */
    public int actualPosYPlayer;
    /**
     * Egy {@code int} érték, a szörny x koordinátáját tárolja..
     */
    public int actualPosXEnemy;

    /**
     * Egy {@code int} érték, a szörny y koordinátáját tárolja..
     */
    public int actualPosYEnemy;
    /**
     * Egy {@code int[]} tömb, mely a szörny lehetséges lépéseit tárolja a szörny koordinátáinak megfelelően,
     * a következő sorrendben fent, jobbra, lent és balra. Az 1-es érték jelentése, hogy léphet a megfelelő irányba,
     * a 0-ás pedig, hogy nem.
     */
    public int[] lepesekEnemy = new int[4];
    /**
     * Egy {@code int[]} tömb, mely a játékos lehetséges lépéseit tárolja a játékos koordinátáinak megfelelően,
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



    public State(int actualposXPlayer, int actualposYPlayer,int actualposXEnemy, int actualposYEnemy) {
        this.actualPosXPlayer = actualposXPlayer;
        this.actualPosYPlayer = actualposYPlayer;
        this.lepesekPlayer = canStep[actualPosXPlayer][actualPosYPlayer];
        this.lepesekEnemy=canStep[actualPosXEnemy][actualPosYEnemy];
        this.actualPosXEnemy=actualposXEnemy;
        this.actualPosYEnemy=actualposYEnemy;
    }
    public List<String> getIshorizontalEnemy() {
        return ishorizontalEnemy;
    }

    public static int[][][] getCanStep() {
        return canStep;
    }

    public int getActualPosXEnemy() {
        return actualPosXEnemy;
    }

    public int getActualPosYEnemy() {
        return actualPosYEnemy;
    }

    public List<String> getIsVerticalEnemy() {
        return isVerticalEnemy;
    }

    public List<String> getEnableButtonsEnemy() {
        return enableButtonsEnemy;
    }

    public int getActualPosXPlayer() {
        return actualPosXPlayer;
    }

    public int getActualPosYPlayer() {
        return actualPosYPlayer;
    }

    /**
     *Visszadja a szörny új koordinátáit.
     * @param enemyPosX egy {@code int} érték, amely tárolja a szörny aktuális x koordinátáját
     * @param enemyPosY egy {@code int} érték, amely tárolja a szörny aktuális y koordinátáját
     * @param playerPosX egy {@code int} érték, amely tárolja a játékos aktuális x koordinátáját
     * @param playerPosY egy {@code int} érték, amely tárolja a játkos aktuális y koordinátáját
     * @return egy {@code int[]} tömböt ad vissza, a szörny új koordinátáit adja vissza
     */
    public int[] enemylepes(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY) {
        int[] newCoordinatesOfTheEnemy = new int[2];
        this.lepesekEnemy = canStep[enemyPosX][enemyPosY];
        this.ishorizontalEnemy = new ArrayList<>();
        this.isVerticalEnemy = new ArrayList<>();
        enableButtons(enemyPosX, enemyPosY, ishorizontalEnemy, isVerticalEnemy, lepesekEnemy, enableButtonsEnemy);
        checkDirection(enemyPosX, enemyPosY, playerPosX, playerPosY, newCoordinatesOfTheEnemy, lepesekEnemy);

        return newCoordinatesOfTheEnemy;
    }

    /**
     * Beállítja a játékos által eléhető gombok nevét a kapott koordináták alapján.
     * @param actualX egy {@code int} érték, amely az aktuális x koordinátáját tárolja a játékosnak
     * @param actualY egy {@code int} érték, amely az aktuális y koordinátáját tárolja a játékosnak
     */
    public void setEnableButtons(int actualX, int actualY) {
        State state = new State(actualPosXPlayer, actualPosYPlayer, actualPosXEnemy, actualPosYEnemy);
        this.ishorizontalPlayer = new ArrayList<>();
        this.isVerticalPlayer = new ArrayList<>();
        enableButtons(actualX, actualY, ishorizontalPlayer, isVerticalPlayer, state.lepesekPlayer, enableButtonsPlayer);
    }

    /**
     *Egy {@link javafx.scene.shape.Circle} objektum lépéseinek irányát vizsgálja, a megfelelőeket eltárolja.
     * @param posX egy {@code int} érték, amely az aktuális x koordinátáját tárolja a megadott elemnek
     * @param posY egy {@code int} érték, amely az aktuális y koordinátáját tárolja a megadott elemnek
     * @param isHorizontal egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a meadott elem vízszintes irányú lépéssel léphet.
     * @param isVertical egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a megadott elem függőleges irányú lépéssel léphet.
     * @param lepesek egy {@code int[]} érték, mely a megadott elem lehetséges lépéseit tárolja a következő sorrendben fent, jobbra, lent és balra. Az 1-es érték jelentése, hogy léphet a megfelelő irányba,
     * a 0-ás pedig, hogy nem.
     * @param enableButtons egy {@link List}&lt;{@link String}&gt; típusú objektum, mely azoknak a gomboknak a nevét tárolja, ahova a szörny vízszintes irányú lépéssel léphet.
     */
    public void enableButtons(int posX, int posY, List<String> isHorizontal, List<String> isVertical, int[] lepesek, List<String> enableButtons) {
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

    /**
     *Leteszteli milyen irányokba léphet a szörny, és beállítja annak pozícióját.
     * @param enemyPosX egy {@code int} érték, amely tárolja a szörny aktuális x koordinátáját
     * @param enemyPosY egy {@code int} érték, amely tárolja a szörny aktuális y koordinátáját
     * @param playerPosX egy {@code int} érték, amely tárolja a játékos aktuális x koordinátáját
     * @param playerPosY egy {@code int} érték, amely tárolja a játékos aktuális x koordinátáját
     * @param newCoordinatesOfTheEnemy egy {@code int[]} tömböt, mely tárolja a szörny új koordinátáit
     * @param lepesek egy {@code int[]} tömb, mely a megadott elem lehetséges lépéseit tárolja a következő sorrendben fent, jobbra, lent és balra. Az 1-es érték jelentése, hogy léphet a megfelelő irányba,
     *      * a 0-ás pedig, hogy nem.
     */
    public void checkDirection(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY, int[] newCoordinatesOfTheEnemy,  int[] lepesek) {
        if (ishorizontalEnemy.size() != 0) {

            if ((enemyPosY - playerPosY) > 0 && lepesek[3] == 1)
                enemyPosY = balra(enemyPosY);
            else if ((enemyPosY - playerPosY) < 0 && lepesek[1] == 1)
                enemyPosY = jobbra(enemyPosY);

            else if (isVerticalEnemy.size() != 0) {
                if ((enemyPosX - playerPosX) > 0 && lepesek[0] == 1) {
                    enemyPosX = fel(enemyPosX);
                } else if ((enemyPosX - playerPosX) < 0 && lepesek[2] == 1)
                    enemyPosX = le(enemyPosX);
            }
            logger.info("Enemy position: (" + enemyPosX + ", " + enemyPosY + ");");
            newCoordinatesOfTheEnemy[0] = enemyPosX;
            newCoordinatesOfTheEnemy[1] = enemyPosY;

        } else if (this.isVerticalEnemy.size() != 0) {
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
            newCoordinatesOfTheEnemy[0] = enemyPosX;
            newCoordinatesOfTheEnemy[1] = enemyPosY;

        }
    }
    public boolean isWin(int actualPosXPlayer, int actualPosYPlayer){
        if(actualPosXPlayer==0&&actualPosYPlayer==4)
            return true;
        else return false;
    }
    public boolean isDefeated(int actualPosXPlayer, int actualPosYPlayer, int actualPosXEnemy, int actualPosYEnemy){
        if(actualPosXPlayer==actualPosXEnemy&&actualPosYPlayer==actualPosYEnemy){
            return true;
        }
        else return false;
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

