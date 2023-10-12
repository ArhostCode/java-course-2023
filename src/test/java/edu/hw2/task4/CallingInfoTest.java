package edu.hw2.task4;

import java.lang.reflect.Method;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CallingInfoTest {

    public static Stream<Method> methodArguments() {
        try {
            return Stream.of(
                CallingInfoTest.class.getMethod("firstStackTraceDepth"),
                CallingInfoTest.class.getMethod("secondStackTraceDepth"),
                CallingInfoTest.class.getMethod("thirdStackTraceDepth")
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Тест метода CallingInfo#callingInfo")
    @ParameterizedTest(name = "{0} - возвращает предпоследний элемент в стеке вызовов")
    @MethodSource("methodArguments")
    public void callingInfo_shouldReturnClassAndMethodName_whenCalling(Method method) {
        try {
            CallingInfo c = (CallingInfo) method.invoke(null); // null when calling static method

            assertThat(c)
                .extracting("methodName", "className")
                .containsExactly("firstStackTraceDepth", "edu.hw2.task4.CallingInfoTest");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
