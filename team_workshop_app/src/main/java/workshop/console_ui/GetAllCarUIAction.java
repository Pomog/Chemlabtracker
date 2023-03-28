package workshop.console_ui;

import workshop.core.requests.GetAllCarRequest;
import workshop.core.responses.GetAllCarResponse;
import workshop.core.services.GetAllCarService;

public class GetAllCarUIAction implements UIAction {
    private GetAllCarService getAllCarService;

    public GetAllCarUIAction(GetAllCarService getAllCarService) {
        this.getAllCarService = getAllCarService;
    }

    @Override
    public void execute() {

            System.out.println("workshop.Car list: ");
            GetAllCarRequest request = new GetAllCarRequest();
            GetAllCarResponse response = getAllCarService.execute(request);
            response.getCars().forEach(System.out::println);
            System.out.println("workshop.Car list end.");


    }
}
