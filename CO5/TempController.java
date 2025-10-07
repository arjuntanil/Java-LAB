package co5;

import javafx.util.converter.NumberStringConverter;

public class TempController {
    private final TempModel model;
    private final TempView view;

    public TempController(TempModel m, TempView v) {
        this.model = m;
        this.view = v;

        view.celsiusField.textProperty().bindBidirectional(model.celsius, new NumberStringConverter());
        view.fahrenheitField.textProperty().bindBidirectional(model.fahrenheit, new NumberStringConverter());
        view.kelvinField.textProperty().bindBidirectional(model.kelvin, new NumberStringConverter());

        view.resetBtn.setOnAction(e -> {
            model.setCelsius(0);
        });
    }
}
