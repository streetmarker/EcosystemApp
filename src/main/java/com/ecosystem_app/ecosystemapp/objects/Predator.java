package com.ecosystem_app.ecosystemapp.objects;

public class Predator extends Organism{
    public Predator(int x, int y) {
        super(x, y, true, "Predator", "\uD83D\uDC3A", "#ff4500");
    }

    @Override
    void act(World world) {

    }
}
