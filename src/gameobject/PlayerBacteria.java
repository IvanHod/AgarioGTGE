package gameobject;

import view.PlayerBacteriaView;

public class PlayerBacteria extends MovableGameObject {


    int eatenAICount;

    public PlayerBacteria(PlayerBacteriaView playerBacteriaView) {
        gameObjectView = playerBacteriaView;
    }

    public int getEatenAiCount() {
        return eatenAICount;
    }

    public void increaseEatenAICount(){
        eatenAICount++;
    }


}
