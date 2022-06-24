package vladislav_goncharov.learning_english.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vladislav_goncharov.learning_english.entity.EnglishRussianLanguage;
import vladislav_goncharov.learning_english.repository.EnglishRussianLanguageRepository;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class EnglishRussianLanguageServiceImpl implements EnglishRussianLanguageService {

    private final EnglishRussianLanguageRepository englishRussianLanguageRepository;

    public EnglishRussianLanguageServiceImpl(EnglishRussianLanguageRepository englishRussianLanguageRepository) {
        this.englishRussianLanguageRepository = englishRussianLanguageRepository;
    }

    @Override
    public List<EnglishRussianLanguage> findAll() {
        return englishRussianLanguageRepository.findAll();
    }

    @Override
    public void save(EnglishRussianLanguage englishRussianLanguage) throws RuntimeException{
        EnglishRussianLanguage englandWord = englishRussianLanguageRepository.findFirstByEnglandWord(englishRussianLanguage.getEnglandWord());
        EnglishRussianLanguage russianWord = englishRussianLanguageRepository.findFirstByRussianWord(englishRussianLanguage.getRussianWord());

        if (englandWord != null)
            throw new RuntimeException("Английское слово " + englishRussianLanguage.getEnglandWord() + " уже существует");
        if (russianWord != null)
            throw new RuntimeException("Русское слово " + englishRussianLanguage.getRussianWord() + " уже существует");

        System.out.println(englishRussianLanguageRepository.count());
        englishRussianLanguageRepository.save(englishRussianLanguage);
        System.out.println(englishRussianLanguageRepository.count());

    }

    @Override
    public void deleteById(Long id) {
        englishRussianLanguageRepository.deleteById(id);
    }

    @Override
    public EnglishRussianLanguage randomWord() {
        int size = (int) englishRussianLanguageRepository.count();
        Long randomLong = (long) new Random().nextInt(size) + 1L;

        return englishRussianLanguageRepository.getById(randomLong);

    }
}
