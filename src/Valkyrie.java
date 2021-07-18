package Model;

import javafx.scene.image.Image;

public class Valkyrie extends Troop{
    private int level;

    public Valkyrie() {
        level = 1;
        set_level_base();
        setCost(4);
        setHit_speed(1.5);
        setSpeed(Troop.SPEED.MEDIUM);
        setTarget(TARGET.GROUND);
        setRange(0);
        setArea_splash(true);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/ship (3).png");
    }

    public int set_level_base() {
//        int level = super.set_level_base();
        System.out.println( "level : " + level);
        if(level == 1){
            setHP(880);
            setDamage(120);
        }else if(level == 2){
            setHP(968);
            setDamage(132);
        }else if(level == 3){
            setHP(1064);
            setDamage(145);
        }else if(level == 4){
            setHP(1170);
            setDamage(159);
        }else if(level == 5){
            setHP(1284);
            setDamage(175);
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
        return "Valkyrie";
    }
}