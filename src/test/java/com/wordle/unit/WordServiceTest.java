package com.wordle.unit;

import com.wordle.exceptions.IncorrectSizeForInput;
import com.wordle.services.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


class WordServiceTest {

    private WordService wordService;

    @BeforeEach
    void setUp() { wordService = new WordService(); }

    @Test
    void ShouldBeAbleToVerifyInput() {
        wordService.verifyInput("crane");
    }

    @Test
    void ShouldThrowExceptionWhenInputIsTooLong() {
        assertThatThrownBy(() -> wordService.verifyInput("lemotestbeaucouptroplong"))
                .isInstanceOf(IncorrectSizeForInput.class);
    }

    @Test
    void ShouldThrowExceptionWhenInputIsTooShort() {
        assertThatThrownBy(() -> wordService.verifyInput("bl"))
                .isInstanceOf(IncorrectSizeForInput.class);
    }

    @Test
    void ShouldRemovesAccentsAndUppercases() {
        assertThat(WordService.normalizeWord("éàüê")).isEqualTo("EAUE");
    }

    @Test
    void ShouldDecodeUnicode() {
        assertThat(WordService.decodeUnicode("caf\\u00e9")).isEqualTo("café");
    }

    @Test
    void ShouldExtractFieldFromJson() {
        String json = "{\"id\":1,\"name\":\"crane\",\"size\":5}";
        assertThat(wordService.extractField(json, "name")).isEqualTo("crane");
    }

}