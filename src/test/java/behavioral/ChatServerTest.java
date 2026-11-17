package behavioral;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;


public class ChatServerTest {
    @Test
    public void testRegisterUser() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Hop on Fortnite", recipients);

        assertEquals(1, eric.getChatHistory().size());
        assertEquals("Hop on Fortnite",
                eric.getChatHistory().get(0).getContent());
    }

    @Test
    public void testUnregisterUser() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        server.unregisterUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Are you working today?", recipients);

        assertTrue(eric.getChatHistory().isEmpty());
    }

    @Test
    public void testSingleRecipient() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Did you take PVTO?", recipients);

        assertEquals(1, eric.getChatHistory().size());
        assertSame(angel,
                eric.getChatHistory().get(0).getSender());
        assertEquals("Did you take PVTO?",
                eric.getChatHistory().get(0).getContent());
    }

    @Test
    public void testMultipleRecipients() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        server.registerUser(angel);
        server.registerUser(eric);
        server.registerUser(castro);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);
        recipients.add(castro);

        angel.sendMessage("Hop on Fortnite", recipients);

        assertEquals(1, eric.getChatHistory().size());
        assertEquals(1, castro.getChatHistory().size());

        assertSame(eric.getChatHistory().get(0),
                castro.getChatHistory().get(0));
    }

    @Test
    public void testUnregisteredSender() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Hello Eric", recipients);

        assertTrue(eric.getChatHistory().isEmpty());
        assertTrue(angel.getChatHistory().isEmpty());
    }

    @Test
    public void testBlockedUser() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        server.registerUser(angel);
        server.registerUser(eric);
        server.registerUser(castro);

        eric.blockUser(angel);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);
        recipients.add(castro);

        angel.sendMessage("Hop on Fortnite", recipients);

        assertTrue(eric.getChatHistory().isEmpty());
        assertEquals(1, castro.getChatHistory().size());

        assertEquals("Hop on Fortnite",
                castro.getChatHistory().get(0).getContent());
    }

}