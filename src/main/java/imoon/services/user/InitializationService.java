package imoon.services.user;

import imoon.entities.user.User;
import imoon.entities.user.Role;
import imoon.entities.user.UserGroup;
import imoon.repositories.user.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitializationService {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeAdminUsers() {
        initializeAdminUser(adminUsername, adminPassword);
    }

    // Инициализация админа
    private void initializeAdminUser(String username, String password) {
        if (!userRepository.existsByUsername(username)) {
            User adminUser = new User();
            adminUser.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            adminUser.setPassword(encodedPassword);
            adminUser.setRole(Role.ADMIN);
            adminUser.setHidden(false);
            adminUser.setTimestamp(System.currentTimeMillis());
            adminUser.getUserGroups().addAll(Arrays.asList(UserGroup.COMMON_CAN_AUTH, UserGroup.PANEL_SEE_USER_CONTROLLER));

            userRepository.save(adminUser);
        }
    }
}
