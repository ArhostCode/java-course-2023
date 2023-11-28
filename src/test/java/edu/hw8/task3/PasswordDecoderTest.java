package edu.hw8.task3;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordDecoderTest {

    @Test
    @DisplayName("Тестирование ParallelPasswordsDecoder#decode")
    public void decode_shouldReturnCorrectResult_whenMultiThread() {
        PasswordsDecoder decoder = new ParallelPasswordsDecoder(
            Map.of(
                "k.p.maslov", "18e84fa2f549f87a4a3ee3a46e63c3e2",
                "k.p.mdfaslov", "9a32f4a86b3bcd053642f4c708be27f8",
                "k.p.в", "0765c0f7c8e3df239f846c4f78ed1da6",
                "kh.p.в", "2354b5f68a4ce8a030eee955639fdd16"
            ), Runtime.getRuntime().availableProcessors()
        );
        Assertions.assertThat(decoder.decode()).containsExactly(
            new User("k.p.maslov", "abbba"),
            new User("k.p.mdfaslov", "czzzc"),
            new User("k.p.в", "gvvvg"),
            new User("kh.p.в", "zbbbz")
        );
    }

    @Test
    @DisplayName("Тестирование SingleThreadPasswordDecoder#decode")
    public void decode_shouldReturnCorrectResult_whenSingleThread() {
        PasswordsDecoder decoder = new SingleThreadPasswordDecoder(
            Map.of(
                "k.p.maslov", "18e84fa2f549f87a4a3ee3a46e63c3e2",
                "k.p.mdfaslov", "9a32f4a86b3bcd053642f4c708be27f8",
                "k.p.в", "0765c0f7c8e3df239f846c4f78ed1da6",
                "kh.p.в", "2354b5f68a4ce8a030eee955639fdd16"
            )
        );
        Assertions.assertThat(decoder.decode()).containsExactly(
            new User("k.p.maslov", "abbba"),
            new User("k.p.mdfaslov", "czzzc"),
            new User("k.p.в", "gvvvg"),
            new User("kh.p.в", "zbbbz")
        );
    }
}
