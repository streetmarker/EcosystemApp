package com.ecosystem_app.ecosystemapp;

import com.ecosystem_app.ecosystemapp.objects.World;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.UUID;

public class HelloController {
    @FXML
    private GridPane gridPane = new GridPane();
    private World world = new World(10,10);

    public void setWorld(World world){
        this.world = world;
        initialize();
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    public void initialize() {

        world.putRandomObjects();
        UUID[][] data = world.getBoard();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Label cell = new Label(String.valueOf(data[i][j]));
                cell.setStyle("-fx-border-color: gray; -fx-padding: 5;");
                gridPane.add(cell, j, i);
            }
        }
    }

}
