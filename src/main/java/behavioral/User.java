package behavioral;

import java.util.ArrayList;

public class User {
    private String userName;
    private final ChatHistory chatHistory = new ChatHistory();
    private final ChatServer server;

    public User(String userName, ChatServer server) {
        this.userName = userName;
        this.server = server;
    }

    public void sendMessage(String content, ArrayList<User> recipients) {
        this.server.sendMessage(this, content, recipients);

    }

    public void receiveMessage(Message message) {
        this.chatHistory.addMessage(message);
    }

    public void recordSentMessage(Message message) {
        chatHistory.addMessage(message);
    }

    public void undoMessage() {
        
    }

    public void blockUser(User blockedUser) {
        server.blockUser(this, blockedUser);
    }
}
