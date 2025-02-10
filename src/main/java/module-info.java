module com.example.mywork {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires static lombok;
    requires net.sf.jasperreports.core;


    opens com.example.mywork.controller to javafx.fxml;
    opens com.example.mywork.dto.tm to javafx.base;

    exports com.example.mywork;
}