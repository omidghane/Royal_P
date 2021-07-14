package Model;

import javafx.scene.image.Image;

public class Wizard extends Troop{
    private int level;

    public Wizard() {
        level = 1;
        set_level_base();
        setCost(5);
        setHit_speed(1.7);
        setSpeed(SPEED.MEDIUM);
        setTarget(TARGET.GROUND_AIR);
        setRange(5);
        setArea_splash(true);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/ship (5).png");
    }


    public int set_level_base() {
//        int level = super.set_level_base();
        System.out.println( "level : " + level);
        if(level == 1){
            setHP(340);
            setDamage(130);
        }else if(level == 2){
            setHP(374);
            setDamage(143);
        }else if(level == 3){
            setHP(411);
            setDamage(157);
        }else if(level == 4){
            setHP(452);
            setDamage(172);
        }else if(level == 5){
            setHP(496);
            setDamage(189);
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
        return "Wizard";
    }
}
