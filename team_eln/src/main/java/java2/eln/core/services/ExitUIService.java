package java2.eln.core.services;

import java2.eln.core.requests.ExitUIActionRequest;
import java2.eln.core.responses.ExitUIActionResponse;
import org.springframework.stereotype.Component;

@Component
public class ExitUIService {

    public ExitUIActionResponse execute (ExitUIActionRequest exitUIActionRequest){
        return new ExitUIActionResponse();
    }
}
