module Practice.Cheshuin.Matrosov.Masalykin {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.base;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.fxml;

    exports application.gui;
    opens application.gui to javafx.base, javafx.controls, javafx.fxml,
            javafx.graphics, javafx.media, javafx.swing, javafx.web;

    opens application;
}