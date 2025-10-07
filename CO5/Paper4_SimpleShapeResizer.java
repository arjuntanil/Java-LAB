package co5;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Paper4_SimpleShapeResizer extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(50, Color.ORANGE);
        Slider slider = new Slider(10, 100, 50);
        Label label = new Label();
        circle.radiusProperty().bind(slider.valueProperty()); // Binding slider to circle radius
        label.textProperty().bind(Bindings.format("Radius: %.0f", slider.valueProperty()));

        VBox root = new VBox(15, circle, slider, label);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(root, 250, 220));
        stage.setTitle("Shape Resizer");
        stage.show();
    }

    // Binding: Slider value is bound to circle radius and label text.
    public static void main(String[] args) { launch(args); }
}
