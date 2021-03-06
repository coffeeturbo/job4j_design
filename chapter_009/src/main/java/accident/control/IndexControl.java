package accident.control;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {

        List<String> names = List.of("Viachgend", "Ridley Scott", "John Malcovich");

        model.addAttribute("names", names);
        return "index";
    }
}
