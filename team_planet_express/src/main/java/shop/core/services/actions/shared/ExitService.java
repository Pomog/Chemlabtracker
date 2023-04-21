package shop.core.services.actions.shared;

import shop.dependency_injection.DIComponent;

@DIComponent
public class ExitService {

    public void execute() {
        System.exit(0);
    }

}
