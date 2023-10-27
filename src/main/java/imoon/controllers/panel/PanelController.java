package imoon.controllers.panel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PanelController {

    @GetMapping("/PanelController/index")
    public String index(Model model, @RequestParam(required = false) String message) {

        if (message != null) {
            model.addAttribute("error", message);
        }
        return "index"; // Это имя представления (index.html)
    }
}
