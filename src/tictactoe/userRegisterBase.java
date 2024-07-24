package tictactoe;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public  class userRegisterBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final TextField NewUserNameTxtField;
    protected final TextField NewPassTxtField;
    protected final Button NewRegisterBtn;
    protected final ImageView arrow;
     private final Stage stage;

    public userRegisterBase(Stage stage) {
        this.stage = stage;

        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        NewUserNameTxtField = new TextField();
        NewPassTxtField = new TextField();
        NewRegisterBtn = new Button();
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
        text2.setLayoutY(185.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("ENTER USERNAME ");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#f4a24c"));
        text3.setLayoutX(70.0);
        text3.setLayoutY(284.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("ENTER PASSWORD");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text4.setLayoutX(159.0);
        text4.setLayoutY(122.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("CREATE ACCOUNT");
        text4.setFont(new Font("Agency FB Bold", 45.0));

        NewUserNameTxtField.setLayoutX(67.0);
        NewUserNameTxtField.setLayoutY(192.0);
        NewUserNameTxtField.setPrefHeight(42.0);
        NewUserNameTxtField.setPrefWidth(267.0);

        NewPassTxtField.setLayoutX(66.0);
        NewPassTxtField.setLayoutY(290.0);
        NewPassTxtField.setPrefHeight(42.0);
        NewPassTxtField.setPrefWidth(267.0);

        NewRegisterBtn.setLayoutX(248.0);
        NewRegisterBtn.setLayoutY(346.0);
        NewRegisterBtn.setMnemonicParsing(false);
        NewRegisterBtn.setText("REGISTER");
        NewRegisterBtn.setFont(new Font("Agency FB Bold", 18.0));
        NewRegisterBtn.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        //handel registbtn
       NewRegisterBtn.setOnAction(e -> navigatetolog());

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(34.0);
        arrow.setFitWidth(37.0);
        arrow.setLayoutX(34.0);
        arrow.setLayoutY(40.0);
        arrow.setPickOnBounds(true);
        arrow.setSmooth(false);
        arrow.setImage(new Image(getClass().getResource("arrow2.png").toExternalForm()));
        arrow.setOnMouseClicked(e -> navigatetolog());

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        getChildren().add(text4);
        getChildren().add(NewUserNameTxtField);
        getChildren().add(NewPassTxtField);
        getChildren().add(NewRegisterBtn);
        getChildren().add(arrow);

  }
public void navigatetolog() {
       LoginBase log = new LoginBase(stage);
        Scene loScene = new Scene(log, 600, 400);
        stage.setScene(loScene);
    }

  }