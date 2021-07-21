package Model;

import javafx.scene.image.Image;

public class Cannon extends Building{
    private int level;
    private int damage;

    public Cannon() {
        level = 1;
        card_index = 11;
        set_level_base();
        setCost(6);
        setHit_speed(0.8);
        setTarget(TARGET.GROUND);
        setRange(5.5);
        setLifetime(30);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/cannonMobile.png");
        card_image_bar = new Image("/photoes/CannonCard.jpg");
        shut_image = new Image("/photoes/cannonBall.png");
    }

    public int set_level_base() {
//        int level = super.set_level_base();
        if(level == 1){
            setHP(380);
            setDamage(60);
        }else if(level == 2){
            setHP(418);
            setDamage(66);
        }else if(level == 3){
            setHP(459);
            setDamage(72);
        }else if(level == 4){
            setHP(505);
            setDamage(79);
        }else if(level == 5){
            setHP(554);
            setDamage(87);
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
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "cannon";
    }
}
