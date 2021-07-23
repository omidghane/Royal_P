package Model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.InputStream;

/**
 * The type Princess.
 */
public class Princess {
    private int HP;
    private int damage;
    private double range;
    private double hit_speed;
    private int level;
    private Point2D position;
    private int idea;
    private int target_card;
    private Image shut_image;

    /**
     * Instantiates a new Princess.
     *
     * @param position the position
     */
    public Princess(Point2D position){
        this.position = position;
        level = 1;
        set_level_base();
        range = 7.5;
        hit_speed = 0.8;
        target_card = -1;
        shut_image = new Image("/photoes/whitePuff19.png");
    }

    /**
     * Sets level base.
     *
     * @return the level base
     */
    public int set_level_base() {
        if(level == 1){
            setHP(1400);
            setDamage(50);
        }else if(level == 2){
            setHP(1512);
            setDamage(54);
        }else if(level == 3){
            setHP(1624);
            setDamage(58);
        }else if(level == 4){
            setHP(1750);
            setDamage(62);
        }else if(level == 5){
            setHP(1890);
            setDamage(69);
        }
        return 0;
    }

    /**
     * Gets shut image.
     *
     * @return the shut image
     */
    public Image getShut_image() {
        return shut_image;
    }

    /**
     * Gets target card.
     *
     * @return the target card
     */
    public int getTarget_card() {
        return target_card;
    }

    /**
     * Sets target card.
     *
     * @param target_card the target card
     */
    public void setTarget_card(int target_card) {
        this.target_card = target_card;
    }

    /**
     * Gets idea.
     *
     * @return the idea
     */
    public int getIdea() {
        return idea;
    }

    /**
     * Sets idea.
     *
     * @param idea the idea
     */
    public void setIdea(int idea) {
        this.idea = idea;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHP() {
        return HP;
    }

    /**
     * Sets hp.
     *
     * @param HP the hp
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public double getHit_speed() {
        return hit_speed;
    }

    /**
     * Sets hit speed.
     *
     * @param hit_speed the hit speed
     */
    public void setHit_speed(double hit_speed) {
        this.hit_speed = hit_speed;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
        set_level_base();
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "PrincessTower";
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Point2D getPosition() {
        return position;
    }
}
