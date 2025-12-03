package com.ecosystem_app.ecosystemapp.objects;

import java.util.Random;

public class OrganismFactory {
    private static final Random random = new Random();

    public static Organism createRandomOrganism() {
        int type = random.nextInt(4);
        return switch (type) {
            case 0 -> new Plant(random.nextInt(10), random.nextInt(10));
            case 1 -> new Predator(random.nextInt(10), random.nextInt(10));
            default -> new Herbivore(random.nextInt(10), random.nextInt(10));
        };
    }
    public static Organism createPlant(int x, int y) {
        return new Plant(x, y);
    }
}
