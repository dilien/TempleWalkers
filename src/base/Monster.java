package base;

import temple.Temple;

public class Monster {
    Temple temple;
    int distance;
    public Monster(){
        distance = 5;
        temple = Temple.getInstance();
        temple.tickEvent.listen((_void)->tick());
    }

    public void changeDistance(int dist){
        distance += dist;
        if(dist <= 0){
            //lose
        }
    }

    void tick(){
        if(temple.dark){
            changeDistance(-1);
        }else if(distance > 8){
            distance = (int) (Math.random() * 4) + 8;
        }else{
            changeDistance(2);
        }
    }
}
