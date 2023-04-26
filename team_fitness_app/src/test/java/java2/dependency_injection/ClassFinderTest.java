package java2.dependency_injection;

import java2.fitness_app.dependency_injection.ClassFinder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ClassFinderTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder finder = new ClassFinder();
		List<Class> classes = finder.findClassesInsidePackage("java2");
		classes.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}