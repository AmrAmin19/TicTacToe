package tictactoe;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class OnlineBoard extends AnchorPane {

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
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public OnlineBoard(Stage stage, String serverAddress) throws IOException {
        this.stage = stage;

        // Connect to the server
        socket = new Socket("172.16.8.202", 9081);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);

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

        // Start listening for messages from the server
        new Thread(this::listenForServerMessages).start();
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
        if (!playerTurn) {
            return; // Ignore the move if it's not this player's turn
        }

        button.setDisable(true);
        button.setText("X");
        playerTurn = false;
        sendMoveToServer(button);

        if (checkWin("X")) {
            alertShowX();
        } else if (isBoardFull()) {
            alertShowDraw();
        }
    }

    private void sendMoveToServer(Button button) {
        try {
            int index = getButtonIndex(button);
            JSONObject move = new JSONObject();
            move.put("query", "MOVE");
            move.put("index", index);
            move.put("player", "X");
            out.println(move.toString()); // Send the move to the server
        } catch (JSONException ex) {
            Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getButtonIndex(Button button) {
        if (button == btn1) return 0;
        if (button == btn2) return 1;
        if (button == btn3) return 2;
        if (button == btn4) return 3;
        if (button == btn5) return 4;
        if (button == btn6) return 5;
        if (button == btn7) return 6;
        if (button == btn8) return 7;
        if (button == btn9) return 8;
        return -1;
    }

    private void listenForServerMessages() {
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println("Received from server: " + line); // Debugging print statement
            if (line != null) {
                try {
                    JSONObject json = new JSONObject(line);
                    String query = json.optString("query");
                    switch (query) {
                        case "MOVE":
                            int index = json.getInt("index");
                            String player = json.getString("player");
                            Platform.runLater(() -> makeMove(index, player));
                            break;
                        case "VICTORY":
                            Platform.runLater(this::alertShowO);
                            break;
                        case "DEFEAT":
                            Platform.runLater(this::alertShowX);
                            break;
                        case "TIE":
                            Platform.runLater(this::alertShowDraw);
                            break;
                        default:
                            System.out.println("Unknown query received");
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(OnlineBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void makeMove(int index, String player) {
        Button button = getButtonByIndex(index);
        if (button != null) {
            button.setText(player);
            button.setDisable(true);

            if (player.equals("O")) {
                playerTurn = true;
                if (checkWin("O")) {
                    alertShowO();
                } else if (isBoardFull()) {
                    alertShowDraw();
                }
            }
        }
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

   private boolean checkWin(String player) {
    // Check rows
    if (btn1.getText().equals(player) && btn2.getText().equals(player) && btn3.getText().equals(player)) return true;
    if (btn4.getText().equals(player) && btn5.getText().equals(player) && btn6.getText().equals(player)) return true;
    if (btn7.getText().equals(player) && btn8.getText().equals(player) && btn9.getText().equals(player)) return true;

    // Check columns
    if (btn1.getText().equals(player) && btn4.getText().equals(player) && btn7.getText().equals(player)) return true;
    if (btn2.getText().equals(player) && btn5.getText().equals(player) && btn8.getText().equals(player)) return true;
    if (btn3.getText().equals(player) && btn6.getText().equals(player) && btn9.getText().equals(player)) return true;

    // Check diagonals
    if (btn1.getText().equals(player) && btn5.getText().equals(player) && btn9.getText().equals(player)) return true;
    if (btn3.getText().equals(player) && btn5.getText().equals(player) && btn7.getText().equals(player)) return true;

    return false;
}

private boolean isBoardFull() {
    return !btn1.getText().isEmpty() &&
           !btn2.getText().isEmpty() &&
           !btn3.getText().isEmpty() &&
           !btn4.getText().isEmpty() &&
           !btn5.getText().isEmpty() &&
           !btn6.getText().isEmpty() &&
           !btn7.getText().isEmpty() &&
           !btn8.getText().isEmpty() &&
           !btn9.getText().isEmpty();
}


    private void alertShowX() {
        // Implement alert for X win
    }

    private void alertShowO() {
        // Implement alert for O win
    }

    private void alertShowDraw() {
        // Implement alert for draw
    }

    private void navigateback() {
        // Implement navigation back
    }
}
