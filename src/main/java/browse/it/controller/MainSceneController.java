package browse.it.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.*;
import java.net.Socket;

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

    public void txtAddressOnAction(ActionEvent actionEvent) throws IOException {
        String url = txtAddress.getText();
        if (url.isBlank()) return;
        loadWebPage(url);
    }

    private void loadWebPage(String url) throws IOException {
        int i = 0;
        String protocol = null;
        int port = -1;
        String host = null;
        String path;

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
        else {
            path = "/";
        }
        if (host.isBlank() || port == -1) throw new RuntimeException("Invalid URL");

        System.out.println("Protocol: " + protocol);
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);
        System.out.println("Path: " + path);
        System.out.println("===========================");

        Socket socket = new Socket(host, port);

        String finalHost = host;
        String finalProtocol = protocol;
        int finalPort = port;

        new Thread(() -> {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                String status = br.readLine();
                int statusCode = Integer.parseInt(status.split(" ")[1]);
                boolean redirection = statusCode >= 300 && statusCode <= 399;

                String line;
                String contentType = null;
                while ((line = br.readLine()) != null && !line.isBlank()) {
                    String header = line.split(":")[0];
                    String value = line.split(":")[1];

                    if (redirection) {
                        if(header.equalsIgnoreCase("location")) {
                            loadWebPage(value);
                        }
                    } else {
                        if (header.equalsIgnoreCase("content-type")) {
                            contentType = value.split(";")[0].strip();
                        }
                    }
                }
                StringBuilder content = new StringBuilder();
                if(contentType != null && contentType.equalsIgnoreCase("text/html")) {
                    while((line = br.readLine()) != null) {
                        content.append(line);
                    }
                }

                content.delete(0, content.indexOf("<"));
                String baseUrl = "<base href=\"%s://%s:%d%s\" />".formatted(finalProtocol, finalHost, finalPort, path);
                StringBuilder finalContent = content.insert((content.indexOf("<head>") + 6), baseUrl);

                Platform.runLater(() -> wbPreview.getEngine().loadContent(finalContent.toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        String request = """
                GET %s HTTP/1.1
                Host: %s
                User-Agent: Browse-it/1.0
                Connection: close
                Accept: text/html;
                
                """.formatted(path, host);
        bos.write(request.getBytes());
        bos.flush();
    }
}
