package org.example.client.Frames.Authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.client.Client;
import org.example.client.Frames.ClientMenu.ClientMenu;

import java.io.IOException;
import java.net.Socket;


public class AuthorizationController{

    @FXML
    private Button logInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    public void logIn(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 8888);
            Client client = new Client(socket);
            String message = "logIn/".concat(userName.getText()).concat("/").concat(userPassword.getText());
            client.sendMessage(message);

            String id = client.getBufferedReader().readLine();
            Long clientId = Long.valueOf(id);
            client.setClientID(clientId);
            client.setClientUsername(userName.getText());

            if(clientId == -1){
                welcomeText.setText("This password or username is incorrect. Try another");
                client.closeEverything();
            } else {
                ((Node) event.getSource()).getScene().getWindow().hide();
                new ClientMenu().startWithClient(new Stage(), client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    public void signUp(ActionEvent event) {
//        try {
//            Socket socket = new Socket("localhost", 8888);
//            Client client = new Client(socket, userName.getText());
//
//            ((Node) event.getSource()).getScene().getWindow().hide();
//            Stage primaryStage = new Stage();
//            FXMLLoader loader = new FXMLLoader();
//
//            File currentFileDirectory = new File(String.valueOf(Authorization.class.getResource("")));
//            String fxmlPath = currentFileDirectory.getParentFile().getParentFile() + File.separator + "ClientMenu.fxml";
//            URL fxmlUrl = new URL(fxmlPath);
//
//            Pane root = loader.load(fxmlUrl.openStream());
//            ClientMenuController controller = (ClientMenuController) loader.getController();
//            controller.setUser(client);
//
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//            client.listenForMessage();
//            client.sendMessage();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
