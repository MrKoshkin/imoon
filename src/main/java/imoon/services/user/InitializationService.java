package imoon.services.user;

import imoon.models.user.MoonUser;
import imoon.models.user.UserGroup;
import imoon.models.user.UserRole;
import imoon.repositories.user.MoonUserRepository;
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
    private MoonUserRepository moonUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeAdminUsers() {
        initializeAdminUser(adminUsername, adminPassword);
    }

    private void initializeAdminUser(String username, String password) {
        if (!moonUserRepository.existsByUsername(username)) {
            MoonUser adminUser = new MoonUser();
            adminUser.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            adminUser.setPassword(encodedPassword);
            adminUser.setRole(UserRole.ADMIN);
            adminUser.setHidden(false);
            adminUser.setTimestamp(System.currentTimeMillis());
            adminUser.getUserGroups().addAll(Arrays.asList(UserGroup.COMMON_CAN_AUTH, UserGroup.PANEL_SEE_USER_CONTROLLER));

            moonUserRepository.save(adminUser);
        }
    }
}


