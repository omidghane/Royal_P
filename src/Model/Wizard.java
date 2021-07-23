package Model;

import javafx.scene.image.Image;

/**
 * The type Wizard.
 */
public class Wizard extends Troop{
    private int level;

    /**
     * Instantiates a new Wizard.
     */
    public Wizard() {
        level = 1;
        card_index = 6;
        set_level_base();
        setCost(5);
        setHit_speed(1.6);
        setSpeed(SPEED.MEDIUM);
        setTarget(TARGET.GROUND_AIR);
        setRange(5);
        setArea_splash(true);
        setCount(1);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/ship (5).png");
        card_image_bar = new Image("/photoes/WizardCard.jpg");
        shut_image = new Image("/photoes/cannonBall.png");
    }


    /**
     * Sets level base.
     *
     * @return the level base
     */
    public int set_level_base() {
//        int level = super.set_level_base();
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
        return "wizard";
    }
}
