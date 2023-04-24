package java2.eln.console_ui;

import java2.eln.core.requests.ExitUIActionRequest;
import java2.eln.core.responses.ExitUIActionResponse;
import java2.eln.core.services.ExitUIService;
import java2.eln.dependency_injection.DIComponent;
import java2.eln.dependency_injection.DIDependency;

@DIComponent
public class ExitUIAction implements UIAction{

    @DIDependency
    ExitUIService exitUIService;

//    public ExitUIAction(ExitUIService exitUIService) {
//        this.exitUIService = exitUIService;
//    }

    @Override
    public void execute() {
        System.out.println("Good by!");
        ExitUIActionRequest exitUIActionRequest =
                new ExitUIActionRequest();
        ExitUIActionResponse exitUIActionResponse =
                exitUIService.execute(exitUIActionRequest);
        System.exit(0);
    }
}
