package com.ecosystem_app.ecosystemapp.objects;

import java.util.List;
import java.util.UUID;

public abstract class Organism {
    int x, y;
    int energy;
    UUID id;
    boolean isMoveable;
    String type;

    public Organism(int x, int y, boolean isMoveable, String type) {
        this.x = x;
        this.y = y;
        this.energy = 5;
        this.id = java.util.UUID.randomUUID();
        this.isMoveable = isMoveable;
        this.type = type;
    }

    abstract void act(World world);

    void updateLocation(int x, int y, UUID[][] board, List<Organism> organisms){
        this.x = x;
        this.y = y;
        this.energy--;
        System.out.println("UPDATE LOCATION ENERGY: "+this.energy);
        if (this.energy == 0){
            board[x][y] = null;
            organisms.remove(this);
            System.out.println("Uwaliłem "+this.id);
        }
    }
}
