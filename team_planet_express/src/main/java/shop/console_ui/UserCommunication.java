package shop.console_ui;


import shop.dependency_injection.DIComponent;

import java.util.Scanner;

@DIComponent
public class UserCommunication {

    private static final String PROMPT = "Please enter ";

    private final Scanner scanner = new Scanner(System.in);

    public String requestInput(String topic) {
        System.out.print(PROMPT + topic);
        return scanner.nextLine();
    }

    public void informUser(String message) {
        System.out.println(message);
    }

}
