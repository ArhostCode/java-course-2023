package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.*;

public class RemoteServerTest {

    @Test
    @DisplayName("Тест FaultyConnectionManager#getConnection, что он всегда возвращает FaultyConnection")
    public void getConnection_shouldReturnFaultyConnection_whenConnectionManagerFaulty() {
        Connection connection = new FaultyConnectionManager().getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Тест DefaultConnectionManager#getConnection, что он может возвращать StableConnection")
    public void getConnection_shouldReturnStableConnection_whenConnectionManagerDefaultAndRandomReturnTrue() {
        // I not sure, that using prepared random with seed is good case. But otherwise there is no way to cover half of the code of this task with tests.
        Random random = new Random(1);
        Connection connection = new DefaultConnectionManager(random).getConnection();
        assertThat(connection).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("Тест DefaultConnectionManager#getConnection, что он может возвращать FaultyConnection")
    public void getConnection_shouldReturnFaultyConnection_whenConnectionManagerDefaultAndRandomReturnFalse() {
        Random random = new Random(78);
        Connection connection = new DefaultConnectionManager(random).getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName(
        "Тест PopularCommandExecutor#tryExecute, что он не выбрасывает исключение, когда DefaultConnectionManager использует StableConnection")
    public void tryExecute_shouldNotThrowException_whenConnectionDefaultAndRandomIsTrue() {
        PopularCommandExecutor commandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(new Random(1)), 1);
        assertThatCode(commandExecutor::updatePackages).doesNotThrowAnyException();
    }

    @Test
    @DisplayName(
        "Тест PopularCommandExecutor#tryExecute, что он выбрасывает исключение, когда DefaultConnectionManager использует FaultyConnection и оно возвращает исключение")
    public void tryExecute_shouldThrowException_whenConnectionDefaultAndRandomIsPreparedToFalse() {
        PopularCommandExecutor commandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(new Random(78)), 1);
        assertThatThrownBy(commandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName(
        "Тест PopularCommandExecutor#tryExecute, что он выбрасывает исключение, когда передано неправильное максимальное количество попыток")
    public void tryExecute_shouldThrowException_whenWrongMaxAttempts() {
        ConnectionManager connectionManager = new DefaultConnectionManager(new Random(1));
        assertThatThrownBy(() -> new PopularCommandExecutor(
            connectionManager,
            0
        )).isInstanceOf(IllegalArgumentException.class);
    }

}
