package behavioral;

import java.util.ArrayList;

public class User {
    private String userName;
    private ArrayList<String> blockedUsers;

    public User(String userName) {
        this.userName = userName;
        this.blockedUsers = new ArrayList<>();
    }

    public void blockUser(String userName) {
        if (!blockedUsers.contains(userName)) { 
            blockedUsers.add(userName);
        }
    }

    public boolean isBlocked(String userName) {
        return blockedUsers.contains(userName);
    }

    public void sendMessage(String message) {
        
    }

    public void recieveMessage(String message) {

    }

    public void undoMessage() {

    }
}
