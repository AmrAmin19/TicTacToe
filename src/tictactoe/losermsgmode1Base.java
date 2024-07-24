package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class losermsgmode1Base extends DialogPane {

    protected final AnchorPane anchorPane;
    protected final Text text;
    protected final Text text0;
    protected final AnchorPane anchorPane0;
    protected final Button button;

    public losermsgmode1Base() {

        anchorPane = new AnchorPane();
        text = new Text();
        text0 = new Text();
        anchorPane0 = new AnchorPane();
        button = new Button();

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(425.0);

        text.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text.setLayoutX(67.0);
        text.setLayoutY(106.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("GOOD LUCK NEXT");
        text.setWrappingWidth(352.705078125);
        text.setFont(new Font("Agency FB Bold", 55.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text0.setLayoutX(122.0);
        text0.setLayoutY(172.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TIME"+"\uD83C\uDFCB"+"\uD83C\uDFCB");
        text0.setFont(new Font("Agency FB Bold", 55.0));
        setHeader(anchorPane);

        anchorPane0.setPrefHeight(69.0);
        anchorPane0.setPrefWidth(365.0);

        button.setLayoutX(122.0);
        button.setLayoutY(13.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(44.0);
        button.setPrefWidth(192.0);
        button.setText("TAKE YOUR REVENGE");
        button.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        button.setFont(new Font("Agency FB Bold", 20.0));
        setContent(anchorPane0);

        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(text0);
        anchorPane0.getChildren().add(button);

    }
     public Button getPlayAgainButton() {
        return button;
    }
}
