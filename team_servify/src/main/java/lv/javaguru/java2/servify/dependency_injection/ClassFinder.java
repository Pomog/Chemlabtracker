package lv.javaguru.java2.servify.dependency_injection;

import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class ClassFinder {

    public List<Class> findClassesInsidePackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
    }

}
