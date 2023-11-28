package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class QuotesServerWorker implements Runnable {

    private final SocketChannel clientChannel;
    private final QuotesStorage quotesStorage;
    private final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    private Runnable after;
    private Consumer<String> messageConsumer;

    @SneakyThrows @Override
    public void run() {
        while (clientChannel.isConnected()) {
            String message = readMessageFromClient();
            if (message == null) {
                break;
            }
            if (messageConsumer != null) {
                messageConsumer.accept(message);
            }
            writeMessageToClient(quotesStorage.getQuote(message));
        }
        if (after != null) {
            after.run();
        }
    }

    @SneakyThrows
    private String readMessageFromClient() {
        try {
            int read = clientChannel.read(byteBuffer);
            if (read <= 0) {
                return null;
            }
            byteBuffer.flip();
            byte[] bytesArray = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytesArray);
            byteBuffer.clear();
            return new String(bytesArray, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    @SneakyThrows
    private void writeMessageToClient(String message) {
        clientChannel.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
    }

    public QuotesServerWorker afterClosing(Runnable after) {
        this.after = after;
        return this;
    }

    public QuotesServerWorker onMessage(Consumer<String> messageSupplier) {
        this.messageConsumer = messageSupplier;
        return this;
    }
}
