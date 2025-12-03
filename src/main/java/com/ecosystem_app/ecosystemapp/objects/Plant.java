package com.ecosystem_app.ecosystemapp.objects;

public class Plant extends Organism{
    public Plant(int x, int y) {
        super(x, y, false, WorldObjects.PLANT.energy, WorldObjects.PLANT.name, "\uD83C\uDF31", "#7ce413");
    }

    @Override
    void act(World world) {

    }
}
