package tictactoe;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public  class EnterIpBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final TextField EnterIpTxtField;
    protected final Button NextBtn;
    protected final ImageView arrow;
  private final Stage stage;

    public EnterIpBase(Stage stage) {
        this.stage =stage ;

        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        EnterIpTxtField = new TextField();
        NextBtn = new Button();
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
        text2.setLayoutX(144.0);
        text2.setLayoutY(180.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("ENTER IP ADDRESS ");
        text2.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        text2.setWrappingWidth(298.9999854564667);
        text2.setFont(new Font("Agency FB Bold", 45.0));

        EnterIpTxtField.setLayoutX(167.0);
        EnterIpTxtField.setLayoutY(202.0);
        EnterIpTxtField.setPrefHeight(45.0);
        EnterIpTxtField.setPrefWidth(252.0);

        NextBtn.setLayoutX(258.0);
        NextBtn.setLayoutY(266.0);
        NextBtn.setMnemonicParsing(false);
        NextBtn.setText("NEXT");
        NextBtn.setFont(new Font("Agency FB Bold ", 24.0));
        NextBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        NextBtn.setOnAction(e ->navigatetologin ());

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(24.0);
        arrow.setLayoutY(30.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("arrow2.png").toExternalForm()));
       arrow.setOnMouseClicked(e -> navigate());
       
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(EnterIpTxtField);
        getChildren().add(NextBtn);
        getChildren().add(arrow);

    }
     public void navigate() {
       ChooseModeBase mode = new ChooseModeBase(stage);
        Scene modScene = new Scene(mode, 600, 400);
        stage.setScene(modScene);
    }
    private void navigatetoLogin() {
        String serverIp = EnterIpTxtField.getText().trim();

        if (serverIp.isEmpty()) {
            showError("IP address cannot be empty.");
            return;
        }

        if (!isValidIpAddress(serverIp)) {
            showError("Invalid IP address format.");
            return;
        }

        new Thread(() -> {
            if (isServerReachable(serverIp)) {
                Platform.runLater(() -> {
                    // Create LoginBase with the provided IP address
                    LoginBase loginBase = new LoginBase(stage, serverIp);
                    Scene loginScene = new Scene(loginBase, 600, 400);
                    stage.setScene(loginScene);
                });
            } else {
                Platform.runLater(() -> showError("Connection failed. Server not reachable."));
            }
        }).start();
    }
}
