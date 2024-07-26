package tictactoe;

import org.json.JSONObject;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class PlayerStatusBase extends AnchorPane {

    // UI Components
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final ScrollPane scrollPane;
    protected final VBox vBox;
    private final Stage stage;

    // Networking components
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    // Constructor to initialize UI and network connection
    public PlayerStatusBase(Stage stage) {
        this.stage = stage;
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        scrollPane = new ScrollPane();
        vBox = new VBox(10); // Vertical box with spacing of 10

        // Set dimensions of the AnchorPane
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        // Title text settings
        text.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
        text.setFont(new Font("Agency FB Bold", 45.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text0.setLayoutX(330.0);
        text0.setLayoutY(65.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TOE");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text1.setLayoutX(264.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TAC");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        // Labels for player name and score
        text2.setFill(javafx.scene.paint.Color.valueOf("#2A9DB8"));
        text2.setLayoutX(26.0);
        text2.setLayoutY(113.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("PLAYER NAME");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#F4A24C"));
        text3.setLayoutX(474.0);
        text3.setLayoutY(114.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("SCORE");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        // ScrollPane to hold the list of players
        scrollPane.setLayoutX(110.0);
        scrollPane.setLayoutY(121.0);
        scrollPane.setPrefHeight(227.0);
        scrollPane.setPrefWidth(366.0);
        scrollPane.setContent(vBox);
        scrollPane.setStyle("-fx-background-color: #2A9DB8;");
        scrollPane.setFitToWidth(true);

        // Add UI components to the AnchorPane
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(scrollPane);

        // Connect to the server
        try {
            // Establish socket connection
            socket = new Socket("192.168.1.106", 9081);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Send username to server
            JSONObject request = new JSONObject();
            request.put("username", "USERNAME");
            output.println(request.toString());

            // Read the active users list from server
            String response = input.readLine();
            JSONObject data = new JSONObject(response);
            List<String> usersList = Arrays.asList(data.getString("activeUsers").split(","));

            // Update UI with active users
            for (String user : usersList) {
                vBox.getChildren().add(createPlayerEntry(user));
            }

        } catch (IOException | JSONException e) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Method to create a UI entry for each player
    private AnchorPane createPlayerEntry(String playerName) {
        AnchorPane playerPane = new AnchorPane();
        playerPane.setStyle("-fx-background-color: #2A9DB8;");
        playerPane.setPrefHeight(50.0);
        playerPane.setPrefWidth(366.0);

        // Player name text
        Text playerText = new Text(playerName);
        playerText.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        playerText.setLayoutX(39.0);
        playerText.setLayoutY(30.0);
        playerText.setFont(new Font("Agency FB Bold", 24.0));

        // Player image
        ImageView playerImage = new ImageView();
        playerImage.setFitHeight(34.0);
        playerImage.setFitWidth(34.0);
        playerImage.setLayoutX(4.0);
        playerImage.setLayoutY(8.0);
        playerImage.setPickOnBounds(true);
        playerImage.setPreserveRatio(true);
        playerImage.setImage(new Image(getClass().getResource("person.png").toExternalForm()));

        // Challenge button
        Button challengeButton = new Button("challenge");
        challengeButton.setLayoutX(264.0);
        challengeButton.setLayoutY(10.0);
        challengeButton.setMnemonicParsing(false);
        challengeButton.setFont(new Font("Agency FB Bold", 18.0));
        challengeButton.setStyle("-fx-background-color: #F4A24C; -fx-text-fill: #ffffff;");

        // Set action for challenge button
        challengeButton.setOnAction(event -> challengeUser(playerName));

        // Add components to playerPane
        playerPane.getChildren().add(playerText);
        playerPane.getChildren().add(playerImage);
        playerPane.getChildren().add(challengeButton);

        return playerPane;
    }

    // Method to send a challenge request to the server
    private void challengeUser(String opponent) {
        try {
            JSONObject request = new JSONObject();
            request.put("command", "CHALLENGE");
            request.put("opponent", opponent);
            output.println(request.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
