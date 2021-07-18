package Model;

public class King {
    private int HP;
    private int damage;
    private double range;
    private double hit_speed;
    private int level;

    public King(){
        level = 1;
        set_level_base();
        range = 7;
        hit_speed = 1;
    }

    public int set_level_base() {
        System.out.println( "level : " + level);
        if(level == 1){
            setHP(2400);
            setDamage(50);
        }else if(level == 2){
            setHP(2568);
            setDamage(53);
        }else if(level == 3){
            setHP(2736);
            setDamage(57);
        }else if(level == 4){
            setHP(2904);
            setDamage(60);
        }else if(level == 5){
            setHP(3096);
            setDamage(64);
        }
        return 0;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public double getHit_speed() {
        return hit_speed;
    }

    public void setHit_speed(double hit_speed) {
        this.hit_speed = hit_speed;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setLevel(int level) {
        this.level = level;
        set_level_base();
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "KingTower";
    }
}
