package browse.it.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

public class MainSceneController {
    public AnchorPane root;
    public TextField txtAddress;
    public WebView wbPreview;

    public void initialize() {
        txtAddress.requestFocus();
        Platform.runLater(()->txtAddress.setText("www.google.com"));
        loadWebPage(txtAddress.getText());
        txtAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Platform.runLater(() -> txtAddress.selectAll());
        });
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
    }

    private void loadWebPage(String url) {

    }
}
