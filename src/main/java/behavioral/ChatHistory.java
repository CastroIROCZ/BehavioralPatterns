package behavioral;

import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private final ArrayList<Message> messages = new ArrayList<>();
    
    public ChatHistory() { 
    }

    public ArrayList<Message> getChatHistory() {
        return new ArrayList<>(this.messages);
    }

    public void addMessage(Message message) { 
        this.messages.add(message);
    }

    public Message getLastMessage(User sender) {
        for(int i = messages.size() - 1; i >= 0; --i) {
            if(messages.get(i).getSender() == sender) {
                return messages.get(i);
            }
        }
        
        return null;
    }

    public boolean removeMessage(Message message) {
        return this.messages.remove(message);
    }
    
    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new SearchMessagesByUser(this.messages, userToSearchWith);
    }
}
