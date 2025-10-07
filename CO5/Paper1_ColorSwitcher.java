package co5;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Paper1_ColorSwitcher extends Application {
    private final Color color1 = Color.RED;
    private final Color color2 = Color.BLUE;
    private final StringProperty colorName = new SimpleStringProperty("Red");
    private boolean isColor1 = true;

    @Override
    public void start(Stage stage) {
        Rectangle rect = new Rectangle(120, 60, color1);
        ToggleButton toggle = new ToggleButton("Switch Color");
        Label label = new Label();
        label.textProperty().bind(colorName); // Binding label to colorName property

        toggle.setOnAction(e -> {
            isColor1 = !isColor1;
            rect.setFill(isColor1 ? color1 : color2);
            colorName.set(isColor1 ? "Red" : "Blue");
        });

        VBox root = new VBox(15, rect, toggle, label);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");
        stage.setScene(new Scene(root, 250, 180));
        stage.setTitle("Color Switcher");
        stage.show();
    }

    // Design note: Label text is bound to a StringProperty updated on toggle.
    public static void main(String[] args) { launch(args); }
}
