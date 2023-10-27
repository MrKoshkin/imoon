package imoon.services.user;

import imoon.models.user.MoonUser;
import imoon.repositories.user.MoonUserRepository;
import imoon.repositories.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoonUserDetailsService implements UserDetailsService {

    @Autowired
    private MoonUserRepository moonUserRepository;
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MoonUser user = moonUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Создаем список прав доступа (ролей) пользователя
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Transactional
    public void createNewUser(MoonUser moonUser) {
        moonUser.setRole(List.of(roleRepository.findByName("ROLE_USER").get()).toString());
    }
}
