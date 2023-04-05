package shop.console_ui;


import java.util.Scanner;

public class UserCommunication {

    private static final String PROMPT = "Please enter ";

    private final Scanner scanner = new Scanner(System.in);

    public void requestInput(String topic) {
        System.out.print(PROMPT + topic);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void informUser(String message) {
        System.out.println(message);
    }

    public Integer getMenuActionNumber() {
        return Integer.parseInt(scanner.nextLine());
    }

}
