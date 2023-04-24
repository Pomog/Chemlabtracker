package java2.eln.dependency_injection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassFinderTest {


    @Test
    public void test() throws IOException, ClassNotFoundException {
        String packageName = "java2.eln";

        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage(packageName);

        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });

        String message = String.format("The number of classes in the package \"%s\" is %d.", packageName, classes.size());
        System.out.println(message);
    }
}