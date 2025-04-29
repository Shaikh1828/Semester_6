
public class AuthManager {
    private UserRepository repository;

    public AuthManager(UserRepository repository) {
        this.repository = repository;
    }

    public boolean login(String email, String password) throws Exception {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }

        String hashedPassword = repository.hashPassword(password);
        return hashedPassword.equals(user.getPasswordHash());
    }
}
