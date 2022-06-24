package vladislav_goncharov.learning_english.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vladislav_goncharov.learning_english.entity.EnglishRussianLanguage;
import vladislav_goncharov.learning_english.service.EnglishRussianLanguageService;
import vladislav_goncharov.learning_english.utils.NumberOfCorrectAnswersUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private final EnglishRussianLanguageService englishRussianLanguageService;

    public MainController(EnglishRussianLanguageService englishRussianLanguageService) {
        this.englishRussianLanguageService = englishRussianLanguageService;
    }


    @RequestMapping("/")
    public String mainView(Model model){
        model.addAttribute("allWord",englishRussianLanguageService.findAll());
        model.addAttribute("newWord",new EnglishRussianLanguage());
        return "main-view";
    }
    @PostMapping("/save")
    public String saveWord(@ModelAttribute("newWord") EnglishRussianLanguage englishRussianLanguage,Model model){
        try {
            englishRussianLanguageService.save(englishRussianLanguage);
        } catch (RuntimeException e){
            model.addAttribute("error",e.getMessage());
            return mainView(model);
        }
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteWord(@PathVariable("id") Long id){
        englishRussianLanguageService.deleteById(id);
        return "redirect:/";
    }
}
