package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int MAX_CHANCE_VALUE = 10;
    private final Random random;

    public DefaultConnectionManager() {
        this.random = new Random();
    }

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        return random.nextInt(MAX_CHANCE_VALUE) == 0 ? new FaultyConnection(random) : new StableConnection();
    }
}
