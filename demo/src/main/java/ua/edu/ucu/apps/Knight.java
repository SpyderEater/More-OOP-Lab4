package ua.edu.ucu.apps;

import java.util.Random;

public class Knight extends Character {
    private static final Random random = new Random();

    public Knight() {
        super(random.nextInt(11) + 2, random.nextInt(11) + 2); // 2 to 12
    }

    @Override
    public void kick(Character c) {
        int damage = random.nextInt(this.getPower()) + 1; // Random damage from 1 to power
        c.setHp(c.getHp() - damage);
    }
}