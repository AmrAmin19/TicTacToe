package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerStatusBase extends AnchorPane {

    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final AnchorPane AncorPane2;
    protected final Text text4;
    protected final Text text5;
    protected final Text text6;
    protected final Text text7;
    protected final ImageView ImageViwe1;
    protected final ImageView ImageViwe2;
    protected final ImageView ImageViwe3;
    protected final Button Challenge1Btn;
    protected final Button Challenge2Btn;
    protected final Button Challenge3Btn;
    private final Stage stage;

    public PlayerStatusBase(Stage stage) {
        this.stage =stage ;
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        AncorPane2 = new AnchorPane();
        text4 = new Text();
        text5 = new Text();
        text6 = new Text();
        text7 = new Text();
        ImageViwe1 = new ImageView();
        ImageViwe2 = new ImageView();
        ImageViwe3 = new ImageView();
        Challenge1Btn = new Button();
        Challenge2Btn = new Button();
        Challenge3Btn = new Button();

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

        text2.setFill(javafx.scene.paint.Color.valueOf("#2A9DB8"));
        text2.setLayoutX(26.0);
        text2.setLayoutY(113.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("PLAYER NAME");
        text2.setFont(new Font("Agency FB Bold", 35.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("F4A24C"));
        text3.setLayoutX(474.0);
        text3.setLayoutY(114.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("SCORE");
        text3.setFont(new Font("Agency FB Bold", 35.0));

        AncorPane2.setLayoutX(110.0);
        AncorPane2.setLayoutY(121.0);
        AncorPane2.setPrefHeight(227.0);
        AncorPane2.setPrefWidth(366.0);
        AncorPane2.setStyle("-fx-background-color: #2A9DB8;" );

        text4.setLayoutX(41.0);
        text4.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        text4.setLayoutY(96.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("PLAYER ONE");
        text4.setFont(new Font("Agency FB Bold", 24.0));
        

        text5.setLayoutX(39.0);
        text5.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        text5.setLayoutY(146.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("PLAYER TWO");
        text5.setFont(new Font("Agency FB Bold", 24.0));
        

        text6.setLayoutX(39.0);
        text6.setLayoutY(196.0);
        text6.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("PLAYER THREE");
        text6.setFont(new Font("Agency FB Bold", 24.0));
        
        text7.setLayoutX(121.0);
        text7.setLayoutY(46.0);
        text7.setFill(javafx.scene.paint.Color.valueOf("#ffffff"));
        text7.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text7.setStrokeWidth(0.0);
        text7.setText("ONLINE PLAYER");
        text7.setFont(new Font("Agency FB Bold", 24.0));
        

        ImageViwe1.setFitHeight(35.0);
        ImageViwe1.setFitWidth(34.0);
        ImageViwe1.setLayoutX(4.0);
        ImageViwe1.setLayoutY(70.0);
        ImageViwe1.setPickOnBounds(true);
        ImageViwe1.setPreserveRatio(true);
        ImageViwe1.setImage(new Image(getClass().getResource("person.png").toExternalForm()));

        ImageViwe2.setFitHeight(34.0);
        ImageViwe2.setFitWidth(34.0);
        ImageViwe2.setLayoutX(4.0);
        ImageViwe2.setLayoutY(119.0);
        ImageViwe2.setPickOnBounds(true);
        ImageViwe2.setPreserveRatio(true);
        ImageViwe2.setImage(new Image(getClass().getResource("person.png").toExternalForm()));

        ImageViwe3.setFitHeight(34.0);
        ImageViwe3.setFitWidth(34.0);
        ImageViwe3.setLayoutX(4.0);
        ImageViwe3.setLayoutY(170.0);
        ImageViwe3.setPickOnBounds(true);
        ImageViwe3.setPreserveRatio(true);
        ImageViwe3.setImage(new Image(getClass().getResource("person.png").toExternalForm()));

        Challenge1Btn.setLayoutX(264.0);
        Challenge1Btn.setLayoutY(70.0);
        Challenge1Btn.setMnemonicParsing(false);
        Challenge1Btn.setText("challenge");
        Challenge1Btn.setFont(new Font("Agency FB Bold", 18.0));
        Challenge1Btn.setStyle("-fx-background-color: #F4A24C; -fx-text-fill: #ffffff;");

        Challenge2Btn.setLayoutX(264.0);
        Challenge2Btn.setLayoutY(120.0);
        Challenge2Btn.setMnemonicParsing(false);
        Challenge2Btn.setText("challenge");
        Challenge2Btn.setFont(new Font("Agency FB Bold", 18.0));
        Challenge2Btn.setStyle("-fx-background-color: #F4A24C; -fx-text-fill: #ffffff;");

        Challenge3Btn.setLayoutX(264.0);
        Challenge3Btn.setLayoutY(170.0);
        Challenge3Btn.setMnemonicParsing(false);
        Challenge3Btn.setText("challenge");
        Challenge3Btn.setFont(new Font("Agency FB Bold", 18.0));
        Challenge3Btn.setStyle("-fx-background-color: #F4A24C; -fx-text-fill: #ffffff;");

        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(text2);
        getChildren().add(text3);
        AncorPane2.getChildren().add(text4);
        AncorPane2.getChildren().add(text5);
        AncorPane2.getChildren().add(text6);
        AncorPane2.getChildren().add(text7);
        AncorPane2.getChildren().add(ImageViwe1);
        AncorPane2.getChildren().add(ImageViwe2);
        AncorPane2.getChildren().add(ImageViwe3);
        AncorPane2.getChildren().add(Challenge1Btn);
        AncorPane2.getChildren().add(Challenge2Btn);
        AncorPane2.getChildren().add(Challenge3Btn);
        getChildren().add(AncorPane2);

    }
}
