package Model;

import javafx.scene.image.Image;

public class Fireball extends Spell{
    private double areaDamage;

        public Fireball(){
            level = 1;
            card_index = 9;
            set_level_base();
            setCost(4);
            setRadius(2.5);
            image_newing();
        }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/FireballCard.jpg");
    }

    @Override
    public double getAreaDamage() {
        return areaDamage;
    }

    public void setAreaDamage(double areaDamage) {
        this.areaDamage = areaDamage;
    }

    public int set_level_base() {
            if(level == 1){
                setAreaDamage(325);
            }else if(level == 2){
                setAreaDamage(357);
            }else if(level == 3){
                setAreaDamage(393);
            }else if(level == 4){
                setAreaDamage(432);
            }else if(level == 5){
                setAreaDamage(474);
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
            return "fireBall";
        }
}
