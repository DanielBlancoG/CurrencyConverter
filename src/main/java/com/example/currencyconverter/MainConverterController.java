package com.example.currencyconverter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainConverterController {
    @FXML
    protected AnchorPane originalContent;
    @FXML
    protected Button closedButton;
    @FXML
    protected Button minimizedButton;

    @FXML
    protected void closingApplication(ActionEvent event) {
        Platform.exit();
    }
    @FXML
    protected void minimizedApplication(ActionEvent event) {
        // Obténiendo una referencia al Stage actual
        Stage stage = (Stage) minimizedButton.getScene().getWindow();
        // Minimiza la ventana
        stage.setIconified(true);
    }

    //A travez de hipervinculos de scene builder, asignamos funciones que cambian los eventos del coversor.

    @FXML
    private void showCurrencyConverter(ActionEvent event){

        try {
            FXMLLoader moneyConverter = new FXMLLoader(getClass().getResource("/com/example/currencyconverter/MoneyConverter.fxml"));
            AnchorPane newContent = moneyConverter.load();
            originalContent.getChildren().setAll(newContent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void showTemperatureConverter(ActionEvent event){

        try{
            FXMLLoader temperatureConverter = new FXMLLoader(getClass().getResource("/com/example/currencyconverter/TemperatureConverter.fxml"));
            AnchorPane newContent = temperatureConverter.load();
            originalContent.getChildren().setAll(newContent);

            TemperatureConverterController items = temperatureConverter.getController();
            items.initializeItemsTemperature();


        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }


}

