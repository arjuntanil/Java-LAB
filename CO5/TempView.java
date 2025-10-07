package co5;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TempView {
    public final TextField celsiusField = new TextField();
    public final TextField fahrenheitField = new TextField();
    public final TextField kelvinField = new TextField();
    public final Button resetBtn = new Button("Reset");
    public final ComboBox<String> unitBox = new ComboBox<>();
    public final Label instructions = new Label("Enter a value in any field. All units update automatically.");

    public TempView() {
        unitBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        unitBox.setValue("Celsius");
    }

    public VBox getLayout() {
        HBox fields = new HBox(10, new Label("Celsius:"), celsiusField,
                                   new Label("Fahrenheit:"), fahrenheitField,
                                   new Label("Kelvin:"), kelvinField);
        VBox root = new VBox(15, instructions, fields, resetBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return root;
    }
}
