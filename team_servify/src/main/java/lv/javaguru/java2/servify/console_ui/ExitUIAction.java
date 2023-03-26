package lv.javaguru.java2.servify.console_ui;

public class ExitUIAction implements UIAction {
    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }

}
