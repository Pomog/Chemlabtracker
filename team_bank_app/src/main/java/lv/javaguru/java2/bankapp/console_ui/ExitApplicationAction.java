package lv.javaguru.java2.bankapp.console_ui;

public class ExitApplicationAction implements UIAction {
    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
