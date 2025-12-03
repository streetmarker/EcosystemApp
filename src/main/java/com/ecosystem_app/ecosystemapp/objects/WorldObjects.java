package com.ecosystem_app.ecosystemapp.objects;

public enum WorldObjects {
    PLANT("Plant",0),
    HERBIVORE("Herbivore", 8),
    PREDATOR("Predator", 5);

    final String name;
    final int energy;

    WorldObjects(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }
}
