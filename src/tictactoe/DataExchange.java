package tictactoe;

public class DataExchange {
    private String[][] board = new String[3][3];
    private boolean isPlayerOneTurn = true;

    public DataExchange() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "";
            }
        }
    }

    public synchronized void makeMove(int x, int y, String letter) {
        board[x][y] = letter;
        isPlayerOneTurn = !isPlayerOneTurn;  // Toggle turn
    }

    public synchronized String getCell(int x, int y) {
        return board[x][y];
    }

    public synchronized boolean isPlayerOneTurn() {
        return isPlayerOneTurn;
    }
}
