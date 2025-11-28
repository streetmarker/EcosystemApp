package com.ecosystem_app;

import com.ecosystem_app.ecosystemapp.objects.Organism;
import com.ecosystem_app.ecosystemapp.objects.World;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        World world = new World(10,10);
        world.putRandomObjects(8);
        world.showBoard();
//        com.ecosystem_app.ecosystemapp.objects.Organism organism = world.findByUUID(world.organisms.getFirst().getId());
//        organism.getAvatar();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 11; i++) {
                        Thread.sleep(1000);
                        world.runWorld();
                        world.showBoard();
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(10000);
                        world.showBoard();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        thread.start();
//        thread1.start();
    }
}
