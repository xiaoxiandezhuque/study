package com.xh.chajian;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

//    public LifecycleClassVisitor(ClassVisitor cv) {
//        super(Opcodes.ASM5, cv);
//    }

    private String mClassName;
    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);

    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        //匹配FragmentActivity
//        if ("androidx/appcompat/app/FragmentActivity".equals(this.mClassName)) {


        if ("onCreate".equals(name)) {
            System.out.println("change method ----> " + name);
            return new MyMethodVisitir(Opcodes.ASM5, mv);
        }

//            if ("onCreate".equals(name) ) {
//                //处理onCreate
//                System.out.println("LifecycleClassVisitor : change method ----> " + name);
//                return new LifecycleOnCreateMethodVisitor(mv);
//            } else if ("onDestroy".equals(name)) {
//                //处理onDestroy
//                System.out.println("LifecycleClassVisitor : change method ----> " + name);
//                return new LifecycleOnDestroyMethodVisitor(mv);
//            }
//        }
        return mv;

    }
}
