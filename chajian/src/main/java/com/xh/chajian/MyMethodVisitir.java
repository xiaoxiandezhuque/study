package com.xh.chajian;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyMethodVisitir extends MethodVisitor implements Opcodes {
    public MyMethodVisitir(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        //方法之前
        System.out.println(" method ----> visitCode");
        mv.visitInsn(ICONST_2);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_0);
        mv.visitLdcInsn("11");
        mv.visitInsn(AASTORE);
        mv.visitInsn(DUP);
        mv.visitInsn(ICONST_1);
        mv.visitLdcInsn("start");
        mv.visitInsn(AASTORE);
        mv.visitMethodInsn(INVOKESTATIC, "com/blankj/utilcode/util/LogUtils", "e", "([Ljava/lang/Object;)V", false);
    }

    @Override
    public void visitInsn(int opcode) {

        System.out.println(" method ----> visitInsn");
        //方法之后
        if (opcode == Opcodes.RETURN) {
            System.out.println(" method ----> visitInsn-return");
            mv.visitInsn(ICONST_2);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitLdcInsn("22");
            mv.visitInsn(AASTORE);
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_1);
            mv.visitLdcInsn("end");
            mv.visitInsn(AASTORE);
            mv.visitMethodInsn(INVOKESTATIC, "com/blankj/utilcode/util/LogUtils", "e", "([Ljava/lang/Object;)V", false);
        }
        super.visitInsn(opcode);
    }

    @Override
    public void visitEnd() {

        System.out.println(" method ----> visitEnd");

        super.visitEnd();
    }
}
