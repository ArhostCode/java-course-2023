package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public final class FibClassGenerator {

    private static final String FIB_CLASS_NAME = "Fibonacci";
    private static final String FUNCTION_NAME = "fib";
    private static final int FUNCTION_OPERANDS_STACK_SIZE = 5;
    private static final String FUNCTION_SIGNATURE = "(I)J";

    private FibClassGenerator() {
    }

    public static Object generate() {
        try (var unloaded = new ByteBuddy()
            .subclass(Object.class)
            .name(FIB_CLASS_NAME)
            .defineMethod(FUNCTION_NAME, long.class, Ownership.MEMBER, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(createFibImplementation())
            .make()
        ) {
            return unloaded.load(FibClassGenerator.class.getClassLoader()).getLoaded().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Implementation createFibImplementation() {
        return new Implementation.Simple(new FibByteCodeAppender());
    }

    private final static class FibByteCodeAppender implements ByteCodeAppender {

        @Override
        @NotNull
        public Size apply(
            MethodVisitor methodVisitor,
            @NotNull Implementation.Context context,
            @NotNull MethodDescription methodDescription
        ) {
            Label moreTwoLabel = new Label();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, moreTwoLabel);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            // more than 2
            methodVisitor.visitLabel(moreTwoLabel);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);

            // fib(n - 1)
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                FIB_CLASS_NAME,
                FUNCTION_NAME,
                FUNCTION_SIGNATURE,
                false
            );

            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);

            // fib(n - 2)
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                FIB_CLASS_NAME,
                FUNCTION_NAME,
                FUNCTION_SIGNATURE,
                false
            );
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            // localVariableSize = 0 because Implementation.Simple creates variables
            // for parameters and I don`t use anything else
            return new ByteCodeAppender.Size(FUNCTION_OPERANDS_STACK_SIZE, 0);
        }
    }

}
