package ar.edu.davinci.BONUS;

public abstract class Bonus {
    private float bonus;

    public Bonus(float bonus) {
        this.bonus = bonus;
    }

    public float getBonus() {
        return bonus;
    }

    public float calcularbonus(float daño) {
        float dañototal = daño * this.getBonus();
        return dañototal;
    }

    ;
}
