package game;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class State {


    //a labda helyzete
    public int actualPosX;
    public int actualPosY;
    private static Logger logger = LoggerFactory.getLogger(State.class);
    private int[] lepesekPlayer = new int[4];
    public int[] lepesekEnemy = new int[4];
    public List<String> ishorizontalPlayer;
    public List<String> isVerticalPlayer;
    public List<String> ishorizontalEnemy;
    public List<String> isVerticalEnemy;
    private int counter = 0;
    public List<String> enableButtonsPlayer = new ArrayList<>();
    public List<String> enableButtonsEnemy = new ArrayList<>();

    //1.fel 2.jobbra 3.le 4.balra
    public static int[][][] canStep = {
            {{0, 1, 0, 0}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}},
            {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 0}},
            {{1, 0, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}},
            {{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 0, 0, 1}}
    };
    public List<String> getEnableButtonsPlayer(){
        return enableButtonsPlayer;
    }
    public List<String> getEnableButtonsEnemy(){
        return enableButtonsEnemy;
    }

    public State(int actualposX, int actualposY) {
        this.actualPosX = actualposX;
        this.actualPosY = actualposY;
        this.lepesekPlayer = canStep[actualPosX][actualPosY];


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
        //segédváltozók csak
        this.ishorizontalPlayer = new ArrayList<>();
        this.isVerticalPlayer = new ArrayList<>();
        for (int i = 0; i < lepesekPlayer.length; i++) {
            if (lepesekPlayer[i] != 0) {
                switch (i) { //megvizsálja hanyadik indexű
                    case 0:
                        actualPosX = fel(actualPosX);
                        isVerticalPlayer.add("btn" + actualPosX + actualPosY);
                        break;
                    case 1:
                        actualPosY = jobbra(actualPosY);
                        ishorizontalPlayer.add("btn" + actualPosX + actualPosY);
                        break;
                    case 2:
                        actualPosX = le(actualPosX);
                        isVerticalPlayer.add("btn" + actualPosX + actualPosY);
                        break;
                    default:
                        actualPosY = balra(actualPosY);
                        ishorizontalPlayer.add("btn" + actualPosX + actualPosY);
                        break;

                }
                this.enableButtonsPlayer.add("btn" + actualPosX + actualPosY);
                actualPosX = actualX;
                actualPosY = actualY;
            }
        }

    }




    int [] enemylepes(int enemyPosX, int enemyPosY, int playerPosX, int playerPosY){
        int [] s=new int[2];
        int tpmX=enemyPosX;
        int tmpY=enemyPosY;
        this.ishorizontalEnemy = new ArrayList<>();
        this.isVerticalEnemy=new ArrayList<>();
        this.lepesekEnemy = canStep[enemyPosX][enemyPosY];
        for (int i = 0; i < lepesekEnemy.length; i++) {
            if (lepesekEnemy[i] != 0) {
                switch (i) { //megvizsálja hanyadik indexű
                    case 0:
                        enemyPosX = fel(enemyPosX);
                        isVerticalEnemy.add("btn" + enemyPosX + enemyPosY);
                        break;
                    case 1:
                        enemyPosY = jobbra(enemyPosY);
                        ishorizontalEnemy.add("btn" + enemyPosX + enemyPosY);
                        break;
                    case 2:
                        enemyPosX = le(enemyPosX);
                        isVerticalEnemy.add("btn" + enemyPosX + enemyPosY);
                        break;
                    case 3:
                        enemyPosY = balra(enemyPosY);
                        ishorizontalEnemy.add("btn" + enemyPosX + enemyPosY);
                        break;
                }
                this.enableButtonsEnemy.add("btn" + enemyPosX + enemyPosY);
                enemyPosX =tpmX;
                enemyPosY = tmpY;
            }
        }
        System.out.println(s[0]+s[1]);

            if(this.ishorizontalEnemy.size()!=0){

                if((enemyPosY-playerPosY)>0&&lepesekEnemy[3]==1)
                    enemyPosY-=1;
                else if ((enemyPosY-playerPosY)<0&&lepesekEnemy[1]==1)
                    enemyPosY+=1;

                else if(this.isVerticalEnemy.size()!=0) {
                        if ((enemyPosX - playerPosX) > 0 && lepesekEnemy[0] == 1) {
                            enemyPosX -= 1;
                        } else if ((enemyPosX - playerPosX) < 0 && lepesekEnemy[2] == 1)
                            enemyPosX += 1;
                }



                logger.info("Enemy position: ("+enemyPosX+", "+enemyPosY+");");
                s[0]=enemyPosX;
                s[1]=enemyPosY;

            }
            else if(this.isVerticalEnemy.size()!=0) {
                if((enemyPosX-playerPosX)>0&&lepesekEnemy[0]==1)
                    enemyPosX-=1;
                else if ((enemyPosX-playerPosX)<0&&lepesekEnemy[2]==1)
                    enemyPosX+=1;


                else if(this.ishorizontalEnemy.size()!=0) {
                        if ((enemyPosY - playerPosY) > 0 && lepesekEnemy[0] == 1) {
                            enemyPosY -= 1;
                        } else if ((enemyPosY - playerPosY) < 0 && lepesekEnemy[2] == 1)
                            enemyPosY += 1;
                    }


                s[0]=enemyPosX;
                s[1]=enemyPosY;


            }
        return s;
        }




}

