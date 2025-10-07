package co5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskApp extends Application {
    @Override
    public void start(Stage stage) {
        TaskModel model = new TaskModel();
        TaskView view = new TaskView();
        new TaskController(model, view);
        stage.setScene(new Scene(view.getLayout(), 480, 320));
        stage.setTitle("To-Do List Manager");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
