package Model;

public class Spell extends Card{
    protected int level;
    private String ability;
    private double radius;

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

    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    public double getRadius() {
        return radius;
    }

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
