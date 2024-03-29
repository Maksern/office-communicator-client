package org.example.client.Frames.InterfaceAbstractFactory.Authorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.client.Client;
import org.example.client.Frames.InterfaceAbstractFactory.Factory.AbstractFactory;
import org.example.client.Models.User;

import java.io.IOException;
import java.net.Socket;


public class AuthorizationController{
    @Setter
    private AbstractFactory factory;

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

            if(id.equals("-1")){
                welcomeText.setText("This password or username are incorrect");
                client.closeEverything();
            } else {
                ((Node) event.getSource()).getScene().getWindow().hide();
                User user = new User();
                user.setUserId(Long.valueOf(id));
                user.setUserName(userName.getText());
                client.setUser(user);
                factory.getClientMenu().startWithClient(new Stage(), client);
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
