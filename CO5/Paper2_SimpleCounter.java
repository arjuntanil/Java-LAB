package co5;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Paper2_SimpleCounter extends Application {
    private final IntegerProperty count = new SimpleIntegerProperty(0);

    @Override
    public void start(Stage stage) {
        Button clickBtn = new Button("Click Me");
        Button resetBtn = new Button("Reset");
        Label label = new Label();
        label.textProperty().bind(count.asString()); // Label bound to count property

        clickBtn.setOnAction(e -> count.set(count.get() + 1));
        resetBtn.setOnAction(e -> count.set(0));

        HBox buttons = new HBox(10, clickBtn, resetBtn);
        VBox root = new VBox(15, label, buttons);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(root, 220, 120));
        stage.setTitle("Simple Counter");
        stage.show();
    }

    // Binding: Label text is bound to IntegerProperty count.
    public static void main(String[] args) { launch(args); }
}
