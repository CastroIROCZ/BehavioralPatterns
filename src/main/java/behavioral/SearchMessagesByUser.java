package behavioral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SearchMessagesByUser implements Iterator<Message> {
    private final ArrayList<Message> messages;
    private final User userToSearchWith;
    private int currentIndex;

    public SearchMessagesByUser(ArrayList<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        for (int i = currentIndex; i < messages.size(); i++) {
            Message message = messages.get(i);

            if (message.getSender() == userToSearchWith ||
                message.getRecipients().contains(userToSearchWith)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Message next() {
        while (currentIndex < messages.size()) {
            Message message = messages.get(currentIndex);
            currentIndex++;

            if (message.getSender() == userToSearchWith ||
                message.getRecipients().contains(userToSearchWith)) {
                return message;
            }
        }

        throw new NoSuchElementException("No more matching messages");
    }
}
