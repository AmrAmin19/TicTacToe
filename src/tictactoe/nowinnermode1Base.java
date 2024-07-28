package tictactoe;

import java.io.File;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public  class nowinnermode1Base extends DialogPane {

    protected final VBox vbox;
    protected final MediaView mediaView;
    protected final Button button;
    protected MediaPlayer mediaPlayer;
  

    public nowinnermode1Base() {
        vbox = new VBox();
        mediaView = new MediaView();
        button = new Button();

        // Set up the MediaView to display the video
        mediaView.setFitHeight(200.0);
        mediaView.setFitWidth(450.0);

        // Path to the video file
        File videoFile = new File("C:\\Users\\Abdul-Rahman\\OneDrive\\Desktop\\TicTacToe\\TicTacToe\\src\\tictactoe\\draw.mp4");
        if (!videoFile.exists()) {
            System.out.println("Error: Video file does not exist at path: " + videoFile.getAbsolutePath());
            return;
        }

        // Create Media and MediaPlayer
        String videoPath = videoFile.toURI().toString();
        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        mediaPlayer.setOnReady(() -> {
            System.out.println("Media Player is ready.");
            mediaPlayer.play();  // Start playing the video
        });

        mediaPlayer.setOnError(() -> {
            System.out.println("Error: " + mediaPlayer.getError().getMessage());
        });

        // Configure the "PLAY AGAIN" button
        button.setMnemonicParsing(false);
        button.setPrefHeight(44.0);
        button.setPrefWidth(126.0);
        button.setStyle("-fx-background-color: #2A9DB8; -fx-text-fill: #ffffff;");
        button.setText("PLAY AGAIN");
        button.setFont(new Font("Agency FB Bold", 20.0));

        // Add event handler to stop mediaPlayer and transition to a new game
        button.setOnAction(event -> {
            stopMediaPlayer();  // Properly stop and dispose of the media player
           
        });

        // Add MediaView and Button to VBox
        vbox.getChildren().add(mediaView);
        vbox.getChildren().add(button);
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

    void stopMediaPlayer() {
        Platform.runLater(() -> {
            if (mediaPlayer != null) {
                try {
                    mediaPlayer.stop(); // Stop the video playback
                    System.out.println("Media player stopped");
                } catch (Exception e) {
                    System.err.println("Error while stopping and disposing media player: " + e.getMessage());
                } finally {
                    mediaPlayer = null; // Ensure that mediaPlayer is set to null
                }
            }
        });
    }

    
}
