package user_input;

import java.util.Scanner;

public class UserCommunication {

    private static final String PROMPT = "Please enter ";

    private final Scanner scanner = new Scanner(System.in);

    public void requestInput(String topic) {
        System.out.print(PROMPT + topic);
    }

    public void informUser(String message) {
        System.out.println(message);
    }

    public String getItem() {
        return scanner.nextLine();
    }

    public Integer getQuantity() {
        return scanner.nextInt();
    }

}
