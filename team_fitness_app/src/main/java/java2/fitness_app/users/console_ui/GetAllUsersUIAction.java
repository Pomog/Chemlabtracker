package java2.fitness_app.users.console_ui;

import java2.fitness_app.users.core.requests.GetAllUsersRequest;
import java2.fitness_app.users.core.responses.GetAllUsersResponse;
import java2.fitness_app.users.core.services.GetAllUsersService;
import java2.fitness_app.dependency_injection.DIComponent;
import java2.fitness_app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUIAction implements UIAction {

    @Autowired private GetAllUsersService getUsersService;

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = getUsersService.execute(request);
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }

}
