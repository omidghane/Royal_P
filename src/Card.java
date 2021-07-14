package Model;

import javafx.scene.image.Image;

public class Card {
    private boolean restricted;
    private int cost;
    protected Image card_image;

    public Card(){

    }

    public void image_newing(){

    }

    public Image getShut_image() {
        return null;
    }

    public int setLevel(int level){
        return level;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getHit_speed(){
        return 0;
    }

    public Troop.SPEED getSpeed(){
        return null;
    }

    public int getHP() {
        return 0;
    }

    public int getDamage() {
        return 0;
    }

    public int[] get_DamageRange() {
        return null;
    }

    public int getCount() {
        return 0;
    }

    public double getRange() {
        return 0;
    }

    public Troop.TARGET getTarget1() {
        return null;
    }

    public boolean getArea_splash() {
        return false;
    }

    public boolean getRestricted(){
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public String getAbility() {
        return null;
    }

    public double getRadius() {
        return 0;
    }

    public double getDuration() {
        return 0;
    }

    public double getAreaDamage() {
        return 0;
    }

    public Image getCard_image() {
        return card_image;
    }

    public Building.TARGET getTarget2() {
        return  null;
    }

    public int getLifetime() {
        return 0;
    }
}
