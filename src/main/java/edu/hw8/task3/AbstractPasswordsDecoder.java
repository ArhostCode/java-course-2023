package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public abstract class AbstractPasswordsDecoder implements PasswordsDecoder {

    private static final int MINIMAL_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 6;

    // Actually, we not need to synchronize userMap and decoded,
    // because probability of hash collision is very small with MD5 algorithm
    protected Map<ByteArray, String> usersMap;
    protected final List<User> decoded = new CopyOnWriteArrayList<>();
    protected static final byte[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".getBytes(StandardCharsets.UTF_8);

    public AbstractPasswordsDecoder(Map<String, String> usersMap) {
        this.usersMap = usersMap
            .entrySet()
            .stream().collect(Collectors.toMap(
                entry -> {
                    try {
                        // To provide more performance
                        return new ByteArray(Hex.decodeHex(entry.getValue().toCharArray()));
                    } catch (DecoderException e) {
                        throw new RuntimeException(e);
                    }
                },
                Map.Entry::getKey
            ));
    }

    @Override
    public abstract List<User> decode();

    @SneakyThrows
    protected void decodeInRange(int min, int max) {
        boolean containsSmallPasswords = min != -1;
        int[] indexes =
            createIndexes(MAX_PASSWORD_LENGTH, containsSmallPasswords ? MINIMAL_PASSWORD_LENGTH : MAX_PASSWORD_LENGTH);
        indexes[indexes.length - 1] = min;
        // Create there to provide each thread new MessageDigest instance
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        while (indexes[indexes.length - 1] < max) {
            if (decoded.size() == usersMap.size()) {
                break;
            }
            var bytes = nextPassword(indexes);
            ByteArray encodedPassword = encodeMd5(bytes, messageDigest);
            if (usersMap.containsKey(encodedPassword)) {
                decoded.add(new User(usersMap.get(encodedPassword), new String(bytes)));
            }
        }
    }

    private int[] createIndexes(int passwordLength, int minimumLength) {
        int[] indexes = new int[passwordLength];
        for (int i = 0; i < indexes.length; i++) {
            if (i >= minimumLength) {
                indexes[i] = -1;
            } else {
                indexes[i] = 0;
            }
        }
        return indexes;
    }

    private byte[] nextPassword(int[] indexes) {
        byte[] bytes = makeByteString(indexes);
        addOneToIndexes(indexes, ALPHABET.length);
        return bytes;
    }

    private byte[] makeByteString(int[] indexes) {
        byte[] bytes = new byte[indexes.length];
        int realCount = indexes.length;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] < 0) {
                realCount = i;
                break;
            }
            bytes[i] = ALPHABET[indexes[i]];
        }
        bytes = Arrays.copyOf(bytes, realCount);
        return bytes;
    }

    private void addOneToIndexes(int[] indexes, int max) {
        indexes[0]++;
        for (int i = 0; i < indexes.length - 1; i++) {
            if (indexes[i] >= max) {
                indexes[i] = 0;
                indexes[i + 1]++;
            } else {
                break;
            }
        }
    }

    @SneakyThrows
    private ByteArray encodeMd5(byte[] password, MessageDigest messageDigest) {
        messageDigest.update(password);
        return new ByteArray(messageDigest.digest());
    }

    protected record ByteArray(byte[] array) {
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            ByteArray byteArray = (ByteArray) object;
            return Arrays.equals(array, byteArray.array);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }
}
