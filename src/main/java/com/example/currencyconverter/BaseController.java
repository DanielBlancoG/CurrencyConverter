package com.example.currencyconverter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class BaseController {
    //Variables:
    protected String convertedTemperature;
    protected static double conversionResult;
    protected static String originChoiceCurrency;
    protected static String destinationChoiceCurrency;
    protected static String textFieldInput;

    //Funciones genericas que nos permitiran reducir codigo.
    @FXML
    protected void initializeComboBox(ComboBox<String> ComboBox, ObservableList<String> items, String defaultValue) {
        ComboBox.setItems(items);
        ComboBox.setValue(defaultValue);
    }
    @FXML
    protected boolean validateNumericInput(String input) {
        return input.isEmpty() || !input.matches("\\d+(\\.\\d+)?");
    }

    //Creamos el error del input.
    protected void throwingError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, ingreso un numero valido.");
        alert.showAndWait();
    }
    //--------------------------------Temperature Converter------------------------------------------------------//
    protected void convertingTemperature(String label, double input){
        switch (label) {
            case "Celcius (°C) a Fahrenheit (°F)" -> convertedTemperature = "°F " + (input * ((double) 9 / 5) + 32);
            case "Celcius (°C) a Kelvin (°K)" -> convertedTemperature = "°K " + input + 273.15;
            default -> System.out.println("Opcion invalida");
        }
    }

    //--------------------------------Currency Converter------------------------------------------------------//

    private static boolean validCurrency(String currency) {
        String[] validCurrencies = {"COP", "USD", "EUR", "GBP", "JPY", "KRW"};

        for (String validCurrency : validCurrencies) {
            if (currency.contains(validCurrency)) {
                return true;

            }
        }
        return false;
    }

    protected void managingCurrencySelection(String label){
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(label);

        // Obtener las monedas desde el texto entre paréntesis
        String fromCurrency = "";
        String toCurrency = "";

        int i = 0;
        while (matcher.find()) {
            if (i == 0) {
                fromCurrency = matcher.group(1);

            } else if (i == 1) {
                toCurrency = matcher.group(1);

            }
            i++;
        }

        if (validCurrency(fromCurrency) && validCurrency(toCurrency)) {
            originChoiceCurrency = fromCurrency;
            destinationChoiceCurrency = toCurrency;

        } else {
            System.out.println("Monedas inválidas");

        }

    }

    protected void implementingCurrencyResults(Label descriptionAmount, String amount, Label choiceCurrency, String choice, Label conversionResult, String result){
        descriptionAmount.setText(String.valueOf("El ajuste de " + originChoiceCurrency + " " + amount));
        choiceCurrency.setText(String.valueOf(choice + " es de:"));
        conversionResult.setText(String.valueOf(result + " " + destinationChoiceCurrency));
    }

    protected void settingMainVisibility(ComboBox<String> choiceCoins, Button converCoinsButton, Label txtConversion,
                                 TextField mainTextField, Button mainButton, Label mainTextLabel) {

        choiceCoins.setVisible(true);converCoinsButton.setVisible(true);txtConversion.setVisible(true);
        mainTextField.setVisible(false);mainButton.setVisible(false);mainTextLabel.setVisible(false);

    }
    protected void settingVisibilityInterfaceCurrencies(Label amount, Label currencySelection, Label conversionResult, Label conversion,
                                                    Button backButton, ComboBox<String> choiceCoins, Button convertCoins) {

        amount.setVisible(true);currencySelection.setVisible(true);conversionResult.setVisible(true);backButton.setVisible(true);
        choiceCoins.setVisible(false);convertCoins.setVisible(false);conversion.setVisible(false);

    }
    protected void settingVisibilityInterfaceBack(TextField textField, Button mainButton, Label textLabel, Label textAmount,
                                                  Label textCurrencySelection, Label conversionResultText, Button backButton) {

        textField.setVisible(true);mainButton.setVisible(true);textLabel.setVisible(true);textAmount.setVisible(false);
        textCurrencySelection.setVisible(false);conversionResultText.setVisible(false);backButton.setVisible(false);

    }

}

