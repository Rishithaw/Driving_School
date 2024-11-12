module com.ijse.gdse.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires lombok;
    requires mysql.connector.j;

    opens com.ijse.gdse.project.dto.tm to javafx.base;
    opens com.ijse.gdse.project.controller to javafx.fxml;
    exports com.ijse.gdse.project;
}