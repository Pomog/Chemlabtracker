package com.fightclub.core.service;

import com.fightclub.core.domain.User;
import com.fightclub.core.domain.command.RegisterCommand;
import com.fightclub.core.domain.command.RegisterResult;
import com.fightclub.core.inbound.RegisterUserUseCase;
import com.fightclub.core.outbound.SaveUserPort;

import java.util.List;

public class RegistrationService implements RegisterUserUseCase {

    private final ValidationService validationService;
    private final PasswordHashingService passwordHashingService;
    private final SaveUserPort saveUserPort;

    public RegistrationService(ValidationService validationService,
                               PasswordHashingService passwordHashingService,
                               SaveUserPort saveUserPort) {
        this.validationService = validationService;
        this.passwordHashingService = passwordHashingService;
        this.saveUserPort = saveUserPort;
    }

    @Override
    public RegisterResult register(RegisterCommand command) {

        validationService.validateName(command.getName());
        validationService.validateEmail(command.getEmail());
        String hashPassword = passwordHashingService.hashPassword(command.getPassword());
        User user = buildUser(command, hashPassword);
        Long userId = saveUserPort.save(user);
        return new RegisterResult(userId);
    }

    private User buildUser(RegisterCommand command, String hashPassword) {

        return new User(null, command.getName(), hashPassword, command.getEmail(), List.of());
    }

}
