package java2.fitness_app.users.console_ui;

public class ExitUIAction implements UIAction{

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
