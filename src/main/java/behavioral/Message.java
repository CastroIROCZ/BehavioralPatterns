package behavioral;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    private final User sender;
    private final ArrayList<User> recipients;
    private final LocalDateTime timeStamp;
    private final String content;

    public Message(User sender, String content, ArrayList<User> recipients) {
        this.sender = sender;
        this.content = content;
        this.recipients = new ArrayList<>(recipients);
        this.timeStamp = LocalDateTime.now();

    }

    public User getSender() {
        return this.sender;
    }

    public String getContent() {
        return this.content;
    }

    public ArrayList<User> getRecipients() {
        return new ArrayList<>(this.recipients);
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public MessageMemento saveState() {
        return new MessageMemento(this.content, this.timeStamp);
    }


}
