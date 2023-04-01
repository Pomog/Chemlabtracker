package com.fightclub.in.rest;

import com.fightclub.core.domain.command.RegisterCommand;
import com.fightclub.core.domain.command.RegisterResult;
import com.fightclub.core.inbound.RegisterUserUseCase;
import com.fightclub.in.rest.api.RegistrationRequest;
import com.fightclub.in.rest.api.RegistrationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegisterUserUseCase registerUserUseCase;

    public RegistrationController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/registration")
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {

        RegisterResult result = registerUserUseCase.register(convertToCommand(request));

        return convertToResponse(result);
    }

    private RegisterCommand convertToCommand(RegistrationRequest request) {

        return new RegisterCommand(
                request.getName(),
                request.getPassword(),
                request.getRepeatPassword(),
                request.getEmail());
    }

    private RegistrationResponse convertToResponse(RegisterResult result) {

        return new RegistrationResponse(result.getUserId(), null);
    }
}
