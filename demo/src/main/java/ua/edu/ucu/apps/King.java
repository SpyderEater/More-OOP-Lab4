package ua.edu.ucu.apps;

import java.util.Random;

public class King extends Character {
    private static final Random random = new Random();

    public King() {
        super(random.nextInt(11) + 5, random.nextInt(11) + 5); // 5 to 15
    }

    @Override
    public void kick(Character c) {
        int damage = random.nextInt(this.getPower()) + 1; // Random damage from 1 to power
        c.setHp(c.getHp() - damage);
    }
}