package imoon.controllers.views.panel;

import imoon.entities.place.PlaceCategory;
import imoon.services.place.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public String getCategory(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoryPage"; // Вернуть имя представления (view name)
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute PlaceCategory category, @RequestParam Long parentId) {
        try {
            if (parentId != null && !parentId.equals(category.getId())) {
                category.setParent(categoryService.getCategoryById(parentId));
            }
            categoryService.updateCategory(category, parentId);
            // Вернуть на страницу с обновленной категорией или другим действием
            return "redirect:/category/" + category.getId();
        } catch (Exception e) {
            // Обработка ошибок
            return "errorPage"; // Вернуть имя представления для ошибки
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);
            // Вернуть на предыдущую страницу или другое действие
            return "redirect:/previousPage";
        } catch (Exception e) {
            // Обработка ошибок
            return "errorPage"; // TODO Вернуть имя представления для ошибки
        }
    }
}
