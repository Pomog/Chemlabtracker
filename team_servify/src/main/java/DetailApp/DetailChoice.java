package DetailApp;

import java.util.Scanner;

class DetailChoice {
    Scanner scanner = new Scanner(System.in);

    private final DetailLocationChoice detailLocationChoice = new DetailLocationChoice();
    private final DetailSideChoice detailSideChoice = new DetailSideChoice();


    Detail detailChoice() {

        String detailType = null;
        String detailLocation = null;
        String detailSide = null;

        System.out.println("Enter detail type:");
        System.out.println("""
                1. Bonnet
                2. Boot
                3. Bumper
                4. Roof
                5. Door\s
                6. Wing\s
                7. Wing mirror""");

        while (detailType == null) {

            int detailChoice = Integer.parseInt(scanner.nextLine());

            switch (detailChoice) {
                case 1 -> {
                    System.out.println("You chose bonnet.");
                    detailType = "Bonnet";
                    detailLocation = "";
                    detailSide = "";
                }
                case 2 -> {
                    System.out.println("You chose boot");
                    detailType = "Boot";
                    detailLocation = "";
                    detailSide = "";
                }
                case 3 -> {
                    System.out.println("You chose bumper");
                    detailType = "Bumper";
                    detailLocation = detailLocationChoice.detailLocationChoice();
                    detailSide = "";
                }
                case 4 -> {
                    System.out.println("You chose roof");
                    detailType = "Roof";
                    detailLocation = "";
                    detailSide = "";
                }
                case 5 -> {
                    System.out.println("You chose door");
                    detailType = "Door";
                    detailLocation = detailLocationChoice.detailLocationChoice();
                    detailSide = detailSideChoice.newDetailSide();
                }
                case 6 -> {
                    System.out.println("You chose wing");
                    detailType = "Wing";
                    detailLocation = detailLocationChoice.detailLocationChoice();
                    detailSide = detailSideChoice.newDetailSide();

                }
                case 7 -> {
                    System.out.println("You chose wing mirror");
                    detailType = "Wing mirror";
                    detailLocation = "";
                    detailSide = detailSideChoice.newDetailSide();
                }
                default -> System.out.println("Choose the variant from the menu, please.");
            }
        }

        return new Detail(detailType, detailLocation, detailSide);
    }
}
