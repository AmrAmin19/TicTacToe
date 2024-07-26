package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

public  class LoginBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final TextField EmailField;
    protected final TextField PassTxtField;
    protected final Button LoginBtn;
    protected final Button RegisterBtn;
    protected final ImageView arrow;
    protected final Stage stage;
    private final String serverIp;  // Remove static and final
    private static final int SERVER_PORT = 5005;

    public LoginBase( Stage stage , String serverIp ) {
        this.stage = stage;
        this.serverIp = serverIp; // Store the server IP address for future use

        
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        EmailField = new TextField();
        PassTxtField = new TextField();
        LoginBtn = new Button();
        RegisterBtn = new Button();
        arrow = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

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

        text2.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text2.setLayoutX(70.0);
        text2.setLayoutY(125.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("ENTER USERNAME ");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(67.0);
        text3.setLayoutY(234.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("ENTER PASSWORD");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        EmailField.setLayoutX(67.0);
        EmailField.setLayoutY(137.0);
        EmailField.setPrefHeight(42.0);
        EmailField.setPrefWidth(267.0);

        PassTxtField.setLayoutX(66.0);
        PassTxtField.setLayoutY(247.0);
        PassTxtField.setPrefHeight(42.0);
        PassTxtField.setPrefWidth(267.0);

        LoginBtn.setLayoutX(204.0);
        LoginBtn.setLayoutY(320.0);
        LoginBtn.setMnemonicParsing(false);
        LoginBtn.setPrefHeight(37.0);
        LoginBtn.setPrefWidth(90.0);
        LoginBtn.setText("LOGIN");
        LoginBtn.setFont(new Font("Agency FB Bold", 20.0));
        LoginBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        LoginBtn.setOnAction(e -> login ());


        RegisterBtn.setLayoutX(311.0);
        RegisterBtn.setLayoutY(320.0);
        RegisterBtn.setMnemonicParsing(false);
        RegisterBtn.setText("REGISTER");
        RegisterBtn.setFont(new Font("Agency FB Bold", 20.0));
        RegisterBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        RegisterBtn.setOnAction(e -> navigate());
        
        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(24.0);
        arrow.setLayoutY(30.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("arrow2.png").toExternalForm()));
        arrow.setOnMouseClicked(e -> navigatetoenterip());
        
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(EmailField);
        getChildren().add(PassTxtField);
        getChildren().add(LoginBtn);
        getChildren().add(RegisterBtn);
        getChildren().add(arrow);

    }
    
     public void navigate() {
       userRegisterBase regst = new userRegisterBase(stage);
        Scene regScene = new Scene(regst, 600, 400);
        stage.setScene(regScene);
    }
 
     public void navigatetoenterip () {
       EnterIpBase ipBase = new EnterIpBase(stage);
        Scene ipScene = new Scene(ipBase, 600, 400);
        stage.setScene(ipScene);
    }

    private void login() {
     String email = EmailField.getText();
        String password = PassTxtField.getText();

        new Thread(() -> {
            try (Socket socket = new Socket(serverIp, SERVER_PORT);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                JSONObject request = new JSONObject();
                request.put("action", "LOGIN");
                request.put("email", email);
                request.put("password", password);

                out.println(request.toString());
                System.out.println("Request sent: " + request.toString());

                String response = in.readLine();
                System.out.println("Response received: " + response);

                if (response != null) {
                    JSONObject jsonResponse = new JSONObject(response);

                    Platform.runLater(() -> {
                        try {
                            if ("SUCCESS".equals(jsonResponse.getString("status"))) {
                                Parent root = FXMLLoader.load(getClass().getResource("PlayerStatus.fxml"));
                                Scene playerStatusScene = new Scene(root);
                                stage.setScene(playerStatusScene);
                            } else {
                                showError(jsonResponse.getString("message"));
                            }
                        } catch (JSONException | IOException ex) {
                            showError("Error processing response.");
                            Logger.getLogger(LoginBase.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                } else {
                    Platform.runLater(() -> showError("No response from server."));
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Platform.runLater(() -> showError("Connection error."));
            }
        }).start();
    }

}
