package DetailApp;

import java.util.Scanner;

public class DetailSideChose {
    Scanner scanner = new Scanner(System.in);
    String newDetailSide;
    String NewDetailSide(){
        System.out.println("Enter detail side: \n" +
                "1.Left\n" +
                "2.Right");
        int sideChose = Integer.parseInt(scanner.nextLine());
        if (sideChose == 1) {
            newDetailSide = "Left";
        } else if (sideChose == 2) {
            newDetailSide = "Right";
        }
        return newDetailSide;
    }
}
