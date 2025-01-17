package tictactoe;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardMode1Difficult extends AnchorPane {

    // GUI components
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button btn1;
    protected final Button btn2;
    protected final Button btn3;
    protected final Button btn4;
    protected final Button btn5;
    protected final Button btn6;
    protected final Button btn7;
    protected final Button btn8;
    protected final Button btn9;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final ImageView arrow;
    Alert alert;
    private final Stage stage; // Reference to the application stage
    private boolean playerTurn = true; // Track player's turn

    // Constructor
    public BoardMode1Difficult(Stage stage) {
        this.stage = stage;

        // Initialize GUI components
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
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
        alert = new Alert(AlertType.NONE);

        // Set dimensions of the board
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        // Set layout of the grid
        gridPane.setHgap(4.0);
        gridPane.setLayoutX(164.0);
        gridPane.setLayoutY(147.0);
        gridPane.setPrefHeight(184.0);
        gridPane.setPrefWidth(273.0);
        gridPane.setVgap(4.0);

        // Set column constraints
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        // Set row constraints
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        // Initialize buttons and add them to the grid
        initializeButton(btn1, 0, 0);
        initializeButton(btn2, 1, 0);
        initializeButton(btn3, 2, 0);
        initializeButton(btn4, 0, 1);
        initializeButton(btn5, 1, 1);
        initializeButton(btn6, 2, 1);
        initializeButton(btn7, 0, 2);
        initializeButton(btn8, 1, 2);
        initializeButton(btn9, 2, 2);

        // Set text properties
        text.setFill(javafx.scene.paint.Color.valueOf("#04b1b8"));
        text.setLayoutX(206.0);
        text.setLayoutY(65.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("TIC");
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

        // Set padding for the board
        setPadding(new Insets(10.0));

        // Add column and row constraints to the grid
        gridPane.getColumnConstraints().addAll(columnConstraints, columnConstraints0, columnConstraints1);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints0, rowConstraints1);

        // Add all GUI components to the main pane
        getChildren().addAll(gridPane, text, text0, text1, arrow);
    }

    // Initialize a button with its properties and add it to the grid
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

    // Handle button click event
    private void handleButtonClick(Button button) {
        button.setDisable(true);
        button.setText("X");
        playerTurn = false;
        if (checkWin("X")) {
            alertShowX();
        } else if (isBoardFull()) {
            alertShowDraw();
        } else {
            // Computer makes a move after 1 second
            new Thread(() -> {
                try {
                    Thread.sleep(500); // 1 second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(this::computerMove);
            }).start();
        }
    }

    // Computer makes a move
    private void computerMove() {
        Button[][] board = {
            {btn1, btn2, btn3},
            {btn4, btn5, btn6},
            {btn7, btn8, btn9}
        };
        Move bestMove = findBestMove(); // Find the best move
        Platform.runLater(() -> {
            Button buttonToDisable = board[bestMove.row][bestMove.col];
            buttonToDisable.setText("O"); // Set computer move
            buttonToDisable.setDisable(true); // Disable the button
            // Check if the computer won after making the move
            if (checkWin("O")) {
                alertShowO(); // Show the win alert
            } else if (isBoardFull()) {
                alertShowDraw(); // Show the draw alert
            } else {
                playerTurn = true; // Switch to player's turn
            }
        });
    }

    // Find the best move for the computer using Minimax algorithm
    private Move findBestMove() {
        Button[][] board = {
            {btn1, btn2, btn3},
            {btn4, btn5, btn6},
            {btn7, btn8, btn9}
        };
        int bestVal = Integer.MIN_VALUE; // Initialize the best value
        Move bestMove = new Move(-1, -1); // Initialize the best move
        for (int i = 0; i < 3; i++) { // Iterate through rows
            for (int j = 0; j < 3; j++) { // Iterate through columns
                if (board[i][j].getText().isEmpty()) { // Check if the cell is empty
                    board[i][j].setText("O"); // Make the move
                    int moveVal = minimax(board, 0, false); // Compute evaluation function for this move
                    board[i][j].setText(""); // Undo the move
                    if (moveVal > bestVal) { // If the value of the current move is better than the best value
                        bestMove.row = i; // Update the best move
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove; // Return the best move
    }

    // Minimax algorithm
    private int minimax(Button[][] board, int depth, boolean isMax) {
        if (checkWin("O")) {
            return 10 - depth; // Return score if computer wins
        }
        if (checkWin("X")) {
            return depth - 10; // Return score if player wins
        }
        if (isBoardFull()) {
            return 0; // Return score if it's a draw
        }
        int best;

        if (isMax) { // Maximizing player (computer)
            best = Integer.MIN_VALUE; // Initialize best value
            for (int i = 0; i < 3; i++) { // Iterate through rows
                for (int j = 0; j < 3; j++) { // Iterate through columns
                    if (board[i][j].getText().isEmpty()) { // Check if the cell is empty
                        board[i][j].setText("O"); // Make the move
                        best = Math.max(best, minimax(board, depth + 1, false)); // Call minimax recursively
                        board[i][j].setText(""); // Undo the move
                    }
                }
            }
        } else { // Minimizing player (human)
            best = Integer.MAX_VALUE; // Initialize best value
            for (int i = 0; i < 3; i++) { // Iterate through rows
                for (int j = 0; j < 3; j++) { // Iterate through columns
                    if (board[i][j].getText().isEmpty()) { // Check if the cell is empty
                        board[i][j].setText("X"); // Make the move
                        best = Math.min(best, minimax(board, depth + 1, true)); // Call minimax recursively
                        board[i][j].setText(""); // Undo the move
                    }
                }
            }
        }
        return best; // Return the best value
    }

    // Check if a player has won
    private boolean checkWin(String symbol) {
        Button[][] combinations = {
            {btn1, btn2, btn3},
            {btn4, btn5, btn6},
            {btn7, btn8, btn9},
            {btn1, btn4, btn7},
            {btn2, btn5, btn8},
            {btn3, btn6, btn9},
            {btn1, btn5, btn9},
            {btn3, btn5, btn7},};

        for (Button[] line : combinations) { // Check all possible winning combinations
            if (line[0].getText().equals(symbol) && line[1].getText().equals(symbol) && line[2].getText().equals(symbol)) {
                return true; // Return true if a winning combination is found
            }
        }
        return false; // Return false if no winning combination is found
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            if (button.getText().isEmpty()) {
                return false; // Return false if any cell is empty
            }
        }
        return true; // Return true if all cells are filled
    }

    // Show an alert with the given message
    public void alertShowO() {
        alert.setTitle("Player Two win");
        alert.setHeaderText(null);
        losermsgmode1Base customDialogPane = new losermsgmode1Base();
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setOnCloseRequest(event -> {
            navigateback();
            alertStage.close();
        });
        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDialogPane);
        customDialogPane.getPlayAgainButton().setOnAction(e -> {
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();
    }

    public void alertShowX() {
        alert.setTitle("Player  Win");
        alert.setHeaderText(null);
        winnermsgmode1Base customDialogPane = new winnermsgmode1Base();
        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDialogPane);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setOnCloseRequest(event -> {
            navigateback();
            alertStage.close();
        });
        customDialogPane.getPlayAgainButton().setOnAction(e -> {
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();
    }

   public void alertShowDraw() {
        alert.setTitle("Draw");
        alert.setHeaderText(null);
        nowinnermode1Base customDrawPane = new nowinnermode1Base();
        // Set the custom dialog pane as the content of the alert
        alert.setDialogPane(customDrawPane);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setOnCloseRequest(event -> {
            navigateback();
            alertStage.close();
        });
        customDrawPane.getPlayAgainButton().setOnAction(e -> {
            playAgain();
            alertStage.close();
        });
        alertStage.showAndWait();
    }

    public void playAgain() {
        resetBoard();  // Ensure the board is reset
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
    }

    // Reset the board
    private void resetBoard() {
        for (Button button : new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            button.setText(""); // Clear text on all buttons
        }
        playerTurn = true; // Set player's turn to true
    }

    // Class representing a move with row and column
    private static class Move {

        int row, col;

        Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public void navigateback() {
        chooseDiffuculty diff = new chooseDiffuculty(stage);
        Scene difScene = new Scene(diff, 600, 400);
        stage.setScene(difScene);
    }
}
