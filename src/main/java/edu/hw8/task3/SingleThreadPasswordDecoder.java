package edu.hw8.task3;

import java.util.List;
import java.util.Map;

public class SingleThreadPasswordDecoder extends AbstractPasswordsDecoder {
    public SingleThreadPasswordDecoder(Map<String, String> usersMap) {
        super(usersMap);
    }

    @Override
    public List<User> decode() {
        decodeInRange(-1, ALPHABET.length);
        return decoded;
    }
}
