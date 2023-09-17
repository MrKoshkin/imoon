package imoon.repositories;

import imoon.models.user.MoonUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoonUserRepository extends CrudRepository<MoonUser, Long> {
    MoonUser findByUsername(String username);
    boolean existsByUsername(String username);
}
