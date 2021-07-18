package Model;

import javafx.scene.image.Image;

import java.io.IOException;

public class Archer extends Troop{
    private int level;

    public Archer() {
        level = 1;
        card_index = 0;
        set_level_base();
        setCost(3);
        setHit_speed(1.2);
        setSpeed(SPEED.MEDIUM);
        setTarget(TARGET.GROUND_AIR);
        setRange(5);
        setArea_splash(false);
        setCount(2);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/ship (1).png");
    }

    public int set_level_base() {
//        int level = super.set_level_base();
        if(level == 1){
            setHP(125);
            setDamage(33);
        }else if(level == 2){
            setHP(127);
            setDamage(44);
        }else if(level == 3){
            setHP(151);
            setDamage(48);
        }else if(level == 4){
            setHP(166);
            setDamage(53);
        }else if(level == 5){
            setHP(182);
            setDamage(58);
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
        return "Archer";
    }
}
