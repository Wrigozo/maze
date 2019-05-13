package game;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class State {

    public boolean yourTurn=true;
    //a labda helyzete
    public int actualPosX;
    public int actualPosY;
    public void setActualPosX(int actualPosX) {
        this.actualPosX = actualPosX;
    }

    public void setActualPosY(int actualPosY) {
        this.actualPosY = actualPosY;
    }
    //1.fel 2.jobbra 3.le 4.balra
    public int[][][] canStep = {
            {{0, 1, 0, 0}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}},
            {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 0}},
            {{1, 0, 1, 0}, {0, 0, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 1, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}},
            {{1, 0, 1, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 0, 1, 1}},
            {{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 1, 0, 1}, {1, 0, 0, 1}}
    };
    private static Logger logger = LoggerFactory.getLogger(State.class);
    private int[] lepesek=new int[4];

    public State(int actualposX, int actualposY) {
        this.actualPosX=actualposX;
        this.actualPosY=actualposY;
    }
    void lepesek(int actualX, int actualY) {
       lepesek=canStep[actualX][actualY];
    }

    int fel(int x){
        return x-=1;
    }
    int jobbra(int y){
        return y+=1;
    }
    int le(int x){
        return x+=1;
    }
    int balra(int y){
        return y-=1;
    }



    public List<String> placesWhereYouCanMoveTheBall(int actualX, int actualY) {
        List<String> list = new ArrayList<>();
        lepesek( actualX, actualY);
        //segédváltozók csak
        int x=actualX;
        int y=actualY;
        for(int i=0; i<lepesek.length;i++){
            if(lepesek[i]!=0){
                switch (i){ //megvizsálja hanyadik indexű
                    case 0: actualX=fel(actualX);break;
                    case 1: actualY=jobbra(actualY);break;
                    case 2: actualX=le(actualX);break;
                    case 3: actualY=balra(actualY);break;
                }
                list.add("btn"+actualX+actualY);
                actualX=x;
                actualY=y;
            }
        }
        return list;
    }
}
