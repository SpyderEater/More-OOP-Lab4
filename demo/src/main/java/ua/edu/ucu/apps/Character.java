package ua.edu.ucu.apps;

public abstract class Character {
    protected int power;
    protected int hp;

    public Character(int power, int hp) {
        this.power = power;
        this.hp = hp;
    }

    public abstract void kick(Character c);

    public boolean isAlive() {
        return this.hp > 0;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }

    public void setPower(int power) {
        this.power = Math.max(0, power);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{hp=" + hp + ", power=" + power + "}";
    }
}