package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionManager {
    private static ConnectionManager instance;
    private Socket socket;
    private BufferedReader br;
    private PrintStream ps;
    private final List<MessageListener> listeners = new ArrayList<>();

    private ConnectionManager() {
        try {
            socket = new Socket("10.10.13.93", 9081);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            new Thread(() -> listenForMessages()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void sendMessage(JSONObject message) {
        ps.println(message.toString());
    }

    public void addMessageListener(MessageListener listener) {
        listeners.add(listener);
    }

    private void listenForMessages() {
        while (true) {
            try {
                String message = br.readLine();
                System.out.println("Received message: " + message); // Debugging statement
                JSONObject json = new JSONObject(message);

                // Notify all listeners about the new message
                for (MessageListener listener : listeners) {
                    listener.onMessageReceived(json);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public interface MessageListener {
        void onMessageReceived(JSONObject message);
    }
}
