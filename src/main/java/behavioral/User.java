package behavioral;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;

public class User implements IterableByUser {
    private String userName;
    private final ChatHistory chatHistory = new ChatHistory();
    private final ChatServer server;
    private final ArrayDeque<MessageMemento> sentMessages = new ArrayDeque<>();

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
        this.chatHistory.addMessage(message);
        this.sentMessages.push(message.saveState());
    }

    public void undoMessage() {
        if (sentMessages.isEmpty()) {
            System.out.println("No messages to undo");
            return;
        }

        MessageMemento memento = sentMessages.peek();
        Message lastMessage = chatHistory.getLastMessage(this);

        if (lastMessage != null &&
            lastMessage.getContent().equals(memento.getContent()) &&
            lastMessage.getTimeStamp().equals(memento.getTimeStamp())) {

            server.undoMessage(this, lastMessage);
            sentMessages.pop();
        }
    }

    public void blockUser(User blockedUser) {
        server.blockUser(this, blockedUser);
    }

    public void removeMessage(Message message) {
        this.chatHistory.removeMessage(message);
    }

    public ArrayList<Message> getChatHistory() {
        return chatHistory.getChatHistory();
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return this.chatHistory.iterator(userToSearchWith);
    }
}
