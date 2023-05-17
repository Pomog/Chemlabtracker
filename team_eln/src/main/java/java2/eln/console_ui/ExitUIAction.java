package java2.eln.console_ui;

import java2.eln.core.requests.ExitUIActionRequest;
import java2.eln.core.responses.ExitUIActionResponse;
import java2.eln.core.services.ExitUIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction{

    @Autowired
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
