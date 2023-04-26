package java2.dependency_injection;

import java2.fitness_app.dependency_injection.ClassFinder;
import java2.fitness_app.dependency_injection.DIComponentFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DIComponentFilterTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder classFinder = new ClassFinder();
		DIComponentFilter filter = new DIComponentFilter();
		List<Class> classes = classFinder.findClassesInsidePackage("java2");
		List<Class> diComponents = filter.filter(classes);
		diComponents.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}