package com.ecosystem_app.ecosystemapp.objects;

import java.util.List;
import java.util.UUID;

public abstract class Organism {
    int x, y;
    int energy;
    UUID id;
    boolean isMoveable;
    String type;
    boolean shouldDie;
    String avatar;
    String color;

    public Organism(int x, int y, boolean isMoveable, String type, String avatar, String color) {
        this.x = x;
        this.y = y;
        this.energy = 15;
        this.id = java.util.UUID.randomUUID();
        this.isMoveable = isMoveable;
        this.type = type;
        this.shouldDie = false;
        this.avatar = avatar;
        this.color = color;
    }

    abstract void act(World world);

    void updateLocation(int x, int y, UUID[][] board, List<Organism> organisms){
        this.x = x;
        this.y = y;
        this.energy--;
        System.out.println("UPDATE LOCATION ENERGY: "+this.energy);
        if (this.energy == 0){
//            board[x][y] = null;
//            organisms.remove(this);
            this.shouldDie = true;
            System.out.println("Do uwalenia "+this.id);
        }
    }

    public String getAvatar() {
        return avatar;
    }
    public String getColor(){
        return color;
    }

    public UUID getId() {
        return id;
    }
}
