package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuotesServer {
    private static final Logger LOGGER = LogManager.getLogger(QuotesServer.class);
    private final int port;
    private final QuotesStorage quotesStorage;
    private final ExecutorService executorService;
    private final Semaphore parallelConnectionSemaphore;
    private Consumer<String> messageConsumer;
    private ServerSocket serverSocket;

    public QuotesServer(int port, QuotesStorage quotesStorage, int parallelConnections) {
        this.port = port;
        this.quotesStorage = quotesStorage;
        this.parallelConnectionSemaphore = new Semaphore(parallelConnections);
        this.executorService = Executors.newFixedThreadPool(parallelConnections);
        this.messageConsumer = message -> LOGGER.info("Ваня: {}", message);
    }

    @SneakyThrows
    public void start() {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {
            serverSocket = channel.socket();
            serverSocket.bind(new InetSocketAddress(port));
            while (channel.isOpen()) {
                if (parallelConnectionSemaphore.tryAcquire()) {
                    accept(channel);
                }
            }
        }
    }

    @SneakyThrows
    private void accept(ServerSocketChannel channel) {
        SocketChannel clientChannel = channel.accept();
        executorService.execute(new QuotesServerWorker(clientChannel, quotesStorage)
            .afterClosing(parallelConnectionSemaphore::release)
            .onMessage(messageConsumer)
        );
    }

    @SneakyThrows
    public void stop() {
        executorService.shutdownNow();
        serverSocket.close();
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }
}
