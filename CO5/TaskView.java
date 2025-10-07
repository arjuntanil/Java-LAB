package co5;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TaskView {
    public final TextField input = new TextField();
    public final Button addBtn = new Button("Add");
    public final Button removeBtn = new Button("Remove Selected");
    public final Button resetBtn = new Button("Reset");
    public final ListView<String> listView = new ListView<>();
    public final Label countLabel = new Label("Tasks: 0");

    public VBox getLayout() {
        HBox controls = new HBox(10, input, addBtn, removeBtn, resetBtn);
        VBox root = new VBox(15, controls, listView, countLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return root;
    }
}
