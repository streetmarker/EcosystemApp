package com.ecosystem_app.ecosystemapp;

import com.ecosystem_app.ecosystemapp.objects.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Parent root = fxmlLoader.load();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        HelloController controller = fxmlLoader.getController();

        World world = new World(10,10);
        controller.setWorld(world);
        stage.setTitle("Twoja piękna aplikacja");
        stage.setScene(scene);
        stage.show();
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }
}
