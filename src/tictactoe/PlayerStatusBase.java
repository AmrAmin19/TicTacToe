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
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final ImageView RecImageView;
    protected final ImageView BackImageView;
    protected final ScrollPane scrollPane;
    protected final AnchorPane anchorPane2;
    protected final VBox vBox;
    private final Stage stage;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public PlayerStatusBase(Stage stage) {
        this.stage = stage;

        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        RecImageView = new ImageView();
        BackImageView = new ImageView();
        scrollPane = new ScrollPane();
        anchorPane2 = new AnchorPane();
        vBox = new VBox(10); // Vertical box with spacing of 10

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

        text2.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text2.setLayoutX(26.0);
        text2.setLayoutY(113.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("PLAYER NAME");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(432.0);
        text3.setLayoutY(114.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("SCORE ");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text4.setLayoutX(542.0);
        text4.setLayoutY(113.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("0");
        text4.setFont(new Font("Agency FB Bold", 35.0));

        RecImageView.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        RecImageView.setFitHeight(50.0);
        RecImageView.setFitWidth(50.0);
        RecImageView.setLayoutX(533.0);
        RecImageView.setLayoutY(336.0);
        RecImageView.setPickOnBounds(true);
        RecImageView.setPreserveRatio(true);
        RecImageView.setImage(new Image(getClass().getResource("rec.png").toExternalForm()));

        BackImageView.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        BackImageView.setFitHeight(50.0);
        BackImageView.setFitWidth(50.0);
        BackImageView.setLayoutX(26.0);
        BackImageView.setLayoutY(22.0);
        BackImageView.setPickOnBounds(true);
        BackImageView.setPreserveRatio(true);
        BackImageView.setImage(new Image(getClass().getResource("arrow2.png").toExternalForm()));

        scrollPane.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setLayoutX(90.0);
        scrollPane.setLayoutY(136.0);
        scrollPane.setPrefHeight(204.0);
        scrollPane.setPrefWidth(385.0);
        scrollPane.setVbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.ALWAYS);

        anchorPane2.setMinHeight(0.0);
        anchorPane2.setMinWidth(0.0);
        anchorPane2.setPrefHeight(344.0);
        anchorPane2.setPrefWidth(426.0);

        scrollPane.setContent(vBox);

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(text4);
        getChildren().add(RecImageView);
        getChildren().add(BackImageView);
        getChildren().add(scrollPane);

        // Connect to the server
        try {
            socket = new Socket("192.168.1.106", 9081); // Replace with your server IP and port
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Send username to server
            JSONObject request = new JSONObject();
            request.put("username", "USERNAME"); // Replace with actual username
            output.println(request.toString());

            // Read the active users list from server
            String response = input.readLine();
            JSONObject data = new JSONObject(response);
            List<String> usersList = Arrays.asList(data.getString("activeUsers").split(","));

            // Update UI with active users
            for (String user : usersList) {
                vBox.getChildren().add(createPlayerEntry(user)); // Add each player entry to the VBox
            }

        } catch (IOException | JSONException e) {
            Logger.getLogger(PlayerStatusBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Create a UI entry for each player
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

        // Player avatar image
        ImageView playerImage = new ImageView();
        playerImage.setFitHeight(34.0);
        playerImage.setFitWidth(34.0);
        playerImage.setLayoutX(4.0);
        playerImage.setLayoutY(8.0);
        playerImage.setPickOnBounds(true);
        playerImage.setPreserveRatio(true);
        playerImage.setImage(new Image(getClass().getResource("person.png").toExternalForm())); // Ensure the image exists in your resources

        // Challenge button
        Button challengeButton = new Button("challenge");
        challengeButton.setLayoutX(264.0);
        challengeButton.setLayoutY(10.0);
        challengeButton.setMnemonicParsing(false);
        challengeButton.setFont(new Font("Agency FB Bold", 18.0));
        challengeButton.setStyle("-fx-background-color: #ffd700;");

        // Add components to the player pane
        playerPane.getChildren().add(playerText);
        playerPane.getChildren().add(playerImage);
        playerPane.getChildren().add(challengeButton);

        return playerPane;
    }
}
