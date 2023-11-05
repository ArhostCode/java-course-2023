package edu.hw3.task1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AtbashCipherTest {

    @DisplayName("Тестирование AtbashCipher#encode на верных значениях")
    @ParameterizedTest(name = "{0} - returns {1}")
    @CsvSource(value = {
        "Hello world!,Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler,Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    public void encode_souldReturnEncodedString(String original, String expected) {
        Assertions.assertThat(AtbashCipher.encode(original)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование AtbashCipher#encode на null")
    public void encode_shouldThrowNullPointerException_whenInputIncorrect() {
        Assertions.assertThatThrownBy(() -> AtbashCipher.encode(null)).isInstanceOf(NullPointerException.class);
    }
}
