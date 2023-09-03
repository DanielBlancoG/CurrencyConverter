package com.example.currencyconverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MoneyConverterController extends BaseController{
    @FXML
    private ComboBox<String> choiceCoins;
    @FXML
    private TextField mainTextField;
    @FXML
    private Button convertCoinsButton, mainButton, backButton;
    @FXML
    private Label textConversion, mainTextLabel, textAmountConverted, textCurrencySelection, conversionResultText;


    //Creamos array de elecciones de conversion que dispondra el usuario.
    @FXML
    private void initializeItems(){
        ObservableList<String> items = FXCollections.observableArrayList(
                "Peso Colombiano (COP) a Dolar Americano (USD)",
                    "Peso Colombiano (COP) a Euros (EUR)",
                    "Peso Colombiano (COP) a Libras Esterlinas(GBP)",
                    "Peso Colombiano (COP) a Yen Japones (JPY)",
                    "Peso Colombiano (COP) a Won Sol Sul-Corenao(KRW)",
                    "-------------------------------------------------------------",
                    "Dolar Americano (USD) a Peso Colombiano (COP)",
                    "Euros (EUR) a Peso Colombiano (COP)",
                    "Libras Esterlinas(GBP) a Peso Colombiano (COP)",
                    "Yen Japones (JPY) a Peso Colombiano (COP)",
                    "Won Sol Sul-Corenao(KRW) a Peso Colombiano (COP)"
        );
          initializeComboBox(choiceCoins, items, "Peso Colombiano (COP) a Dolar Americano (USD)");
    }
    //Creamos la funcion para desplazarnos al siguiente panel.
    @FXML
    private void managingMainInterface (){
        settingMainVisibility(choiceCoins, convertCoinsButton, textConversion, mainTextField, mainButton, mainTextLabel);
        initializeItems();
    }
    //Invocamos la funcion que contiene el error del input.
    @Override
    protected void throwingError() {
        super.throwingError();
    }

    @FXML
    private void validatingNumericalInput(){
        textFieldInput = mainTextField.getText();

        //Verificamos si el campo esta vacio o no contiene solo numeros.
        if (validateNumericInput(textFieldInput)){
            throwingError();

        }else {
            managingMainInterface();

        }

    }

    //Nos desplazamos al panel final
    @FXML
    protected void convertCurrencies(ActionEvent event){
        String inputSelection = choiceCoins.getValue();

        //Filtramos y validamos la eleccion del usuario.
        managingCurrencySelection(inputSelection);
        //Lanzamos la peticion mediante una instancia de la clase.
        CurrencyConversionServiceAPI.sendingCurrencyRequest(originChoiceCurrency, destinationChoiceCurrency, textFieldInput);
        //Reemplazamos los elementos "Label" por los componenentes entregados por la api.
        implementingCurrencyResults(textAmountConverted, textFieldInput, textCurrencySelection,
                inputSelection, conversionResultText, String.valueOf(conversionResult));
        //Interfaz Grafica
        settingVisibilityInterfaceCurrencies(textAmountConverted, textCurrencySelection, conversionResultText,
                textConversion, backButton, choiceCoins, convertCoinsButton);
    }

    //Volvemos al panel inicial
    @FXML
    private void backOriginalPanel(ActionEvent event){
        settingVisibilityInterfaceBack(mainTextField, mainButton, mainTextLabel, textAmountConverted, textCurrencySelection,
                conversionResultText, backButton);

        mainTextField.clear();
    }

}

