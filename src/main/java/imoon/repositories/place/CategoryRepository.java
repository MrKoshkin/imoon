package imoon.repositories.place;

import imoon.models.place.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<PlaceCategory, Long> {
}

