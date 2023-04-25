package java2.fitness_app.dependency_injection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;

class ClassFinder {

	public List<Class> findClassesInsidePackage(String packageName) {
		Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
		return new ArrayList<>(reflections.getSubTypesOf(Object.class));
	}

}