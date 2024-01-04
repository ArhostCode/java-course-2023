package edu.project3.argument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record Arguments(List<Argument> arguments) {
    public Arguments() {
        this(new ArrayList<>());
    }

    public Arguments addArgument(Argument argument) {
        arguments.add(argument);
        return this;
    }

    public Map<String, String> parse(List<String> args) {
        Map<String, String> argumentsMap = new HashMap<>();
        for (Argument argument : arguments) {
            int index = args.indexOf("--" + argument.name());
            if (index == -1 && argument.isRequired()) {
                throw new IllegalArgumentException("Required argument " + argument.name() + " not found");
            }
            if (index != -1 && index + 1 < args.size()) {
                argumentsMap.put(argument.name(), args.get(index + 1));
            }
        }
        return argumentsMap;
    }
}
