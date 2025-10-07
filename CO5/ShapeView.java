package co5;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ShapeView {
    public final ComboBox<String> shapeBox = new ComboBox<>();
    public final Button drawBtn = new Button("Draw Shape");
    public final Button clearBtn = new Button("Clear All");
    public final ColorPicker colorPicker = new ColorPicker(Color.RED); // Extra credit
    public final Pane drawPane = new Pane();
    public final Label countLabel = new Label("Shapes: 0");

    public ShapeView() {
        shapeBox.getItems().addAll("Circle", "Rectangle");
        shapeBox.setValue("Circle");
        drawPane.setPrefSize(300, 200);
    }

    public VBox getLayout() {
        HBox controls = new HBox(10, shapeBox, colorPicker, drawBtn, clearBtn);
        VBox root = new VBox(15, controls, drawPane, countLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return root;
    }
}
