package Model;

import javafx.scene.image.Image;

public class Arrows extends Spell{
    private double areaDamage;

    public Arrows(){
        level = 1;
        card_index = 10;
        set_level_base();
        setCost(3);
        setRadius(4);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/pic/Arrows.jfif");
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
            setAreaDamage(144);
        }else if(level == 2){
            setAreaDamage(156);
        }else if(level == 3){
            setAreaDamage(174);
        }else if(level == 4){
            setAreaDamage(189);
        }else if(level == 5){
            setAreaDamage(210);
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
        return "Arrows";
    }
}
