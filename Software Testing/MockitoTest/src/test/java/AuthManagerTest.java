
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthManagerTest {
    private UserRepository repository;
    private AuthManager authManager;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UserRepository.class);
        authManager = new AuthManager(repository);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String email = "test@example.com";
        String password = "secret";
        String hashed = "hashed-secret";

        User mockUser = new User(email, hashed);

        when(repository.findByEmail(email)).thenReturn(mockUser);
        when(repository.hashPassword(password)).thenReturn(hashed);

        assertTrue(authManager.login(email, password));
    }

    @Test
    public void testLoginWrongPassword() throws Exception {
        String email = "test@example.com";
        String password = "wrongpassword";
        String hashed = "correct-hash";

        User mockUser = new User(email, hashed);

        when(repository.findByEmail(email)).thenReturn(mockUser);
        when(repository.hashPassword(password)).thenReturn("wrong-hash");

        assertFalse(authManager.login(email, password));
    }

    @Test
    public void testLoginUserNotFound() {
        String email = "notfound@example.com";

        when(repository.findByEmail(email)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            authManager.login(email, "anyPassword");
        });

        assertEquals("User not found", exception.getMessage());
    }
}
