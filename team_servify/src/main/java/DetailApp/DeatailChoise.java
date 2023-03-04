package DetailApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeatailChoise {
    Scanner scanner = new Scanner(System.in);
    List<Detail> details = new ArrayList<>();
    String detailType = null;
    String detailLocation = null;
    String detailSide = null;
    Detail detail = new Detail(detailType,detailLocation,detailSide);

    Detail detailChose() {
        System.out.println("Enter detail type:");
        System.out.println("1. Bonnet\n" +
                "2. Boot\n" +
                "3. Bumper\n" +
                "4. Roof\n" +
                "5. Door \n" +
                "6. Wing \n" +
                "7. Wing mirror");
        int detailChoice = Integer.parseInt(scanner.nextLine());
        switch(detailChoice) {
            case 1: {
                System.out.println("You choosed bonnet.");
                detailType = "Bonnet";
                detailLocation = "";
                detailSide = "";
            }
            break;
            case 2: {
                System.out.println("You choosed is boot");
                detailType = "Boot";
                detailLocation = "";
                detailSide = "";
            }
            break;
            case 3: {
                System.out.println("You choosed bumper");
                detailType = "Bumper";
                detailSide = "";
                System.out.println("Enter detail location: ");
                System.out.println("1.Front\n" +
                        "2.Rear");
                int locationChoise = Integer.parseInt(scanner.nextLine());
                if (locationChoise == 1) {
                    detailLocation = "Front";
                } else if (locationChoise == 2) {
                    detailLocation = "Rear";
                }
            }
            break;
            case 4: {
                System.out.println("You choosed roof");
                detailType = "Roof";
                detailLocation = "";
                detailSide = "";
            }
            case 5: {
                System.out.println("You choosed door");
                detailType = "Door";
                System.out.println("Enter detail location: ");
                System.out.println("1.Front\n" +
                        "2.Rear");
                int locationChoise = Integer.parseInt(scanner.nextLine());
                if (locationChoise == 1) {
                    detailLocation = "Front";
                } else if (locationChoise == 2) {
                    detailLocation = "Rear";
                }
                System.out.println("Enter detail side: ");
                System.out.println("1.Left\n" +
                        "2.Right");
                int sideChose = Integer.parseInt(scanner.nextLine());
                if (sideChose == 1) {
                    detailSide = "Left";
                } else if (sideChose == 2) {
                    detailSide = "Right";
                }
            }
            break;
            case 6: {
                System.out.println("You choosed wing");
                detailType = "Wing";
                System.out.println("Enter detail location: ");
                System.out.println("1.Front\n" +
                        "2.Rear");
                int locationChoise = Integer.parseInt(scanner.nextLine());
                if (locationChoise == 1) {
                    detailLocation = "Front";
                } else if (locationChoise == 2) {
                    detailLocation = "Rear";
                }
                System.out.println("Enter detail side: ");
                System.out.println("1.Left\n" +
                        "2.Right");
                int sideChose = Integer.parseInt(scanner.nextLine());
                if (sideChose == 1) {
                    detailSide = "Left";
                } else if (sideChose == 2) {
                    detailSide = "Right";
                }

            }
            break;
            case 7: {
                System.out.println("You choosed wing miror");
                detailType = "Wing mirror";
                detailLocation = "";
                System.out.println("Enter detail side: ");
                System.out.println("1.Left\n" +
                        "2.Right");
                int sideChose = Integer.parseInt(scanner.nextLine());
                if (sideChose == 1) {
                    detailSide = "Left";
                } else if (sideChose == 2) {
                    detailSide = "Right";
                }
            }
            break;
        }

        return new Detail(detailType,detailLocation,detailSide);
    }
}
