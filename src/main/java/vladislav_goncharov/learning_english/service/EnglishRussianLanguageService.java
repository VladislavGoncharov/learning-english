package vladislav_goncharov.learning_english.service;

import vladislav_goncharov.learning_english.entity.EnglishRussianLanguage;

import java.util.List;

public interface EnglishRussianLanguageService {

    List<EnglishRussianLanguage> findAll();
    void save(EnglishRussianLanguage englishRussianLanguage);

    void deleteById(Long id);

    EnglishRussianLanguage randomWord();
}
