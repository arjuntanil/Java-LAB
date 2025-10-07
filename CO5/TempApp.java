package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TempApp extends Application {
    @Override
    public void start(Stage stage) {
        TempModel model = new TempModel();
        TempView view = new TempView();
        new TempController(model, view);
        stage.setScene(new Scene(view.getLayout(), 520, 160));
        stage.setTitle("Temperature Converter");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
