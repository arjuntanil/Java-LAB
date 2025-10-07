package co5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskModel {
    private final ObservableList<String> tasks = FXCollections.observableArrayList();

    public ObservableList<String> getTasks() { return tasks; }
    public void addTask(String t) { tasks.add(t); }
    public void removeTask(String t) { tasks.remove(t); }
    public void resetTasks() { tasks.clear(); }
}
