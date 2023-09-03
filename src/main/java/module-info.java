module com.example.currencyconverter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.currencyconverter to javafx.fxml;
    exports com.example.currencyconverter;
}