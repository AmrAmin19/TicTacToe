package tictactoe;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WATTINGBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final AnchorPane AncorPane2;
    protected final Text text2;
    protected final Text text3;
    protected final AnchorPane AncorPane1;
    protected final Text text4;
    protected final Text text5;
    protected final Text text6;
    protected final ImageView ImageView;
    private final Stage stage;

    public WATTINGBase(Stage stage) {
        this.stage = stage;
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        AncorPane2 = new AnchorPane();
        text2 = new Text();
        text3 = new Text();
        AncorPane1 = new AnchorPane();
        text4 = new Text();
        text5 = new Text();
        text6 = new Text();
        ImageView = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        text.setFill(javafx.scene.paint.Color.web("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
        text.setFont(new Font("Agency FB Bold", 45.0));

        text0.setFill(javafx.scene.paint.Color.web("#04b1b8"));
        text0.setLayoutX(330.0);
        text0.setLayoutY(65.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TOE");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.web("#fd6801"));
        text1.setLayoutX(264.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TAC");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        AncorPane2.setLayoutX(60.0);
        AncorPane2.setLayoutY(260.0);
        AncorPane2.setPrefHeight(120.0);
        AncorPane2.setPrefWidth(480.0);
        AncorPane2.setStyle("-fx-background-color: #F4A24C;");

        text2.setFill(javafx.scene.paint.Color.web("#FFFFFF"));
        text2.setLayoutX(14.0);
        text2.setLayoutY(73.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("PLAYER TWO");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setLayoutX(382.0);
        text3.setLayoutY(66.0);
        text3.setFill(javafx.scene.paint.Color.web("#ffffff"));
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("score");
        text3.setFont(new Font("Agency FB Bold", 36.0));

        AncorPane1.setLayoutX(60.0);
        AncorPane1.setLayoutY(83.0);
        AncorPane1.setPrefHeight(120.0);
        AncorPane1.setPrefWidth(480.0);
        AncorPane1.setStyle("-fx-background-color: #2A9DB8;");

        text4.setFill(javafx.scene.paint.Color.web("#FFFFFF"));
        text4.setLayoutX(14.0);
        text4.setLayoutY(73.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("PLAYER ONE");
        text4.setFont(new Font("Agency FB Bold", 35.0));

        text5.setLayoutX(385.0);
        text5.setLayoutY(66.0);
        text5.setFill(javafx.scene.paint.Color.web("#FFFFFF"));
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("score");
        text5.setFont(new Font("Agency FB Bold", 36.0));

        text6.setFill(javafx.scene.paint.Color.web("#FD6801"));
        text6.setLayoutX(64.0);
        text6.setLayoutY(245.0);
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("WAITING FOR PLAYER ONE ...");
        text6.setFont(new Font("Agency FB Bold", 35.0));

        ImageView.setFitHeight(60.0);
        ImageView.setFitWidth(71.0);
        ImageView.setLayoutX(25.0);
        ImageView.setLayoutY(17.0);
        ImageView.setPickOnBounds(true);
        ImageView.setPreserveRatio(true);
        ImageView.setImage(new Image(getClass().getResource("arrow.png").toExternalForm()));
        ImageView.setOnMouseClicked(e -> navigate());

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        AncorPane2.getChildren().add(text2);
        AncorPane2.getChildren().add(text3);
        getChildren().add(AncorPane2);
        AncorPane1.getChildren().add(text4);
        AncorPane1.getChildren().add(text5);
        getChildren().add(AncorPane1);
        getChildren().add(text6);
        getChildren().add(ImageView);
    }

    public void navigate() {
        PlayerStatusBase PS = new PlayerStatusBase(stage);
        Scene PScene = new Scene(PS, 600, 400);
        stage.setScene(PScene);
    }
}
