package behavioral;

import java.util.ArrayList;
import java.util.Iterator;

public class Driver{
    public static void main(String[] args) {

        ChatServer server = new ChatServer();

        User angel = new User("Angel", server);
        User eric = new User("Eric", server);
        User castro = new User("Castro", server);

        server.registerUser(angel);
        server.registerUser(eric);
        server.registerUser(castro);

        ArrayList<User> angelRecipients = new ArrayList<>();
        angelRecipients.add(eric);
        angelRecipients.add(castro);

        ArrayList<User> ericRecipients = new ArrayList<>();
        ericRecipients.add(angel);

        ArrayList<User> castroRecipients = new ArrayList<>();
        castroRecipients.add(eric);

        System.out.println("===== Chat Application =====");

        System.out.println("\n===== Sending Messages =====");

        System.out.println("Angel sends a message to Eric and Castro");
        angel.sendMessage("Did you guys take PVTO?", angelRecipients);

        System.out.println("Eric replies to Angel");
        eric.sendMessage("Of course I did bro", ericRecipients);

        System.out.println("Castro sends a message to Eric");
        castro.sendMessage("Hop on Fortnite", castroRecipients);

        System.out.println("\n===== Chat Histories =====");

        printHistory("Angel", angel);
        printHistory("Eric", eric);
        printHistory("Castro", castro);

        System.out.println("\n===== Blocking Messages =====");

        System.out.println("Eric blocks Angel");
        eric.blockUser(angel);

        System.out.println("Angel attempts to message Eric and Castro");
        angel.sendMessage("Are you guys working tomorrow?", angelRecipients);

        System.out.println("\n===== Chat Histories After Blocking =====");

        printHistory("Eric", eric);
        printHistory("Castro", castro);

        System.out.println("\n===== Undo Last Message =====");

        System.out.println("Angel undoes his last sent message");
        angel.undoMessage();

        System.out.println("\n===== Chat Histories After Undo =====");

        printHistory("Angel", angel);
        printHistory("Eric", eric);
        printHistory("Castro", castro);

        System.out.println("\n===== Unregister User =====");

        server.unregisterUser(castro);

        System.out.println("Angel attempts to message Castro");

        ArrayList<User> castroOnly = new ArrayList<>();
        castroOnly.add(castro);

        angel.sendMessage("Hello Castro", castroOnly);
        
        System.out.println("\n===== Iterator Design Pattern =====");

        System.out.println("\nSearching Angel's chat history for messages involving Eric");

        Iterator<Message> angelIterator = angel.iterator(eric);

        while (angelIterator.hasNext()) {
            Message message = angelIterator.next();
            System.out.println("Message: " + message.getContent());
            System.out.println("Timestamp: " + message.getTimeStamp());
        }

        System.out.println("\nSearching Eric's chat history for messages involving Castro");

        Iterator<Message> ericIterator = eric.iterator(castro);

        while (ericIterator.hasNext()) {
            Message message = ericIterator.next();
            System.out.println("Message: " + message.getContent());
            System.out.println("Timestamp: " + message.getTimeStamp());
        }


        System.out.println("\n===== End of Chat Application =====");
    }

    public static void printHistory(String userName, User user) {
        System.out.println("\n" + userName + "'s Chat History:");

        ArrayList<Message> messages = user.getChatHistory();

        if (messages.isEmpty()) {
            System.out.println("No messages found");
            return;
        }

        for (Message message : messages) {
            System.out.println("Message: " + message.getContent());
            System.out.println("Timestamp: " + message.getTimeStamp());
            System.out.println();
        }
    }
}