package Model;

import javafx.scene.image.Image;

public class Giant extends Troop{
    private int level;

    public Giant() {
        level = 1;
        set_level_base();
        setCost(5);
        setHit_speed(1.5);
        setSpeed(SPEED.SLOW);
        setTarget(TARGET.BUILDING);
        setRange(0);
        setArea_splash(false);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/ship (6).png");
    }

    public int set_level_base() {
//        int level = super.set_level_base();
        System.out.println( "level : " + level);
        if(level == 1){
            setHP(2000);
            setDamage(126);
        }else if(level == 2){
            setHP(2200);
            setDamage(138);
        }else if(level == 3){
            setHP(2420);
            setDamage(152);
        }else if(level == 4){
            setHP(2660);
            setDamage(167);
        }else if(level == 5){
            setHP(2920);
            setDamage(183);
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
        return "Giant";
    }
}
