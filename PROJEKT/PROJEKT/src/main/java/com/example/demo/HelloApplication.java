package com.example.demo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    Scene scene;
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/hello-view.fxml"));
        scene = fxmlLoader.load();
        HelloController controller = fxmlLoader.getController();
        controller.setapp(this);
        stage.setTitle("Roskilde Application");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    private void switchScene(String gui) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(gui));
        Scene load = loader.load();
        HelloController controller =loader.getController();
        controller.setapp(this);
        stage.setScene(load);
    }

    public static void main(String[] args) {
        launch();
    }

    public void responsiblebuttonclick() throws IOException {
        switchScene("/responsiblebuttonclick.fxml");
    }

    public void create() throws IOException {
        switchScene("/create.fxml");
    }

    public void logout() throws IOException {
        switchScene("/hello-view.fxml");
    }

    public void edit() throws IOException {
        switchScene("/edit.fxml");
    }

    public void createshift() throws IOException {
        switchScene("/createvagt.fxml");
    }

    public void editshift() throws IOException {
        switchScene("/editvagt.fxml");
    }

    public void volunteerseshifts() throws IOException {
        switchScene("/voluunteersevagter.fxml");
    }
    
}