package Model;

/**
 * The type Troop.
 */
public class Troop extends Card{

    /**
     * Instantiates a new Troop.
     */
    public Troop(){

    }

    /**
     * The enum Speed.
     */
    public enum SPEED {
        /**
         * Slow speed.
         */
        SLOW,
        /**
         * Medium speed.
         */
        MEDIUM,
        /**
         * Fast speed.
         */
        FAST
    }

    /**
     * The enum Target.
     */
    public enum TARGET {
        /**
         * Ground target.
         */
        GROUND,
        /**
         * Air target.
         */
        AIR,
        /**
         * Ground air target.
         */
        GROUND_AIR,
        /**
         * Building target.
         */
        BUILDING
    }

    private int HP;
    private int damage;
    private double hit_speed;
    private SPEED speed;
    private TARGET target;
    private int range;
    private boolean area_splash;
    private int count;

//    @Override
//    public int set_level_base() {
//      return super.set_level_base();
//    }


    @Override
    public int setLevel(int level) {
        return super.setLevel(level);
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getHP() {
        return HP;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
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

    @Override
    public SPEED getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(SPEED speed) {
        this.speed = speed;
    }

    @Override
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public double getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public TARGET getTarget1() {
        return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(TARGET target) {
        this.target = target;
    }

    @Override
    public boolean getArea_splash() {
        return area_splash;
    }

    /**
     * Sets area splash.
     *
     * @param area_splash the area splash
     */
    public void setArea_splash(boolean area_splash) {
        this.area_splash = area_splash;
    }
}
