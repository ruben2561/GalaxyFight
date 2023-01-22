module be.inf1.galaxyfight {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens be.inf1.galaxyfight to javafx.fxml;
    exports be.inf1.galaxyfight;
    requires javafx.media;
}
