package imoon.repositories.user;

import imoon.models.user.MoonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoonUserRepository extends JpaRepository<MoonUser, Long> {
    MoonUser findByUsername(String username);
    boolean existsByUsername(String username);
}
