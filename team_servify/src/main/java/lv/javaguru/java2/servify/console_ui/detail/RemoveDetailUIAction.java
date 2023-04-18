package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.detail.RemoveDetailService;
import lv.javaguru.java2.servify.dependency_injection.DIComponent;
import lv.javaguru.java2.servify.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class RemoveDetailUIAction implements UIAction {

    @DIDependency private RemoveDetailService removeDetailService;
    @DIDependency private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        getAllDetailsService.execute().forEach(System.out::println);

        System.out.println("Enter detail id to remove (only use id that is visible in the list!): ");
        Long detailId = Long.parseLong(scanner.nextLine());
        removeDetailService.execute(detailId);
        System.out.println("Your detail was removed from list.");
    }

}
