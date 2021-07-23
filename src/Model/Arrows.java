package Model;

import javafx.scene.image.Image;

/**
 * The type Arrows.
 */
public class Arrows extends Spell{
    private double areaDamage;

    /**
     * Instantiates a new Arrows.
     */
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
        card_image = new Image("/photoes/tile_73.png");
        card_image_bar = new Image("/photoes/ArrowsCard.jpg");
        shut_image = new Image("/photoes/towerDefense_tile298.png");
    }

    @Override
    public double getAreaDamage() {
        return areaDamage;
    }

    /**
     * Sets area damage.
     *
     * @param areaDamage the area damage
     */
    public void setAreaDamage(double areaDamage) {
        this.areaDamage = areaDamage;
    }

    /**
     * Sets level base.
     *
     * @return the level base
     */
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
        return "arrow";
    }
}
