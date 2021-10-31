module sample.dictionaryjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires en.us;
    requires freetts;
    requires java.sql;

    opens sample.dictionaryjavafx to javafx.fxml;
    exports sample.dictionaryjavafx;
}