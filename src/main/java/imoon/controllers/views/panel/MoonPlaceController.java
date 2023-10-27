package imoon.controllers.views.panel;

import imoon.models.place.MoonPlace;
import imoon.services.place.MoonPlaceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moonplaces")
public class MoonPlaceController {

    private final MoonPlaceService moonPlaceService;

    @Autowired
    public MoonPlaceController(MoonPlaceService moonPlaceService) {
        this.moonPlaceService = moonPlaceService;
    }

    @GetMapping
    public ResponseEntity<List<MoonPlace>> getAllMoonPlaces() {
        List<MoonPlace> moonPlaces = moonPlaceService.getAllMoonPlaces();
        return new ResponseEntity<>(moonPlaces, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoonPlace> getMoonPlaceById(@PathVariable Long id) {
        return moonPlaceService.getMoonPlaceById(id)
                .map(moonPlace -> new ResponseEntity<>(moonPlace, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MoonPlace> createMoonPlace(@RequestBody MoonPlace moonPlace) {
        MoonPlace createdMoonPlace = moonPlaceService.createMoonPlace(moonPlace);
        return new ResponseEntity<>(createdMoonPlace, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoonPlace> updateMoonPlace(@PathVariable Long id, @RequestBody MoonPlace updatedMoonPlace) {
        try {
            MoonPlace updatedPlace = moonPlaceService.updateMoonPlace(id, updatedMoonPlace);
            return new ResponseEntity<>(updatedPlace, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoonPlace(@PathVariable Long id) {
        moonPlaceService.deleteMoonPlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
