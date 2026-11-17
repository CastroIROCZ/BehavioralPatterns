package behavioral;

import java.util.ArrayList;

public class ChatServer implements ChatMediator {

    private final ArrayList<User> users = new ArrayList<>();

    public void registerUser(User user) {
        this.users.add(user);
    }

    public void sendMessage(User sender, String content, ArrayList<User> recipients) {
        Message message = new Message(sender, content, recipients);
    }
    
}
