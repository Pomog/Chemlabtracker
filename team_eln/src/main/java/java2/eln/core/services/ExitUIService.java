package java2.eln.core.services;

import java2.eln.core.requests.ExitUIActionRequest;
import java2.eln.core.responses.ExitUIActionResponse;
import java2.eln.dependency_injection.DIComponent;

@DIComponent
public class ExitUIService {

    public ExitUIActionResponse execute (ExitUIActionRequest exitUIActionRequest){
        return new ExitUIActionResponse();
    }
}
