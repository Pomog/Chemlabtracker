package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.service.GetAllDetailsService;
import lv.javaguru.java2.servify.service.RemoveDetailService;

import java.util.Scanner;

public class RemoveDetailUIAction implements UIAction {

    private RemoveDetailService removeDetailService;
    private GetAllDetailsService getAllDetailsService;

    public RemoveDetailUIAction(RemoveDetailService removeDetailService, GetAllDetailsService getAllDetailsService) {
        this.removeDetailService = removeDetailService;
        this.getAllDetailsService = getAllDetailsService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        getAllDetailsService.execute().forEach(System.out::println);

        System.out.println("Enter detail id to remove: ");
        Long detailId = Long.parseLong(scanner.nextLine());
        removeDetailService.execute(detailId);
        System.out.println("Your detail was removed from list.");
    }

}
