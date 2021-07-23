package Model;

/**
 * The type Spell.
 */
public class Spell extends Card{
    /**
     * The Level.
     */
    protected int level;
    private String ability;
    private double radius;

    /**
     * Instantiates a new Spell.
     */
    public Spell(){
//        super(3);
    }

    @Override
    public int setLevel(int level) {
        return super.setLevel(level);
    }

    @Override
    public String getAbility() {
        return ability;
    }

    /**
     * Sets ability.
     *
     * @param ability the ability
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public double getAreaDamage() {
        return 0;
    }

}
