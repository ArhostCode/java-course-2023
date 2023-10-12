package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Wrong max attempts count");
        }
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        ConnectionException throwedException = null;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try (Connection connection = manager.getConnection()) { // Auto closing
                connection.execute(command);
                LOGGER.trace("Command has been executed");
                return;
            } catch (Exception e) {
                throwedException = (ConnectionException) e;
                attempt++;
            }
        }
        if (throwedException != null) {
            LOGGER.trace("The maximum number of attempts has been reached. Command not executed.");
            throw new ConnectionException(throwedException);
        }
    }
}
