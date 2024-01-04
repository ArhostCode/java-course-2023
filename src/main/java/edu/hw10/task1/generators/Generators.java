package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import java.util.HashMap;
import java.util.Map;

public final class Generators {

    private Generators() {
    }

    public static Map<Class<?>, FieldGenerator<?>> defaultGenerators() {
        HashMap<Class<?>, FieldGenerator<?>> generators = new HashMap<>();

        generators.put(String.class, new StringFieldGenerator());
        generators.put(byte.class, new ByteFieldGenerator());
        generators.put(int.class, new IntFieldGenerator());
        generators.put(long.class, new LongFieldGenerator());
        generators.put(float.class, new FloatFieldGenerator());
        generators.put(double.class, new DoubleFieldGenerator());
        generators.put(boolean.class, new BooleanFieldGenerator());
        generators.put(Byte.class, new ByteFieldGenerator());
        generators.put(Integer.class, new IntFieldGenerator());
        generators.put(Long.class, new LongFieldGenerator());
        generators.put(Float.class, new FloatFieldGenerator());
        generators.put(Double.class, new DoubleFieldGenerator());
        generators.put(Boolean.class, new BooleanFieldGenerator());

        return generators;
    }
}
