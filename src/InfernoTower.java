package Model;

import javafx.scene.image.Image;

public class InfernoTower extends Building{
    private int level;
    private int[] damage;

    public InfernoTower() {
        level = 1;
        set_level_base();
        setCost(5);
        setHit_speed(0.4);
        setTarget(TARGET.GROUND_AIR);
        setRange(6);
        setLifetime(40);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/towerDefense_tile204.png");
        shut_image = new Image("/pic/towerDefense_tile063.png");
    }

    public int set_level_base() {
//        int level = super.set_level_base();
        System.out.println( "level : " + level);
        if(level == 1){
            setHP(800);
            set_DamageRange(new int[]{20,400});
        }else if(level == 2){
            setHP(880);
            set_DamageRange(new int[]{22,440});
        }else if(level == 3){
            setHP(968);
            set_DamageRange(new int[]{24,484});
        }else if(level == 4){
            setHP(1064);
            set_DamageRange(new int[]{26,532});
        }else if(level == 5){
            setHP(1168);
            set_DamageRange(new int[]{29,584});
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
    public int[] get_DamageRange() {
        return damage;
    }

    public void set_DamageRange(int[] damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "InfernoTower";
    }
}
