package Model;

public class Princess {
    private int HP;
    private int damage;
    private double range;
    private double hit_speed;
    private int level;

    public Princess(){
        level = 1;
        set_level_base();
        range = 7.5;
        hit_speed = 0.8;
    }

    public int set_level_base() {
        System.out.println( "level : " + level);
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
        return "PrincessTower";
    }
}
