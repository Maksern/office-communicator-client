package org.example.client;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import lombok.Data;
import org.example.client.Frames.InterfaceAbstractFactory.ClientMenu.ClientMenuController;
import org.example.client.Frames.InterfaceAbstractFactory.Message.MessageVBox;
import org.example.client.Models.User;

import java.io.*;
import java.net.Socket;

@Data
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Thread listenThread;


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

    public void listenForMessage(ClientMenuController controller){
        listenThread = new ListenThread();
        listenThread.start();
    }

    public void stopListenForMessage(){
        listenThread.interrupt();
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
