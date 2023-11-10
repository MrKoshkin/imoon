package imoon.repositories.place;

import imoon.entities.place.MoonPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoonPlaceRepository extends JpaRepository<MoonPlace, Long> {
}
