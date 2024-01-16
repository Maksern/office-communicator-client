package org.example.client;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import lombok.Data;
import org.example.client.Frames.Message.MessageVBox;

import java.io.*;
import java.net.Socket;

@Data
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private boolean keepListening;
    private Long clientID;
    private String clientUsername;

    public Client(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything();
        }
    }


    public  void sendMessage(String message){
        try {
            if(socket.isConnected()){
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything();
        }
    }

    public void listenForMessage(VBox chatHistory){
        keepListening = true;
        new Thread(() -> {
            try {
                while(keepListening & socket.isConnected()){
                    String messageFromServer = bufferedReader.readLine();
                    if(messageFromServer.equalsIgnoreCase("closeChat")){
                        break;
                    }
                    Platform.runLater(() -> {
                        VBox messageVBox = new MessageVBox(messageFromServer, messageFromServer, clientUsername);
                        chatHistory.getChildren().add(messageVBox);
                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public void stopListenForMessage(){
        keepListening = false;
    }

    public void closeEverything(){
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
