package java2.eln.dependency_injection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DIComponentFilterTest {

    @Test
    void filter() {
            String packageName = "java2.eln";

            ClassFinder classFinder = new ClassFinder();
            DIComponentFilter filter = new DIComponentFilter();
            List<Class> classes = classFinder.findClassesInsidePackage(packageName);
            List<Class> diComponents = filter.filter(classes);
            diComponents.forEach(aClass -> {
                System.out.println(aClass.getName());
            });
        }
 }
