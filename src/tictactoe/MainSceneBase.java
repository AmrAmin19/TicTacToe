package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;





import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public  class MainSceneBase extends VBox {

    protected final TextField usernameField;
    protected final Button loginButton;
    protected final ListView<String> onlinePlayersList;
    protected final Button requestButton;
    protected final Text statusText;
    
     private Socket socket;
    private BufferedReader br;
    private PrintStream ps;
    private String playerName;
    protected  final Stage stage;

    public MainSceneBase(Stage  stage) {
        
        this.stage=stage;

        usernameField = new TextField();
        loginButton = new Button();
        onlinePlayersList = new ListView();
        requestButton = new Button();
        statusText = new Text();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        usernameField.setPromptText(" Enter username");

        loginButton.setMnemonicParsing(false);
        loginButton.setText("Login");
        loginButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleLogin();
                } catch (JSONException ex) {
                    Logger.getLogger(MainSceneBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        onlinePlayersList.setPrefHeight(200.0);
        onlinePlayersList.setPrefWidth(200.0);

        requestButton.setMnemonicParsing(false);
        requestButton.setText("Send Game Request");
        requestButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleSendRequest();
                } catch (JSONException ex) {
                    Logger.getLogger(MainSceneBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        statusText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        statusText.setStrokeWidth(0.0);
        statusText.setText("Text");

        getChildren().add(usernameField);
        getChildren().add(loginButton);
        getChildren().add(onlinePlayersList);
        getChildren().add(requestButton);
        getChildren().add(statusText);
        
        
         try {
            socket = new Socket("10.10.13.93", 9081);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());

            new Thread(() -> listenForMessages()).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

  private void handleLogin() throws JSONException {
    playerName = usernameField.getText();
    JSONObject loginJson = new JSONObject();
    loginJson.put("query", "SignIn");
    loginJson.put("playerName", playerName);
    ps.println(loginJson.toString());

    // Request the player list after login
    handleGetPlayerList();
}

@FXML
private void handleGetPlayerList() throws JSONException {
  
            JSONObject playerListJson = new JSONObject();
            playerListJson.put("query", "playerlist");
            ps.println(playerListJson.toString());
        
         
}


   @FXML
private void handleSendRequest() throws JSONException {
    String selectedPlayer = onlinePlayersList.getSelectionModel().getSelectedItem();
    if (selectedPlayer != null) {
        JSONObject requestJson = new JSONObject();
        requestJson.put("query", "request");
        requestJson.put("toPlayer", selectedPlayer);
        ps.println(requestJson.toString());
        System.out.println("Sent game request to: " + selectedPlayer); // Debugging statement
    } else {
        System.out.println("No player selected"); // Debugging statement
    }
}

    
    
    
    
private void listenForMessages() {
    while (true) {
        try {
            String message = br.readLine();
            System.out.println("Received message: " + message); // Debugging statement
            JSONObject json = new JSONObject(message);

            // Log the JSON object received
            System.out.println("JSON received: " + json.toString());

            if (!json.has("response")) {
                // Handle game request which does not have a "response" key
                if (json.getString("query").equals("request")) {
                    handleGameRequest(json.getString("fromPlayer"));
                } else {
                    System.out.println("Unknown query received without response key: " + json.toString());
                }
                continue;
            }

            String response = json.getString("response");

            switch (response) {
                case "SignIn response":
                    Platform.runLater(() -> statusText.setText("Logged in as " + playerName));
                    break;
                case "playerlist":
                    Platform.runLater(() -> {
                        try {
                            onlinePlayersList.getItems().clear();
                            JSONArray players = json.getJSONArray("players");

                            for (int i = 0; i < players.length(); i++) {
                                String player = players.isNull(i) ? null : players.getString(i);
                                if (player != null) {
                                    onlinePlayersList.getItems().add(player);
                                }
                            }
                            System.out.println("Updated player list: " + players); // Debugging statement
                        } catch (JSONException ex) {
                            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    break;
                case "request":
                    handleGameRequest(json.getString("fromPlayer"));
                    break;
                case "accept":
                    Platform.runLater(this::switchToGameScene);
                    break;
                case "decline":
                    Platform.runLater(() -> {
                        try {
                            statusText.setText("Request declined by " + json.getString("fromPlayer"));
                        } catch (JSONException ex) {
                            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    break;
                default:
                    System.out.println("Unknown response: " + response); // Debugging statement
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            break;
        } catch (JSONException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



    private void handleGameRequest(String fromPlayer)  {
        Platform.runLater(() -> {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game Request");
                alert.setHeaderText("Game Request from " + fromPlayer);
                alert.setContentText("Do you want to accept the game request?");

                ButtonType acceptButton = new ButtonType("Accept");
                ButtonType declineButton = new ButtonType("Decline");

                alert.getButtonTypes().setAll(acceptButton, declineButton);

                Optional<ButtonType> result = alert.showAndWait();
                boolean accept = result.isPresent() && result.get() == acceptButton;

                JSONObject responseJson = new JSONObject();
                responseJson.put("query", accept ? "accept" : "decline");
                responseJson.put("fromPlayer", fromPlayer);
                ps.println(responseJson.toString());

                if (accept) {
                    switchToGameScene();
                }
            } catch (JSONException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }


    private void switchToGameScene() {
        Platform.runLater(() -> {
           
            SharedData data=new SharedData();
            data.setPlayerName1("Amr");
            data.setPlayerName2("amin");
            
            
             BoardScene boardScene = new BoardScene(stage,data);
        Scene modScene = new Scene(boardScene, 600, 400);
        stage.setScene(modScene);
//            try {
//                Parent gameRoot = FXMLLoader.load(getClass().getResource("Board.fxml"));
//                Stage stage = (Stage) onlinePlayersList.getScene().getWindow();
//                stage.setScene(new Scene(gameRoot));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        });
    }

}
