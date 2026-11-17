package behavioral;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class SearchMessagesByUserTest {
    @Test
    public void testMessagesSentByUser() {
        ChatServer server = new ChatServer();
        ChatHistory history = new ChatHistory();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        ArrayList<User> ericRecipients = new ArrayList<>();
        ericRecipients.add(eric);

        ArrayList<User> castroRecipients = new ArrayList<>();
        castroRecipients.add(castro);

        Message message1 = new Message(angel, "Hello Eric", ericRecipients);
        Message message2 = new Message(castro, "Hello Angel", ericRecipients);
        Message message3 = new Message(angel, "Hello Castro", castroRecipients);

        history.addMessage(message1);
        history.addMessage(message2);
        history.addMessage(message3);

        Iterator<Message> iterator = history.iterator(angel);

        assertTrue(iterator.hasNext());
        assertSame(message1, iterator.next());

        assertTrue(iterator.hasNext());
        assertSame(message3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testMessagesReceivedByUser() {
        ChatServer server = new ChatServer();
        ChatHistory history = new ChatHistory();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        ArrayList<User> angelRecipients = new ArrayList<>();
        angelRecipients.add(angel);

        ArrayList<User> castroRecipients = new ArrayList<>();
        castroRecipients.add(castro);

        Message message1 = new Message(eric, "Hello Angel", angelRecipients);
        Message message2 = new Message(eric, "Hello Castro", castroRecipients);
        Message message3 = new Message(castro, "How are you Angel?", angelRecipients);

        history.addMessage(message1);
        history.addMessage(message2);
        history.addMessage(message3);

        Iterator<Message> iterator = history.iterator(angel);

        assertTrue(iterator.hasNext());
        assertSame(message1, iterator.next());

        assertTrue(iterator.hasNext());
        assertSame(message3, iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testMessagesSentAndReceived() {
        ChatServer server = new ChatServer();
        ChatHistory history = new ChatHistory();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        ArrayList<User> angelRecipients = new ArrayList<>();
        angelRecipients.add(angel);

        ArrayList<User> ericRecipients = new ArrayList<>();
        ericRecipients.add(eric);

        ArrayList<User> castroRecipients = new ArrayList<>();
        castroRecipients.add(castro);

        Message message1 = new Message(angel, "Hey Eric", ericRecipients);
        Message message2 = new Message(eric, "Hey Angel", angelRecipients);
        Message message3 = new Message(castro, "Hey Eric", ericRecipients);

        history.addMessage(message1);
        history.addMessage(message2);
        history.addMessage(message3);

        Iterator<Message> iterator = history.iterator(angel);

        assertSame(message1, iterator.next());
        assertSame(message2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testNoMatchingMessages() {
        ChatServer server = new ChatServer();
        ChatHistory history = new ChatHistory();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        Message message = new Message(angel, "Hello Eric", recipients);
        history.addMessage(message);

        Iterator<Message> iterator = history.iterator(castro);

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorExhaustion() {
        ChatServer server = new ChatServer();
        ChatHistory history = new ChatHistory();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);

        ArrayList<User> recipients = new ArrayList<>();
        recipients.add(eric);

        Message message = new Message(angel, "Hello Eric", recipients);
        history.addMessage(message);

        Iterator<Message> iterator = history.iterator(angel);

        assertTrue(iterator.hasNext());
        assertSame(message, iterator.next());

        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void testUserIteratorWrapper() {
        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        server.registerUser(angel);
        server.registerUser(eric);
        server.registerUser(castro);

        ArrayList<User> ericRecipients = new ArrayList<>();
        ericRecipients.add(eric);

        ArrayList<User> castroRecipients = new ArrayList<>();
        castroRecipients.add(castro);

        angel.sendMessage("Hello Eric", ericRecipients);
        angel.sendMessage("Hello Castro", castroRecipients);

        Iterator<Message> iterator = angel.iterator(eric);

        assertTrue(iterator.hasNext());

        Message result = iterator.next();

        assertEquals("Hello Eric", result.getContent());
        assertFalse(iterator.hasNext());
    }
    
}
