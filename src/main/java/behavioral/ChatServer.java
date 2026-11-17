package behavioral;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class ChatServer implements ChatMediator {

    private final ArrayList<User> users = new ArrayList<>();
    private final Map<User, Set<User>> blockedUsers = new HashMap<>();

    public void registerUser(User user) {
        if (!users.contains(user)) {
            this.users.add(user);
        }
        else {
            System.out.println("This user already exists");
        }
    }

    public void unregisterUser(User user) {
        this.users.remove(user);
    }

    public void blockUser(User blocker, User blockedUser) {
        if(users.contains(blocker) && users.contains(blockedUser)) {
            blockedUsers.computeIfAbsent(blocker, key -> new HashSet<>()).add(blockedUser);
        }
        else {
            System.out.println("Both users must be registered to block");
        }
    }

    public void sendMessage(User sender, String content, ArrayList<User> recipients) {
        if (users.contains(sender)) {
            Message message = new Message(sender, content, recipients);
            boolean delivered = false;

            for (User recipient : recipients) {
                if (!users.contains(recipient)) {
                    System.out.println("Recipient is not registered");
                }
                else if (blockedUsers.getOrDefault(recipient, Set.of()).contains(sender)) {
                    System.out.println("Message blocked by recipient");
                }
                else {
                    recipient.receiveMessage(message);
                    delivered =  true;
                }
            }

            if (delivered) { 
                sender.recordSentMessage(message);
            }
        }
        else {
            System.out.println("Must register user first");
        }
    }

    public void undoMessage(User sender, Message message) {
        if (!users.contains(sender) || message.getSender() != sender) {
            return;
        }

        sender.removeMessage(message);

        for (User recipient : message.getRecipients()) {
            recipient.removeMessage(message);
        }
    }
    
}
