package com.ecosystem_app.ecosystemapp.objects;

public class Herbivore extends Organism {
    public Herbivore(int x, int y) {
        super(x, y, true, "Herbivore");
    }

    @Override
    void act(World world) {
//        world.updateBoard(this.x,this.y,this.x+1,this.y);
    }
}
