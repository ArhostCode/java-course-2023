package edu.hw5.task2;

import edu.hw5.task2.Friday13Finder;
import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Friday13FinderTest {

    @Test
    @DisplayName("Тестирование Friday13Finder#searchAllAtYear с верными входными данными")
    public void searchAllAtYear_shouldReturnCorrectList() {
        Assertions.assertThat(Friday13Finder.searchAllAtYear(1925))
            .containsExactly(
                LocalDate.of(1925, Month.FEBRUARY, 13),
                LocalDate.of(1925, Month.MARCH, 13),
                LocalDate.of(1925, Month.NOVEMBER, 13)
            );
    }

    @Test
    @DisplayName("Тестирование Friday13Finder#getNextFridayThe13th с верными входными данными")
    public void getNextFridayThe13th_shouldReturnCorrectList() {
        Assertions.assertThat(Friday13Finder.getNextFridayThe13th(LocalDate.of(2020, Month.JANUARY, 5)))
            .isEqualTo(LocalDate.of(2020, Month.MARCH, 13));
    }

}
