package console_ui;

public class ExitUIAction implements UIAction{

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
