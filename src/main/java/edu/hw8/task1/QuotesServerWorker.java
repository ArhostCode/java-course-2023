package edu.hw8.task1;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
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
        Selector selector = Selector.open();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        while (clientChannel.isConnected()) {
            if (selector.selectNow() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        String message = readMessageFromClient();
                        if (message == null) {
                            break;
                        }
                        if (messageConsumer != null) {
                            messageConsumer.accept(message);
                        }
                        writeMessageToClient(quotesStorage.getQuote(message));
                    }
                    iterator.remove();
                }
            }
        }
        if (after != null) {
            after.run();
        }
    }

    @SneakyThrows
    private String readMessageFromClient() {
        try {
            StringBuilder message = new StringBuilder();
            int read = clientChannel.read(byteBuffer);
            if (read <= 0) {
                return null;
            }
            while (read > 0) {
                byteBuffer.flip();
                byte[] bytesArray = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytesArray);
                message.append(new String(bytesArray, StandardCharsets.UTF_8));
                byteBuffer.clear();
                read = clientChannel.read(byteBuffer);
            }
            return message.toString();
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
