package com.ecosystem_app.ecosystemapp.objects;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class World {
    final private int width;
    final private int height;
    final private UUID[][] board;
    final private List<Organism> organisms;

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

    public String[][] getBoard() {
        String[][] outBoard = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                UUID id = board[i][j];
                if (id != null) {
                    var organism = this.organisms.stream()
                            .filter(el -> el.id.equals(id))
                            .findFirst()
                            .orElse(null);
//                    outBoard[i][j] = organism != null ? organism.getClass().getSimpleName()+" "+organism.id.toString().substring(34) : "Unknown";
                    outBoard[i][j] = organism != null ? organism.id.toString() : "Unknown";
                } else {
                    outBoard[i][j] = "";
                }
            }
        }
        return outBoard;
    }

    public void putRandomObjects(int count) {
        for (int i = 0; i < count; i++) {
            Organism organism = OrganismFactory.createRandomOrganism();
            addOrganism(organism);
        }
    }

    public void runWorld() {
        for (int i = 0; i < this.organisms.size(); i++) {
            System.out.println(i+". "+this.organisms.get(i).type+" "+this.organisms.get(i).id);
        }
        List<Organism> toRemove = new ArrayList<>();
        for (Organism organism : this.organisms) {
            actInWorld(organism);
            if (organism.shouldDie){
                board[organism.x][organism.y] = null;
                toRemove.add(organism);
            }
        }
        this.organisms.removeAll(toRemove);
    }

    public void actInWorld(Organism organism) {
        int oldX = organism.x;
        int oldY = organism.y;
        int newX = organism.x;
        int newY = organism.y;
        if (newX < this.width && newY < this.height && organism.isMoveable) {
//            newY = searchFreeSlot(newX, newY);
            newX = searchRandomFreeSlot(newX, newY).x;
            newY = searchRandomFreeSlot(newX, newY).y;
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
    private Coordinate searchRandomFreeSlot(int newX, int newY) {
        // todo obsługa pętli jak nie ma ruchu
        List<Coordinate> coordinates = getPossibleMoves(newX, newY);
        Coordinate coordinate = coordinates.get(ThreadLocalRandom.current().nextInt(0, coordinates.size()-1));
        try {
            if (this.board[coordinate.x][coordinate.y] == null) return coordinate;
            else return searchRandomFreeSlot(newX, newY);
        } catch (Exception e) {
            return searchRandomFreeSlot(newX, newY);
        }
    }

    private List<Coordinate> getPossibleMoves(int x, int y){
        List<Coordinate> list = new ArrayList<>();

        int[][] offsets = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1,  0},          {1,  0},
                {-1,  1}, {0,  1}, {1,  1}
        };

        for (int[] o : offsets) {
            int nx = x + o[0];
            int ny = y + o[1];
            if (nx >= 0 && nx < width && ny >= 0 && ny < height)
                list.add(new Coordinate(nx, ny));
        }
        return list;
    }

    public Organism findByUUID(UUID uuid){
        Organism out = null;
        for (Organism organism : this.organisms){
            if (organism.id.equals(uuid)){
                out = organism;
                break;
            }
        }
        return out;
    }

    record Coordinate(int x, int y){}
    // todo backend test
    public void showBoard() {
        System.out.println("Board -----------------------------------------------------");
        for (UUID[] uuids : this.board) {
            System.out.println(Arrays.toString(uuids));
        }
        System.out.println("Board -----------------------------------------------------");
    }
}
