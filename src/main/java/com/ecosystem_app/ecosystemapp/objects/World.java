package com.ecosystem_app.ecosystemapp.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class World {
    private int width;
    private int height;
    private UUID[][] board;
    private List<Organism> organisms;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new UUID[height][width];
        this.organisms = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        this.board[organism.x][organism.y] = organism.id;
        this.organisms.add(organism);
    }

    public UUID[][] getBoard() {
        return board;
    }

    public void putRandomObjects() {
        for (int i = 0; i < 5; i++) {
            Organism organism = OrganismFactory.createRandomOrganism();
            addOrganism(organism);
        }
    }

    public void runWorld() {
        for (int i = 0; i < this.organisms.size(); i++) {
            System.out.println(i+". "+this.organisms.get(i).type+" "+this.organisms.get(i).id);
        }
        List<Organism> organisms1 = this.organisms;
        for (int i = 0; i < organisms1.size(); i++) {
            actInWorld(organisms1.get(i));
        }
    }

    public void actInWorld(Organism organism) {
        int oldX = organism.x;
        int oldY = organism.y;
        int newX = organism.x;
        int newY = organism.y;
        if (newX < this.width && newY < this.height && organism.isMoveable) {
            newY = searchFreeSlot(newX, newY);
            UUID old = this.board[oldX][oldY];
            this.board[newX][newY] = old;
            this.board[oldX][oldY] = null;

            // aktualizacja pola organizmu
            organism.updateLocation(newX, newY, this.board, this.organisms);
        }
    }

    private int searchFreeSlot(int newX, int newY) {
        newY++;
        if (newY >= height) {
            return searchFreeSlot(newX, -1);
        } else {
            try {
                System.out.println(newY);
                if (this.board[newX][newY] == null) return newY;
                else return searchFreeSlot(newX, newY);
            } catch (Exception e) {
                return searchFreeSlot(newX, newY);
            }
        }
    }

    // todo backend test
    public void showBoard() {
        System.out.println("Board -----------------------------------------------------");
        for (UUID[] uuids : this.board) {
            System.out.println(Arrays.toString(uuids));
        }
        System.out.println("Board -----------------------------------------------------");
    }
}
