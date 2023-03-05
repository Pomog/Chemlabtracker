package DetailApp;

import java.util.Scanner;

public class DeatailChoise {
    Scanner scanner = new Scanner(System.in);
    DetailLocationChoise detailLocationChoise = new DetailLocationChoise();
    DetailSideChoise detailSideChoise = new DetailSideChoise();
    String detailType = null;
    String detailLocation = null;
    String detailSide = null;



    Detail detailChoise() {
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
                System.out.println("You choosed boot");
                detailType = "Boot";
                detailLocation = "";
                detailSide = "";
            }
            break;
            case 3: {
                System.out.println("You choosed bumper");
                detailType = "Bumper";
                detailSide = "";
                detailLocation = detailLocationChoise.detailLocationChoise();
            }
            break;
            case 4: {
                System.out.println("You choosed roof");
                detailType = "Roof";
                detailLocation = "";
                detailSide = "";
            }
            break;
            case 5: {
                System.out.println("You choosed door");
                detailType = "Door";
                detailLocation = detailLocationChoise.detailLocationChoise();
                detailSide = detailSideChoise.newDetailSide();
            }
            break;
            case 6: {
                System.out.println("You choosed wing");
                detailType = "Wing";
                detailLocation = detailLocationChoise.detailLocationChoise();
                detailSide = detailSideChoise.newDetailSide();

            }
            break;
            case 7: {
                System.out.println("You choosed wing miror");
                detailType = "Wing mirror";
                detailLocation = "";
                detailSide = detailSideChoise.newDetailSide();
            }
            break;
        }

        return new Detail(detailType,detailLocation,detailSide);
    }
}
