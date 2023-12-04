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

public final class FibClassGenerator {

    private static final String FIB_CLASS_NAME = "Fibonacci";
    private static final String FUNCTION_NAME = "fib";
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
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            Label notZeroLabel = new Label();
            Label notOneLabel = new Label();

            methodVisitor.visitInsn(Opcodes.ICONST_0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, notZeroLabel);
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            // not zero
            methodVisitor.visitLabel(notZeroLabel);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);

            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, notOneLabel);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            // not one
            methodVisitor.visitLabel(notOneLabel);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);

            // fib(n - 1)
            methodVisitor.visitInsn(Opcodes.ISUB);
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
            final int operandsCount = 5;
            return new ByteCodeAppender.Size(operandsCount, 0);
        }
    }

}
