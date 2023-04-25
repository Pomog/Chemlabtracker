package java2.eln.console_ui;

import java2.eln.core.requests.FindReactionsByMainProductRequest;
import java2.eln.core.responses.FindReactionsByMainProductResponse;
import java2.eln.core.services.FindReactionsByMainProductService;
import java2.eln.core.services.GetStructureFromSMILE;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;
import java2.eln.domain.StructureData;

import java.util.Scanner;

@DIComponent
public class FindReactionByMainProductUIAction implements UIAction{

    @DIDependency
    FindReactionsByMainProductService findReactionByMainProductService;

//    public FindReactionByMainProductUIAction(FindReactionsByMainProductService findReactionByMainProductService) {
//        this.findReactionByMainProductService = findReactionByMainProductService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Main Product SMILE to search: ");
        String  smile = scanner.nextLine();

        GetStructureFromSMILE getStructureFromSMILE = new GetStructureFromSMILE(smile);
        StructureData searchedStructure = getStructureFromSMILE.execute();

        FindReactionsByMainProductRequest findReactionsByMainProductRequest =
                new FindReactionsByMainProductRequest(searchedStructure);
        FindReactionsByMainProductResponse findReactionsByMainProductResponse =
                findReactionByMainProductService.execute(findReactionsByMainProductRequest);

        findReactionsByMainProductResponse.getSearchingResults().forEach(System.out::println);
    }
}
