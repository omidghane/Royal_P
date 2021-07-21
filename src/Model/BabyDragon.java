package Model;

import javafx.scene.image.Image;

public class BabyDragon extends Troop{
    private int level;

    public BabyDragon(){
        level =1;
        card_index = 3;
        set_level_base();
        setCost(4);
        setHit_speed(1.8);
        setSpeed(SPEED.FAST);
        setTarget(TARGET.GROUND_AIR);
        setRange(3);
        setArea_splash(true);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/towerDefense_tile270.png");
        card_image_bar = new Image("/photoes/BabyDragonCard.jpg");
    }

    public int set_level_base() {
        if(level == 1){
            setHP(800);
            setDamage(10);
        }else if(level == 2){
            setHP(880);
            setDamage(110);
        }else if(level == 3){
            setHP(968);
            setDamage(121);
        }else if(level == 4){
            setHP(1064);
            setDamage(133);
        }else if(level == 5){
            setHP(1168);
            setDamage(146);
        }
        return 0;
    }

    @Override
    public int setLevel(int level) {
        this.level = super.setLevel(level);
        set_level_base();
        return 0;
    }

    @Override
    public String toString() {
        return "babyDragon";
    }
}
