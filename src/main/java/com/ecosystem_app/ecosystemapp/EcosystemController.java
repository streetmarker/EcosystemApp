package com.ecosystem_app.ecosystemapp;

import com.ecosystem_app.ecosystemapp.objects.Organism;
import com.ecosystem_app.ecosystemapp.objects.World;
import com.ecosystem_app.ecosystemapp.objects.WorldEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;
import java.util.UUID;

public class EcosystemController {

    @FXML
    public TextArea info;
    @FXML
    private GridPane gridPane;
    private final World world = new World(10,20);

    public void initialize() {
        world.putRandomObjects(20);
        getBoard();
        startAutoRefresh();
        info.setEditable(false);
    }
    private void startAutoRefresh() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(3), e -> {
                    world.runWorld();
                    getBoard();
                    world.showBoard();
                    List<WorldEvent> events = world.drainEvents();
                    if (!events.isEmpty()) {
                        appendEventsToLog(events);
                    }
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
    private void appendEventsToLog(List<WorldEvent> events) {
        StringBuilder sb = new StringBuilder();
        for (WorldEvent ev : events) {
            sb.append(ev.toString()).append("\n");
        }

        info.appendText(sb.toString());
        info.setScrollTop(Double.MAX_VALUE);
    }
}
