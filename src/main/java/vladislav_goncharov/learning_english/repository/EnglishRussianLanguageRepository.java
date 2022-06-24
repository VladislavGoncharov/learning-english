package vladislav_goncharov.learning_english.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vladislav_goncharov.learning_english.entity.EnglishRussianLanguage;

@Repository
public interface EnglishRussianLanguageRepository extends JpaRepository<EnglishRussianLanguage,Long> {
    EnglishRussianLanguage findFirstByEnglandWord(String englandWord);
    EnglishRussianLanguage findFirstByRussianWord(String russianWord);
}
