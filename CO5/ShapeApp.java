package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShapeApp extends Application {
    @Override
    public void start(Stage stage) {
        ShapeModel model = new ShapeModel();
        ShapeView view = new ShapeView();
        new ShapeController(model, view);
        stage.setScene(new Scene(view.getLayout(), 400, 320));
        stage.setTitle("Shape Drawer");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
