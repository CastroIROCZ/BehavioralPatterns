package behavioral;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class UserTest {
    
    @Test
    public void testSentMessageHistory() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Did you take PVTO?", recipients);

        assertEquals(1, angel.getChatHistory().size());
        assertEquals("Did you take PVTO?",
                angel.getChatHistory().get(0).getContent());
    }

    @Test
    public void testReceiveMessage() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("Hop on Fortnite", recipients);

        assertEquals(1, eric.getChatHistory().size());

        Message received = eric.getChatHistory().get(0);

        assertSame(angel, received.getSender());
        assertEquals("Hop on Fortnite", received.getContent());
    }

    @Test
    public void testUndoLastMessage() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("First message", recipients);
        angel.sendMessage("Second message", recipients);

        assertEquals(2, angel.getChatHistory().size());
        assertEquals(2, eric.getChatHistory().size());

        angel.undoMessage();

        assertEquals(1, angel.getChatHistory().size());
        assertEquals(1, eric.getChatHistory().size());

        assertEquals("First message",
                eric.getChatHistory().get(0).getContent());
    }

    @Test
    public void testUndoMultipleMessages() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        server.registerUser(angel);
        server.registerUser(eric);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        angel.sendMessage("First message", recipients);
        angel.sendMessage("Second message", recipients);
        angel.sendMessage("Third message", recipients);

        angel.undoMessage();

        assertEquals(2, eric.getChatHistory().size());
        assertEquals("Second message",
                eric.getChatHistory().get(1).getContent());

        angel.undoMessage();

        assertEquals(1, eric.getChatHistory().size());
        assertEquals("First message",
                eric.getChatHistory().get(0).getContent());

        angel.undoMessage();

        assertTrue(eric.getChatHistory().isEmpty());
        assertTrue(angel.getChatHistory().isEmpty());
    }

    @Test
    public void testUndoEmptyHistory() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);

        server.registerUser(angel);

        assertDoesNotThrow(() -> angel.undoMessage());

        assertTrue(angel.getChatHistory().isEmpty());
    }

}
