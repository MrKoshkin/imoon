package imoon.services.user;

import imoon.entities.user.MoonUser;
import imoon.repositories.user.MoonUserRepository;
import imoon.repositories.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoonUserDetailsService implements UserDetailsService {

    @Autowired
    private MoonUserRepository moonUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Optional<MoonUser> findByUsername(String username) {
        return moonUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MoonUser user = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

        // Создаем спрингового юзера
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Transactional
    public void createNewUser(MoonUser user) {
        user.setRole(List.of(roleRepository.findByName("ROLE_USER").get()).toString());
        moonUserRepository.save(user);
    }

//    @Transactional
//    public void createNewUser(MoonUser moonUser) {
//        moonUser.setRole(List.of(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new UsernameNotFoundException("Role not found"))).);
//    }
}
