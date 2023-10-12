package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Random random;

    public FaultyConnection() {
        random = new Random();
    }

    public FaultyConnection(Random random) {
        this.random = random;
    }

    @Override
    public void execute(String command) {
        if (random.nextBoolean()) {
            throw new ConnectionException();
        }
        LOGGER.info("Faulty connection execute command " + command);
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Faulty connection closed");
    }
}
