package imoon.services.place;

import imoon.entities.place.PlaceCategory;
import imoon.repositories.place.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<PlaceCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public PlaceCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
    }

    public PlaceCategory createCategory(PlaceCategory placeCategory) {
        return categoryRepository.save(placeCategory);
    }

    public PlaceCategory updateCategory(PlaceCategory placeCategory, Long parentId) {
        // Проверки на существование и другая логика обновления

        return categoryRepository.save(placeCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
