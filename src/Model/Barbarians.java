package Model;

import javafx.scene.image.Image;

public class Barbarians extends Troop{
    private int level;

    public Barbarians(){
        level =1;
        card_index = 5;
        set_level_base();
        setHP(300);
        setDamage(75);
        setCost(5);
        setHit_speed(1.5);
        setSpeed(SPEED.MEDIUM);
        setTarget(TARGET.GROUND);
        setRange(0);
        setArea_splash(false);
        setCount(4);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/ship (4).png");
        card_image_bar = new Image("/photoes/BarbariansCard.jpg");
    }

    public int set_level_base() {
        if(level == 1){
            setHP(300);
            setDamage(75);
        }else if(level == 2){
            setHP(330);
            setDamage(82);
        }else if(level == 3){
            setHP(363);
            setDamage(90);
        }else if(level == 4){
            setHP(438);
            setDamage(99);
        }else if(level == 5){
            setHP(480);
            setDamage(109);
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
        return "barbarians";
    }
}
