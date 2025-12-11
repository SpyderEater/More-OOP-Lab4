package ua.edu.ucu.apps;

public class Elf extends Character {
    public Elf() {
        super(10, 10);
    }

    @Override
    public void kick(Character c) {
        if (c.getPower() < this.getPower()) {
            // Kill everybody weaker than him
            c.setHp(0);
        } else {
            // Otherwise decrease the power of character by 1
            c.setPower(c.getPower() - 1);
        }
    }
}