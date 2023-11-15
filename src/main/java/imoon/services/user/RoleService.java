package imoon.services.user;

import imoon.entities.user.Role;
import imoon.repositories.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName(Role.USER).orElseThrow(() -> new RuntimeException("Role not found"));
    }
}