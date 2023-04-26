package java2.fitness_app.users.console_ui;

import java2.fitness_app.dependency_injection.DIComponent;
import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
