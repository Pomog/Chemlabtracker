package java2.eln.console_ui;

import java2.eln.core.services.FindReactionByMainProductService;
import java2.eln.core.services.GetStructureFromSMILE;
import java2.eln.domain.StructureData;

import java.util.Scanner;

public class FindReactionByMainProductUIAction implements UIAction{
    private FindReactionByMainProductService findReactionByMainProductService;

    public FindReactionByMainProductUIAction(FindReactionByMainProductService findReactionByMainProductService) {
        this.findReactionByMainProductService = findReactionByMainProductService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Main Product SMILE to search: ");
        String  smile = scanner.nextLine();

        GetStructureFromSMILE getStructureFromSMILE = new GetStructureFromSMILE(smile);

        StructureData searchedStructure = getStructureFromSMILE.execute();

        System.out.println("Search Results :");
        findReactionByMainProductService.execute(searchedStructure).forEach(System.out::println);
    }
}
