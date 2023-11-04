package edu.project3.filter;

import edu.project3.model.filter.DateFilter;
import edu.project3.model.log.NginxLog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateFilterTest {

    @Test
    @DisplayName("Тестирование DateFilter#isSuitable на null - null")
    public void isSuitable_shouldReturnCorrectValue_whenNullAndNull() {
        Assertions.assertThat(new DateFilter(null, null).isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 10, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isTrue();
    }

    @Test
    @DisplayName("Тестирование DateFilter#isSuitable на date - null")
    public void isSuitable_shouldReturnCorrectValue_whenDateAndNull() {
        DateFilter filter = new DateFilter(OffsetDateTime.of(2023, 1, 10, 0, 0, 0, 0, ZoneOffset.UTC), null);
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 15, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isTrue();
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 5, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isFalse();
    }

    @Test
    @DisplayName("Тестирование DateFilter#isSuitable на null - date")
    public void isSuitable_shouldReturnCorrectValue_whenNullAndDate() {
        DateFilter filter = new DateFilter(null,OffsetDateTime.of(2023, 1, 10, 0, 0, 0, 0, ZoneOffset.UTC));
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 15, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isFalse();
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 5, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isTrue();
    }

    @Test
    @DisplayName("Тестирование DateFilter#isSuitable на date - date")
    public void isSuitable_shouldReturnCorrectValue() {
        DateFilter filter = new DateFilter(OffsetDateTime.of(2023, 1, 5, 0, 0, 0, 0, ZoneOffset.UTC),OffsetDateTime.of(2023, 1, 10, 0, 0, 0, 0, ZoneOffset.UTC));
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 15, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isFalse();
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 5, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isTrue();
        Assertions.assertThat(filter.isSuitable(NginxLog.builder()
            .timeStamp(OffsetDateTime.of(2023, 1, 3, 0, 0, 0, 0, ZoneOffset.UTC))
            .build())
        ).isFalse();
    }

}
