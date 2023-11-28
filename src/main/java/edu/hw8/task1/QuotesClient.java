package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class QuotesClient {
    private final String address;
    private final int port;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private SocketChannel clientChannel;

    public QuotesClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @SneakyThrows
    public void start() {
        clientChannel = SocketChannel.open(new InetSocketAddress(address, port));
    }

    @SneakyThrows
    public String requestQuote(String message) {
        clientChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
        return readFromServer();
    }

    @SneakyThrows
    private String readFromServer() {
        buffer.clear();
        clientChannel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @SneakyThrows
    public void close() {
        clientChannel.close();
    }

}
