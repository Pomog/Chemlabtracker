package java2.eln.console_ui;

import java2.eln.core.requests.FindReactionRequest;
import java2.eln.core.responses.FindReactionResponse;
import java2.eln.core.services.FindReactionService;
import java2.eln.core.services.GetStructureFromSMILE;
import java2.eln.core.domain.StructureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindReactionUIAction implements UIAction{

    @Autowired
    FindReactionService findReactionService;

//    public FindReactionUIAction(FindReactionService findReactionService) {
//        this.findReactionService = findReactionService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter reaction Code to search: ");
        String  code = scanner.nextLine();

        System.out.println("Enter reaction Name to search: ");
        String  name = scanner.nextLine();

        System.out.println("Enter Starting Material SMILE to search: ");
        String  smile = scanner.nextLine();

        System.out.println("Enter reaction Yield to search: ");
        String  yieldStr = scanner.nextLine();
        Double yield = 0d;
        if (!yieldStr.isBlank()){
            yield = Double.parseDouble(yieldStr);
        }

        GetStructureFromSMILE getStructureFromSMILE = new GetStructureFromSMILE(smile);
        StructureData searchedStructure = getStructureFromSMILE.execute();

        FindReactionRequest findReactionRequest =
                new FindReactionRequest(code, name, searchedStructure,yield);

        FindReactionResponse findReactionResponse =
                findReactionService.execute(findReactionRequest);

        if (findReactionResponse.hasErrors()) {
            findReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Search results");
            findReactionResponse.getSearchingResults().forEach(System.out::println);
            System.out.println("Reactions log end.");
        }


    }
}
