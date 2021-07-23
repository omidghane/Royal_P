package Model;

import javafx.scene.image.Image;

/**
 * The type Pekka.
 */
public class Pekka extends Troop {
    private int level;

    /**
     * Instantiates a new Pekka.
     */
    public Pekka() {
        level = 1;
        card_index = 7;
        set_level_base();
        setCost(4);
        setHit_speed(1.8);
        setSpeed(SPEED.FAST);
        setTarget(TARGET.GROUND);
        setRange(0);
        setArea_splash(false);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/ship (2).png");
        card_image_bar = new Image("/photoes/Card_icon_Mini_P.E.K.K.A..jpg");
        shut_image = new Image("/photoes/explosion3.png");
    }


    /**
     * Sets level base.
     *
     * @return the level base
     */
    public int set_level_base() {
//        int level = super.set_level_base();
        if(level == 1){
            setHP(600);
            setDamage(325);
        }else if(level == 2){
            setHP(660);
            setDamage(357);
        }else if(level == 3){
            setHP(726);
            setDamage(393);
        }else if(level == 4){
            setHP(798);
            setDamage(432);
        }else if(level == 5){
            setHP(876);
            setDamage(474);
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
        return "pekka";
    }
}
