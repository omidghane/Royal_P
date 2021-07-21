package Model;

import javafx.scene.image.Image;

public class Building extends Card{
    protected Image shut_image;

    public enum TARGET {
        GROUND,AIR,GROUND_AIR;
    }

    private int HP;
    private double hit_speed;
    private TARGET target;
    private double range;
    private int lifetime;

    @Override
    public Image getShut_image() {
        return shut_image;
    }

    @Override
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int[] get_DamageRange() {
        return null;
    }

    @Override
    public TARGET getTarget2() {
        return target;
    }

    public void setTarget(TARGET target) {
        this.target = target;
    }

    @Override
    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public double getHit_speed() {
        return hit_speed;
    }

    public void setHit_speed(double hit_speed) {
        this.hit_speed = hit_speed;
    }

    @Override
    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
