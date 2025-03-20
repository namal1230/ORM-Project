module SerenityMentalHealthTherapyCenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires lombok;

    opens org.example.controller to javafx.fxml;

    exports org.example;

}