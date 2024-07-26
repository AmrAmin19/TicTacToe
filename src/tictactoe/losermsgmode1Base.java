package tictactoe;

import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import java.io.File;

public class losermsgmode1Base extends DialogPane {

    protected final VBox vbox;
    protected final Button button;
    private final MediaView mediaView;

    public losermsgmode1Base() {
        vbox = new VBox();
        button = new Button();
        mediaView = new MediaView();

        // Correctly format the path to the video
        File videoFile = new File("D:\\ITI Android\\TicTacToe\\src\\tictactoe\\lossing.mp4");
        if (!videoFile.exists()) {
            System.out.println("Error: Video file does not exist at path: " + videoFile.getAbsolutePath());
            return;
        }

        String videoPath = videoFile.toURI().toString();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.setFitHeight(200.0);
        mediaView.setFitWidth(425.0);

        mediaPlayer.setOnReady(() -> {
            System.out.println("Media Player is ready.");
            mediaPlayer.play();  // Start playing the video
        });

        mediaPlayer.setOnError(() -> {
            System.out.println("Error: " + mediaPlayer.getError().getMessage());
        });

        // Configure the button
        button.setMnemonicParsing(false);
        button.setPrefHeight(44.0);
        button.setPrefWidth(192.0);
        button.setText("TAKE YOUR REVENGE");
        button.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        button.setFont(new Font("Agency FB Bold", 20.0));

        // Add MediaView and Button to VBox
        vbox.getChildren().addAll(mediaView, button);
        vbox.setSpacing(10); // Space between video and button
        vbox.setAlignment(Pos.CENTER); // Center align VBox content

        // Center VBox in StackPane
        StackPane stackPane = new StackPane(vbox);
        stackPane.setAlignment(Pos.CENTER);

        // Set header and content
        setHeader(new AnchorPane()); // Optional, if you don't need a header
        setContent(stackPane);
    }

    public Button getPlayAgainButton() {
        return button;
    }
}
