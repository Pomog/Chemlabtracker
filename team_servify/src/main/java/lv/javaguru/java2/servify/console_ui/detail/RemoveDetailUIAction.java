package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.detail.RemoveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveDetailUIAction implements UIAction {

    @Autowired private RemoveDetailService removeDetailService;
    @Autowired private GetAllDetailsService getAllDetailsService;

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
