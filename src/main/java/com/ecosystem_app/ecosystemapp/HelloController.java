package com.ecosystem_app.ecosystemapp;

import com.ecosystem_app.ecosystemapp.objects.Organism;
import com.ecosystem_app.ecosystemapp.objects.World;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.UUID;

public class HelloController {
    @FXML
    private GridPane gridPane;
    private final World world = new World(10,10);

    public void initialize() {
        world.putRandomObjects(10);
        getBoard();
        startAutoRefresh();
    }
    private void startAutoRefresh() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> {
                    world.runWorld();
                    getBoard();
                    world.showBoard();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    public void getBoard() {
        gridPane.getChildren().clear();
        String[][] data = world.getBoard();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Organism organism = null;
                if (!data[i][j].isEmpty())
                    organism = world.findByUUID(UUID.fromString(data[i][j]));
                Label cell;
                if(organism != null){
                    String s = organism.getClass().getSimpleName() + " " + data[i][j].substring(34);
                    cell = new Label(organism.getAvatar() + s);
                    cell.setStyle("-fx-background-color: " + organism.getColor() + "; -fx-border-color: gray; -fx-padding: 5;");
                } else {
                    cell = new Label(String.valueOf(data[i][j]));
                    cell.setStyle("-fx-background-color: " + "#aaccee" + "; -fx-border-color: gray; -fx-padding: 5;");
                }
                cell.setMinWidth(100);
                gridPane.add(cell, j, i);
            }
        }
    }
    public void runCycle() {
        world.runWorld();
    }
}
