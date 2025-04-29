package com.example.usermanagement.application.interfaces;

import com.example.usermanagement.domain.User;
import java.util.*;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
}
