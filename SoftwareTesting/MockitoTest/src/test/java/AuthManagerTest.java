
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthManagerTest {
    private UserRepository repository;
    private AuthManager authManager;
    String email = "test@example.com";
    String password = "secret";
    String hashed = "hashed-secret";


    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(UserRepository.class);
        authManager = new AuthManager(repository);


        User mockUser = new User(email, hashed);

        when(repository.findByEmail(email)).thenReturn(mockUser);
        when(repository.hashPassword(password)).thenReturn(hashed);
    }

    @Test
    public void testLoginSuccess() throws Exception {
        assertTrue(authManager.login(email, password));
    }

    @Test
    public void testLoginWrongPassword() throws Exception {

        User mockUser = new User(email, hashed);

        when(repository.findByEmail(email)).thenReturn(mockUser);
        when(repository.hashPassword(password)).thenReturn("wrong-hash");

        assertFalse(authManager.login(email, password));
    }

    @Test
    public void testLoginUserNotFound() {

        when(repository.findByEmail(email)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> {
            authManager.login(email, "anyPassword");
        });

        assertEquals("User not found", exception.getMessage());
    }
}
