module sda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    opens persistence to com.fasterxml.jackson.annotation,com.fasterxml.jackson.core,com.fasterxml.jackson.databind;
    opens services to javafx.base,com.fasterxml.jackson.annotation,com.fasterxml.jackson.core,com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    opens sda to javafx.fxml,com.fasterxml.jackson.annotation,com.fasterxml.jackson.core,com.fasterxml.jackson.databind;
    exports sda;
}
