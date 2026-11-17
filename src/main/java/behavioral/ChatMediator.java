package behavioral;

import java.util.ArrayList;

public interface ChatMediator {
    
    void registerUser(User user);

    void sendMessage(User sender, String content, ArrayList<User> recipients);
}
