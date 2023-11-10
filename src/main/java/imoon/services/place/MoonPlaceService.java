package imoon.services.place;

import imoon.entities.place.MoonPlace;
import imoon.repositories.place.MoonPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoonPlaceService {

    private final MoonPlaceRepository moonPlaceRepository;

    @Autowired
    public MoonPlaceService(MoonPlaceRepository moonPlaceRepository) {
        this.moonPlaceRepository = moonPlaceRepository;
    }

    public List<MoonPlace> getAllMoonPlaces() {
        return moonPlaceRepository.findAll();
    }

    public Optional<MoonPlace> getMoonPlaceById(Long id) {
        return moonPlaceRepository.findById(id);
    }

    public MoonPlace createMoonPlace(MoonPlace moonPlace) {
        return moonPlaceRepository.save(moonPlace);
    }

    public MoonPlace updateMoonPlace(Long id, MoonPlace updatedMoonPlace) {
        return moonPlaceRepository.findById(id)
                .map(existingMoonPlace -> {
                    // Обновляем данные места на основе updatedMoonPlace
                    existingMoonPlace.setTitle(updatedMoonPlace.getTitle());
                    existingMoonPlace.setShortDescription(updatedMoonPlace.getShortDescription());


                    return moonPlaceRepository.save(existingMoonPlace);
                })
                .orElse(null); // или бросьте исключение EntityNotFoundException
    }

    public void deleteMoonPlace(Long id) {
        moonPlaceRepository.deleteById(id);
    }
}