package tictactoe;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;

public class OnlineBoard extends AnchorPane implements Runnable {

    private final GridPane gridPane;
    private final Button btn1;
    private final Button btn2;
    private final Button btn3;
    private final Button btn4;
    private final Button btn5;
    private final Button btn6;
    private final Button btn7;
    private final Button btn8;
    private final Button btn9;
    private final Text text;
    private final Text text0;
    private final Text text1;
    private final ImageView arrow;
    private boolean playerTurn = false; // True for X's turn, False for O's turn
    private final Stage stage;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Thread listenerThread;
    private String playerSymbol; // X or O
    private String opponentSymbol; // O or X

    public OnlineBoard(Stage stage, String serverAddress, String playerSymbol) throws IOException {
        this.stage = stage;
        this.playerSymbol = playerSymbol;
        this.opponentSymbol = playerSymbol.equals("X") ? "O" : "X";

        // Connect to the server
        socket = new Socket("172.16.8.202", 9081); // Use the serverAddress parameter
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

        // Send player signup information
        sendPlayerSignup();

        gridPane = new GridPane();
        btn1 = new Button();
        btn2 = new Button();
        btn3 = new Button();
        btn4 = new Button();
        btn5 = new Button();
        btn6 = new Button();
        btn7 = new Button();
        btn8 = new Button();
        btn9 = new Button();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        arrow = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        gridPane.setHgap(4.0);
        gridPane.setLayoutX(164.0);
        gridPane.setLayoutY(147.0);
        gridPane.setPrefHeight(184.0);
        gridPane.setPrefWidth(273.0);
        gridPane.setVgap(4.0);

        initializeButton(btn1, 0, 0);
        initializeButton(btn2, 1, 0);
        initializeButton(btn3, 2, 0);
        initializeButton(btn4, 0, 1);
        initializeButton(btn5, 1, 1);
        initializeButton(btn6, 2, 1);
        initializeButton(btn7, 0, 2);
        initializeButton(btn8, 1, 2);
        initializeButton(btn9, 2, 2);

        text.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
        text.setWrappingWidth(50.1365966796875);
        text.setFont(new Font("Agency FB Bold", 45.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#fd6801"));
        text0.setLayoutX(264.0);
        text0.setLayoutY(65.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("TAC");
        text0.setFont(new Font("Agency FB Bold", 45.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text1.setLayoutX(334.0);
        text1.setLayoutY(65.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("TOE");
        text1.setFont(new Font("Agency FB Bold", 45.0));

        arrow.setAccessibleRole(javafx.scene.AccessibleRole.BUTTON);
        arrow.setFitHeight(42.0);
        arrow.setFitWidth(54.0);
        arrow.setLayoutX(14.0);
        arrow.setLayoutY(20.0);
        arrow.setPickOnBounds(true);
        arrow.setPreserveRatio(true);
        arrow.setImage(new Image(getClass().getResource("arrow2.png").toExternalForm()));
        setPadding(new Insets(10.0));
        arrow.setOnMouseClicked(e -> navigateback());

        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(arrow);

        // Start the listener thread
        listenerThread = new Thread(this);
        listenerThread.setDaemon(true); // Ensures thread terminates when application exits
        listenerThread.start();
    }

    private void initializeButton(Button button, int col, int row) {
        button.setMnemonicParsing(false);
        button.setPrefSize(100, 100);
        button.setText("");
        button.setStyle("-fx-background-color: #8DBFBC; -fx-text-fill: #000000;");
        button.setFont(new Font("Arial", 30));
        button.setOnAction(e -> handleButtonClick(button));
        GridPane.setColumnIndex(button, col);
        GridPane.setRowIndex(button, row);
        gridPane.getChildren().add(button);
    }

    private void handleButtonClick(Button button) {
        if (playerTurn && button.getText().isEmpty()) {
            // Player makes a move
            String currentPlayer = playerSymbol;
            button.setText(currentPlayer);
            button.setDisable(true);
            sendMoveToServer(button, currentPlayer);
            playerTurn = false; // Switch turn

            // Check for win or draw
            if (checkWin(currentPlayer)) {
                Platform.runLater(() -> {
                    if (currentPlayer.equals("X")) {
                        alertShowX(); // Player X wins
                    } else {
                        alertShowX(); // Player O wins
                    }
                });
            } else if (isBoardFull()) {
                Platform.runLater(this::alertShowDraw); // Game is a draw
            }
        }
    }

    private void sendMoveToServer(Button button, String player) {
        try {
            int index = getButtonIndex(button);
            JSONObject move = new JSONObject();
            move.put("query", "MOVE");
            move.put("index", index);
            move.put("player", player);
            out.println(move.toString());
        } catch (JSONException e) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void sendPlayerSignup() {
        try {
            JSONObject signup = new JSONObject();
            signup.put("query", "SIGNUP");
            signup.put("symbol", playerSymbol);
            out.println(signup.toString());
        } catch (JSONException e) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private int getButtonIndex(Button button) {
        if (button == btn1) {
            return 0;
        }
        if (button == btn2) {
            return 1;
        }
        if (button == btn3) {
            return 2;
        }
        if (button == btn4) {
            return 3;
        }
        if (button == btn5) {
            return 4;
        }
        if (button == btn6) {
            return 5;
        }
        if (button == btn7) {
            return 6;
        }
        if (button == btn8) {
            return 7;
        }
        if (button == btn9) {
            return 8;
        }
        return -1;
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0:
                return btn1;
            case 1:
                return btn2;
            case 2:
                return btn3;
            case 3:
                return btn4;
            case 4:
                return btn5;
            case 5:
                return btn6;
            case 6:
                return btn7;
            case 7:
                return btn8;
            case 8:
                return btn9;
            default:
                return null;
        }
    }

    private void makeMove(int index, String player) {
        Button button = getButtonByIndex(index);
        if (button != null) {
            Platform.runLater(() -> {
                button.setText(player);
                button.setDisable(true);

                if (player.equals(opponentSymbol)) {
                    playerTurn = true;
                    if (checkWin(opponentSymbol)) {
                        alertShowO();
                    } else if (isBoardFull()) {
                        alertShowDraw();
                    }
                }
            });
        }
    }

    private boolean checkWin(String symbol) {
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        String[] board = getBoardState();
        for (int[] combination : winningCombinations) {
            if (board[combination[0]].equals(symbol)
                    && board[combination[1]].equals(symbol)
                    && board[combination[2]].equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    private String[] getBoardState() {
        return new String[]{
            btn1.getText(), btn2.getText(), btn3.getText(),
            btn4.getText(), btn5.getText(), btn6.getText(),
            btn7.getText(), btn8.getText(), btn9.getText()
        };
    }

    private boolean isBoardFull() {
        for (String cell : getBoardState()) {
            if (cell.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void alertShowO() {
        Platform.runLater(() -> {
            // Create the loser message dialog
            losermsgmode1Base loseDialog = new losermsgmode1Base();

            // Create a modal dialog to display the loser message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(loseDialog, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "Take Your Revenge" button from the dialog
            Button takeRevengeButton = loseDialog.getPlayAgainButton();
            takeRevengeButton.setOnAction(e -> {
                loseDialog.stopMediaPlayer();
                dialogStage.close();
                // Close the current game and start a new one
                restartGame();
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                loseDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

    private void alertShowX() {
        Platform.runLater(() -> {
            // Create the winner message dialog
            winnermsgmode1Base winnerDialog = new winnermsgmode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(winnerDialog, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "Play Again" button from the dialog
            Button playAgainButton = winnerDialog.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                winnerDialog.stopMediaPlayer();
                dialogStage.close();
                // Close the current game and start a new one
                restartGame();
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                winnerDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

    private void alertShowDraw() {
       Platform.runLater(() -> {
            // Create the winner message dialog
           nowinnermode1Base draw = new nowinnermode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(draw, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "Play Again" button from the dialog
            Button playAgainButton = draw.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                draw.stopMediaPlayer();
                dialogStage.close();
                // Close the current game and start a new one
                restartGame();
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                draw.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

    private void restartGame() {
    Platform.runLater(() -> {
        try {
            // Replace the current scene with a new instance of OnlineBoard
            stage.setScene(new Scene(new OnlineBoard(stage, "192.168.1.11", playerSymbol))); // Pass appropriate parameters
        } catch (IOException ex) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
}


    private void navigateback() {
        // Implement your navigation logic here
    }

    private void closeResources() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (in.hasNext()) {
                    String response = in.nextLine();
                    JSONObject json = new JSONObject(response);
                    String query = json.getString("query");

                    switch (query) {
                        case "MOVE":
                            int index = json.getInt("index");
                            String opponent = json.getString("player");
                            makeMove(index, opponent);
                            playerTurn = true; // It's now the player's turn
                            break;

                        case "YOUR_TURN":
                            playerTurn = true;
                            break;

                        case "SYMBOL":
                            playerSymbol = json.getString("symbol");
                            opponentSymbol = playerSymbol.equals("X") ? "O" : "X";
                            break;

                        case "Game Started":
                            // Handle game start logic if needed
                            break;

                        default:
                            // Handle unknown queries
                            break;
                    }
                }
            } catch (JSONException e) {
                Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, e);
                break;
            }
        }
        closeResources();
    }
}
