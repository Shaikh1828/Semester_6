
public class UserRepository {
    public User findByEmail(String email) {
        // Normally this would query a database
        return null;
    }

    public String hashPassword(String password) {
        // Simple hash example
        return Integer.toHexString(password.hashCode());
    }
}
