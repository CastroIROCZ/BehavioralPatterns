package behavioral;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class MessageTest {
    
    @Test 
    public void testMessageCreation() {
        ChatServer server = new ChatServer();

        User Beltz = new User("Beltz", server);
        User Castro = new User("Castro", server);

        server.registerUser(Beltz);
        server.registerUser(Castro);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(Castro);

        Message message = new Message(Beltz, "Get on Minecraft", recipients);

        assertSame(Beltz, message.getSender());
        assertEquals("Get on Minecraft", message.getContent());
        assertEquals(1, message.getRecipients().size());
        assertTrue(message.getRecipients().contains(Castro));
        assertNotNull(message.getTimeStamp());
    }
}
