package behavioral;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ChatHistoryTest {

    @Test 
    public void testChatHistoryLast() { 
        ChatServer server = new ChatServer();
        ChatHistory chatHistory = new ChatHistory();

        User Angel = new User("Angel", server);
        User Bry = new User("Eric", server);

        server.registerUser(Angel);
        server.registerUser(Bry);

        ArrayList<User> angelRecipients = new ArrayList<>();
        angelRecipients.add(Bry);

        ArrayList<User> bryRecipients = new ArrayList<>();
        bryRecipients.add(Angel);

        Message message = new Message(Angel, "Did you take PVTO", angelRecipients);
        chatHistory.addMessage(message);

        Message message2 = new Message(Bry, "Of course I did bro", bryRecipients);
        chatHistory.addMessage(message2);

        Message lastMessage = chatHistory.getLastMessage(Angel);

        assertNotNull(lastMessage);
        assertSame(message, lastMessage);
        assertEquals("Did you take PVTO", lastMessage.getContent());



    }
    
}
