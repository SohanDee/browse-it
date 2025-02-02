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
        Platform.runLater(() -> txtAddress.setText("www.google.com"));
        txtAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) Platform.runLater(() -> txtAddress.selectAll());
        });
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        String url = txtAddress.getText();
        if (url.isBlank()) return;
        loadWebPage(url);
    }

    private void loadWebPage(String url) {
        int i = 0;
        String protocol = null;
        int port = -1;
        String host = null;
        String path = "/";

        if ((i = url.indexOf("://")) != -1) {
            protocol = url.substring(0, i);
        } else protocol = "http";

        int j = (url.indexOf("/", (i == -1 ? (i = 0) : (i += 3))));
        host = j != -1 ? url.substring(i, j) : url.substring(i);

        if ((i = host.indexOf(":")) != -1) {
            port = Integer.parseInt(host.substring(i + 1));
            host = host.substring(0, i);
        } else {
            switch (protocol) {
                case "http":
                    port = 80;
                    break;
                case "https":
                    port = 443;
                    break;
                default:
                    port = -1;
            }
        }
        if (j != -1 && j != url.length()) path = url.substring(j);
        if (host.isBlank() || port == -1) throw new RuntimeException("Invalid URL");

        System.out.println("Protocol: " + protocol);
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);
        System.out.println("Path: " + path);
        System.out.println("===========================");
    }
}
