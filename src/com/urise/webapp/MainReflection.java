package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r,"new_uuid");
        Class resumeClass = Resume.class;
        Object objectResume = resumeClass.newInstance();
        Method methodToString = resumeClass.getMethod("toString");
        System.out.println(methodToString.invoke(objectResume));
    }
}

