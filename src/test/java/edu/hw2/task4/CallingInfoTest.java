package edu.hw2.task4;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {

    public static Stream<Supplier<CallingInfo>> callingInfoSuppliers() {
        return Stream.of(
            CallingInfoTest::firstStackTraceDepth,
            CallingInfoTest::secondStackTraceDepth,
            CallingInfoTest::thirdStackTraceDepth
        );
    }

    @DisplayName("Тест метода CallingInfo#callingInfo")
    @ParameterizedTest(name = "{0} - возвращает предпоследний элемент в стеке вызовов")
    @MethodSource("callingInfoSuppliers")
    public void callingInfo_shouldReturnClassAndMethodName_whenCalling(Supplier<CallingInfo> callingInfoSupplier) {
        CallingInfo c = callingInfoSupplier.get();
        assertThat(c)
            .extracting("methodName", "className")
            .containsExactly("firstStackTraceDepth", "edu.hw2.task4.CallingInfoTest");
    }

    public static CallingInfo firstStackTraceDepth() {
        return CallingInfo.callingInfo();
    }

    public static CallingInfo secondStackTraceDepth() {
        return firstStackTraceDepth();
    }

    public static CallingInfo thirdStackTraceDepth() {
        return secondStackTraceDepth();
    }

}
