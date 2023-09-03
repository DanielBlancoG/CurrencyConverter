package com.example.currencyconverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TemperatureConverterController extends BaseController {
    @FXML
    private Label textResult;
    @FXML
    private TextField temperatureResult;
    @FXML
    private TextField mainInputTemperature;
    @FXML
    private ComboBox<String> choiceTemperatureConversions;

    @FXML
    public void initializeItemsTemperature(){
        ObservableList<String> items = FXCollections.observableArrayList(
                "Celcius (°C) a Fahrenheit (°F)",
                    "Celcius (°C) a Kelvin (°K)"
        );
        initializeComboBox(choiceTemperatureConversions, items, "Celcius (°C) a Fahrenheit (°F)");

    }
    @Override
    protected void throwingError() {
        super.throwingError();
    }
    @FXML
    private void handleCurrencySelection(){
        //Mostramos los elementos ocultos
        textResult.setVisible(true);
        temperatureResult.setVisible(true);
    }
    @FXML
    private void validatingNumericalInput(){
        String input = mainInputTemperature.getText();
        //Verificamos si el campo esta vacio o no contiene solo numeros.
        if (validateNumericInput(input)){
            throwingError();

        }else {
            handleCurrencySelection();
            convertingTemperature(choiceTemperatureConversions.getValue(), Double.parseDouble(input));
            temperatureResult.setText(String.valueOf(convertedTemperature));

        }

    }


}

