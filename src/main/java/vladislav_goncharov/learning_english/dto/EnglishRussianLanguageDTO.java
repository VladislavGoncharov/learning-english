package vladislav_goncharov.learning_english.dto;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EnglishRussianLanguageDTO {

    private Long id;

    private String englandWord;
    private String russianWord;

    private String englandWordCheck;
    private String russianWordCheck;

    public boolean checkingEnglandWord(){
        englandWord = englandWord.trim();
        englandWordCheck = englandWordCheck.trim();
        return englandWord.equalsIgnoreCase(englandWordCheck);
    }
    public boolean checkingRussianWord(){
        russianWord = russianWord.trim();
        russianWordCheck = russianWordCheck.trim();
        return Objects.equals(russianWord,russianWordCheck);
    }

}
