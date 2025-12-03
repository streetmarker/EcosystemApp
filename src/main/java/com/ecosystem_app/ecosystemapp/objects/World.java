package com.ecosystem_app.ecosystemapp.objects;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public final class World {
    final private int width;
    final private int height;
    final private UUID[][] board;
    final private List<Organism> organisms;
    private final ConcurrentLinkedQueue<WorldEvent> eventQueue = new ConcurrentLinkedQueue<>();

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new UUID[height][width];
        this.organisms = new ArrayList<>();
    }


    public void actInWorld(Organism organism) {
        int oldX = organism.x;
        int oldY = organism.y;
        int newX;
        int newY;
        if (organism.isMoveable) {
            Coordinate coordinate = searchRandomFreeSlot(oldX, oldY);
            newX = coordinate.x;
            newY = coordinate.y;
            UUID old = this.board[oldX][oldY];
            // add energy
            // todo possible UUID(old) missing
            try {
                if (old != null && findByUUID(old).getType().equals(WorldObjects.PLANT.name)
                        && organism.type.equals(WorldObjects.HERBIVORE.name)){
                    organism.addEnergy();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            this.board[oldX][oldY] = null;
            this.board[newX][newY] = old;

            // aktualizacja pola organizmu
            organism.updateLocation(newX, newY, this.board, this.organisms);
        }
    }

    public void addEvent(WorldEvent event) {
        eventQueue.add(event);
    }

    public void addOrganism(Organism organism) {
        this.board[organism.x][organism.y] = organism.id;
        this.organisms.add(organism);
    }

    public List<WorldEvent> drainEvents() {
        List<WorldEvent> drained = new ArrayList<>();
        WorldEvent ev;
        while ((ev = eventQueue.poll()) != null) {
            drained.add(ev);
        }
        return drained;
    }

    public Organism findByUUID(UUID uuid){
        Organism out = null;
        for (Organism organism : organisms){
            if (organism.id.equals(uuid)){
                out = organism;
                break;
            }
        }
        return out;
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

    private Coordinate getMove(List<Coordinate> coordinates){
        try {
            return coordinates.get(ThreadLocalRandom.current().nextInt(0, coordinates.size()-1));
        } catch (Exception e) {
            return getMove(coordinates);
        }
//        Coordinate coordinate = coordinates.get(ThreadLocalRandom.current().nextInt(0, coordinates.size()-1));
//        try {
//            if (this.board[coordinate.x][coordinate.y] == null) return coordinate; // don't allow covering
//            else return getMove(coordinates);
//        } catch (Exception e) {
//            return getMove(coordinates);
//        }
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
            if (nx >= 0 && nx < height && ny >= 0 && ny < width)
                list.add(new Coordinate(nx, ny));
        }
        return list;
    }

    private Coordinate getRandomCoordinate() {
        int randomX = ThreadLocalRandom.current().nextInt(height);
        int randomY = ThreadLocalRandom.current().nextInt(width);
        if (board[randomX][randomY] == null)
            return new Coordinate(randomX,randomY);
        else return getRandomCoordinate();
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
                terminateOrganism(organism);
                board[organism.x][organism.y] = null;
                toRemove.add(organism);
            }
        }
        this.organisms.removeAll(toRemove);

        if (Math.random() < 0.50) {
            Coordinate coordinate = getRandomCoordinate();
            Organism organism = OrganismFactory.createPlant(coordinate.x, coordinate.y);

            addOrganism(organism);
        }

    }

    private Coordinate searchRandomFreeSlot(int newX, int newY) {
        // todo obsługa pętli jak nie ma ruchu
        List<Coordinate> coordinates = getPossibleMoves(newX, newY);
        return getMove(coordinates);
    }

    public void showBoard() { // TEST purpose
        System.out.println("Board -----------------------------------------------------");
        for (UUID[] uuids : this.board) {
            System.out.println(Arrays.toString(uuids));
        }
        System.out.println("Board -----------------------------------------------------");
    }

    private void terminateOrganism(Organism o) {
        addEvent(new WorldEvent(EventType.DEATH, o.getId(),
                o.getClass().getSimpleName() + " died", o.getX(), o.getY()));
    }


    record Coordinate(int x, int y){}
}
