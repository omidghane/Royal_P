package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

/**
 * The type Card.
 */
public class Card {
    private boolean restricted;
    private int cost;
    /**
     * The Card image.
     */
    protected Image card_image;
    /**
     * The Card image bar.
     */
    protected Image card_image_bar;
    /**
     * The Shut image.
     */
    protected Image shut_image;
    /**
     * The Explode image.
     */
    protected Image explode_image;
    /**
     * The Card index.
     */
    protected int card_index;
    private int x_pos;
    private int y_pos;

    private int idea;

    /**
     * Instantiates a new Card.
     */
    public Card(){
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
     * Image newing.
     */
    public void image_newing(){

    }

    /**
     * Sets hp.
     *
     * @param HP the hp
     */
    public void setHP(int HP) {
    }

    /**
     * Sets x pos.
     *
     * @param x_pos the x pos
     */
    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    /**
     * Sets y pos.
     *
     * @param y_pos the y pos
     */
    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    /**
     * Gets x pos.
     *
     * @return the x pos
     */
    public int getX_pos() {
        return x_pos;
    }

    /**
     * Gets y pos.
     *
     * @return the y pos
     */
    public int getY_pos() {
        return y_pos;
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
     * Set level int.
     *
     * @param level the level
     * @return the int
     */
    public int setLevel(int level){
        return level;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Get hit speed double.
     *
     * @return the double
     */
    public double getHit_speed(){
        return 0;
    }

    /**
     * Get speed troop . speed.
     *
     * @return the troop . speed
     */
    public Troop.SPEED getSpeed(){
        return null;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHP() {
        return 0;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return 0;
    }

    /**
     * Get damage range int [ ].
     *
     * @return the int [ ]
     */
    public int[] get_DamageRange() {
        return null;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return 0;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public double getRange() {
        return 0;
    }

    /**
     * Gets target 1.
     *
     * @return the target 1
     */
    public Troop.TARGET getTarget1() {
        return null;
    }

    /**
     * Gets area splash.
     *
     * @return the area splash
     */
    public boolean getArea_splash() {
        return false;
    }

    /**
     * Get restricted boolean.
     *
     * @return the boolean
     */
    public boolean getRestricted(){
        return restricted;
    }

    /**
     * Sets restricted.
     *
     * @param restricted the restricted
     */
    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    /**
     * Gets ability.
     *
     * @return the ability
     */
    public String getAbility() {
        return null;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public double getRadius() {
        return 0;
    }

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public double getDuration() {
        return 0;
    }

    /**
     * Gets area damage.
     *
     * @return the area damage
     */
    public double getAreaDamage() {
        return 0;
    }

    /**
     * Gets card image.
     *
     * @return the card image
     */
    public Image getCard_image() {
        return card_image;
    }

    /**
     * Gets target 2.
     *
     * @return the target 2
     */
    public Building.TARGET getTarget2() {
        return  null;
    }

    /**
     * Gets lifetime.
     *
     * @return the lifetime
     */
    public int getLifetime() {
        return 0;
    }

    /**
     * Gets card index.
     *
     * @return the card index
     */
    public int getCard_index() {
        return card_index;
    }

    /**
     * Gets card image bar.
     *
     * @return the card image bar
     */
    public Image getCard_image_bar() {
        return card_image_bar;
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
     * Gets explode image.
     *
     * @return the explode image
     */
    public Image getExplode_image() {
        return explode_image;
    }
}
