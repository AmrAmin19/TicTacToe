package tictactoe;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Boardmode1medium extends AnchorPane {

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
    private boolean playerTurn = true;
    private final Stage stage;

    public Boardmode1medium(Stage stage) {
        this.stage = stage;

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

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

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

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);

        getChildren().add(gridPane);
        getChildren().add(text);
        getChildren().add(text0);
        getChildren().add(text1);
        getChildren().add(arrow);
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

    private void computerMove() {
        if (makeWinningMove("O") || blockWinningMove("X") || takeCenter() || takeCorner() || takeSide()) {
            if (checkWin("O")) {
                alertShowO();
            } else if (isBoardFull()) {
                alertShowDraw();
            }
            playerTurn = true;
        }
    }

    private boolean makeWinningMove(String symbol) {
        return makeStrategicMove(symbol);
    }

    private boolean blockWinningMove(String symbol) {
        return makeStrategicMove(symbol);
    }

    private boolean makeStrategicMove(String symbol) {
        // Define all winning combinations
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        String[] board = getBoardState();
        int emptyIndex = -1;
        for (int[] combination : winningCombinations) {
            int count = 0;
            for (int index : combination) {
                if (board[index].equals(symbol)) {
                    count++;
                } else if (board[index].isEmpty()) {
                    emptyIndex = index;
                }
            }
            if (count == 2 && emptyIndex != -1) {
                Button emptyButton = getButtonByIndex(emptyIndex);
                if (emptyButton != null) {
                    emptyButton.setText("O");
                    emptyButton.setDisable(true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean takeCenter() {
        return takePosition(4);
    }

    private boolean takeCorner() {
        return takePosition(0) || takePosition(2) || takePosition(6) || takePosition(8);
    }

    private boolean takeSide() {
        return takePosition(1) || takePosition(3) || takePosition(5) || takePosition(7);
    }

    private boolean takePosition(int index) {
        Button button = getButtonByIndex(index);
        if (button != null && button.getText().isEmpty()) {
            button.setText("O");
            button.setDisable(true);
            return true;
        }
        return false;
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0: return btn1;
            case 1: return btn2;
            case 2: return btn3;
            case 3: return btn4;
            case 4: return btn5;
            case 5: return btn6;
            case 6: return btn7;
            case 7: return btn8;
            case 8: return btn9;
            default: return null;
        }
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

    private boolean checkWin(String symbol) {
        // Define all winning combinations
        int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        String[] board = getBoardState();
        for (int[] combination : winningCombinations) {
            if (board[combination[0]].equals(symbol) &&
                board[combination[1]].equals(symbol) &&
                board[combination[2]].equals(symbol)) {
                return true;
            }
        }
        return false;
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

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = winnerDialog.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                winnerDialog.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1medium(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                winnerDialog.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });

    }

    private void alertShowO() {
        Platform.runLater(() -> {
            // Create the winner message dialog
            losermsgmode1Base losserDialog = new losermsgmode1Base();

            // Create a modal dialog to display the winner message
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(stage);
            Scene dialogScene = new Scene(losserDialog, 500, 300);
            dialogStage.setScene(dialogScene);

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = losserDialog.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                losserDialog.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1medium(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                losserDialog.stopMediaPlayer();
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

            // Get the "PLAY AGAIN" button from the dialog
            Button playAgainButton = draw.getPlayAgainButton();
            playAgainButton.setOnAction(e -> {
                draw.stopMediaPlayer();
                dialogStage.close();
                stage.setScene(new Scene(new Boardmode1medium(stage)));
            });

            // Handle dialog stage close request
            dialogStage.setOnCloseRequest((WindowEvent we) -> {
                draw.stopMediaPlayer();
                stage.setScene(new Scene(new ChooseModeBase(stage)));
            });

            dialogStage.showAndWait(); // Show the modal dialog and wait for it to close
        });
    }

   

    protected void navigateback() {
        chooseDiffuculty diff = new chooseDiffuculty(stage);
        Scene difScene = new Scene(diff, 600, 400);
        stage.setScene(difScene);
    }
}
