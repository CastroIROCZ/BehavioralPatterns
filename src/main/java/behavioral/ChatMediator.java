package behavioral;

public interface ChatMediator {
    
    void registerUser(User user);

    void sendMessage(String message, User sender);
}
