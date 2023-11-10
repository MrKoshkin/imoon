package imoon.repositories.user;

import imoon.entities.user.MoonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoonUserRepository extends JpaRepository<MoonUser, Long> {
    Optional<MoonUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
