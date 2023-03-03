package DetailApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DetailListApplication {
    public static void main(String[] args) {
        List<Detail> details = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add detail to list");
            System.out.println("2. Delete detail from list");
            System.out.println("3. Show all detail in the list");
            System.out.println("4. Exit");

            System.out.println("");

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1: {
                    System.out.println("Enter detail type: ");
                    String detailType = scanner.nextLine();
                    System.out.println("Enter detail location: ");
                    String detailLocation = scanner.nextLine();
                    System.out.println("Enter detail side: ");
                    String detailSide = scanner.nextLine();
                    Detail detail = new Detail(detailType,detailLocation,detailSide);
                    details.add(detail);
                    System.out.println("Your detail was added to list.");
                    break;
                }
                case 2: {
                    System.out.println("Enter detail type: ");
                    String detailType = scanner.nextLine();
                    System.out.println("Enter detail location: ");
                    String detailLocation = scanner.nextLine();
                    System.out.println("Enter detail side: ");
                    String detailSide = scanner.nextLine();
                    details.remove(new Detail(detailType, detailLocation, detailSide));
                    System.out.println("Your detail was removed from list.");
                    break;
                }
                case 3: {
                    System.out.println("Detail list: ");
                    for (Detail detail : details) {
                        System.out.println(detail);
                    }
                    System.out.println("Detail list end.");
                    break;
                }
                case 4: {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }

    }
}