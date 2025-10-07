package co5;

import javafx.beans.binding.Bindings;

public class TaskController {
    private final TaskModel model;
    private final TaskView view;

    public TaskController(TaskModel m, TaskView v) {
        this.model = m;
        this.view = v;

        view.listView.setItems(model.getTasks());
        view.countLabel.textProperty().bind(
            Bindings.size(model.getTasks()).asString("Tasks: %d")
        );

        view.addBtn.setOnAction(e -> {
            String t = view.input.getText().trim();
            if (!t.isEmpty()) {
                model.addTask(t);
                view.input.clear();
            }
        });

        view.removeBtn.setOnAction(e -> {
            String selected = view.listView.getSelectionModel().getSelectedItem();
            if (selected != null) model.removeTask(selected);
        });

        view.resetBtn.setOnAction(e -> model.resetTasks());
    }
}
