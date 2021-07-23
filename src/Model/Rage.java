package Model;

import javafx.scene.image.Image;

/**
 * The type Rage.
 */
public class Rage extends Spell{
    private double duration;

    /**
     * Instantiates a new Rage.
     */
    public Rage(){
        level = 1;
        card_index = 8;
        set_level_base();
        setCost(3);
        setRadius(5);
        image_newing();
    }

    @Override
    public void image_newing() {
        card_image = new Image("/photoes/RageCard.jpg");
        card_image_bar = new Image("/photoes/RageCard.jpg");
    }

    @Override
    public double getDuration() {
        return duration;
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Sets level base.
     *
     * @return the level base
     */
    public int set_level_base() {
        if(level == 1){
            setDuration(6);
        }else if(level == 2){
            setDuration(6.5);
        }else if(level == 3){
            setDuration(7);
        }else if(level == 4){
            setDuration(7.5);
        }else if(level == 5){
            setDuration(8);
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
        return "rage";
    }
}
