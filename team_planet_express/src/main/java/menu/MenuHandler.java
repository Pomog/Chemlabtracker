package menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class MenuHandler {
    private final Method[] methods;

    public boolean isWorking;

    public MenuHandler() {
        methods = this.getClass().getDeclaredMethods();
        isWorking = true;
    }

    public List<String> getListMethod() {
        return Arrays.stream(methods)
                .map(m -> m.getAnnotation(MenuHandleInfo.class))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(MenuHandleInfo::num))
                .map(MenuHandleInfo::desc)
                .collect(Collectors.toList());
    }

    public void handleChoice(int num) {
        for (Method method : methods) {
            MenuHandleInfo a = method.getAnnotation(MenuHandleInfo.class);
            if (a == null) continue;
            if (a.num() == num) {
                try {
                    method.invoke(this, (Object[]) null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
        }
        throw new RuntimeException("Wrong input! This number doesn't exist in menu:" + num);
    }

}
