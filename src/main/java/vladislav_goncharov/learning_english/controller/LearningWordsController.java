package vladislav_goncharov.learning_english.controller;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vladislav_goncharov.learning_english.dto.EnglishRussianLanguageDTO;
import vladislav_goncharov.learning_english.entity.EnglishRussianLanguage;
import vladislav_goncharov.learning_english.service.EnglishRussianLanguageService;
import vladislav_goncharov.learning_english.utils.NumberOfCorrectAnswersUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/learning-word")
public class LearningWordsController {

    private final EnglishRussianLanguageService englishRussianLanguageService;

    public LearningWordsController(EnglishRussianLanguageService englishRussianLanguageService) {
        this.englishRussianLanguageService = englishRussianLanguageService;
    }

    @RequestMapping("/{lang}")
    public String learningWords(@PathVariable("lang") String language, Model model, HttpServletRequest request) {
        model.addAttribute("quantity",NumberOfCorrectAnswersUtil.getNumberOfCorrectAnswersInSession(request));

        EnglishRussianLanguage word = englishRussianLanguageService.randomWord();
        model.addAttribute("word", EnglishRussianLanguageDTO.builder()
                .englandWord(word.getEnglandWord())
                .russianWord(word.getRussianWord())
                .build());
        model.addAttribute("lang", language);


        return "learning-word";
    }

    @PostMapping("/check-eng")
    public String checkEnglishWords(@ModelAttribute("word") EnglishRussianLanguageDTO word
            , Model model, HttpServletRequest request) {

        if (!word.checkingRussianWord()) {
            model.addAttribute("error", "Перевод неверен");
            model.addAttribute("word", word);
            model.addAttribute("lang", "eng");

            NumberOfCorrectAnswersUtil.resetNumberOfCorrectAnswersInSession(request);
            model.addAttribute("quantity"
                    ,NumberOfCorrectAnswersUtil.getNumberOfCorrectAnswersInSession(request));

            return "learning-word";
        }

        NumberOfCorrectAnswersUtil.increaseNumberOfCorrectAnswersInSession(request);
        return "redirect:/learning-word/eng";
    }

    @PostMapping("/check-rus")
    public String checkRussianWords(@ModelAttribute("word") EnglishRussianLanguageDTO word
            , Model model, HttpServletRequest request) {

        if (!word.checkingEnglandWord()) {
            model.addAttribute("error", "Перевод неверен");
            model.addAttribute("word", word);
            model.addAttribute("lang", "rus");

            NumberOfCorrectAnswersUtil.resetNumberOfCorrectAnswersInSession(request);
            model.addAttribute("quantity"
                    ,NumberOfCorrectAnswersUtil.getNumberOfCorrectAnswersInSession(request));
            return "learning-word";
        }

        NumberOfCorrectAnswersUtil.increaseNumberOfCorrectAnswersInSession(request);
        return "redirect:/learning-word/rus";
    }

}

