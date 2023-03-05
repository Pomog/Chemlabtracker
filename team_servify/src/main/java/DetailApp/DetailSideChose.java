package DetailApp;

import java.util.Scanner;

public class DetailSideChose {
    Scanner scanner = new Scanner(System.in);
    String newDetailSide;
    String NewDetailSide(){
        System.out.println("Enter detail side: \n" +
                "1.Left\n" +
                "2.Right");
        int sideChoise = Integer.parseInt(scanner.nextLine());
        if (sideChoise == 1) {
            newDetailSide = "Left";
        } else if (sideChoise == 2) {
            newDetailSide = "Right";
        }
        return newDetailSide;
    }
}
