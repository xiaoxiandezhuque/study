package com.xh.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentProxy implements InvocationHandler {

    private IStudent student;

    public StudentProxy(IStudent student) {
        this.student = student;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("我来代理"+method.getName());
        student.study();
        return null;
    }
}
