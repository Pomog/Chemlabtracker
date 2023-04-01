package com.fightclub.app;

import com.fightclub.core.inbound.RegisterUserUseCase;
import com.fightclub.core.outbound.SaveUserPort;
import com.fightclub.core.service.PasswordHashingService;
import com.fightclub.core.service.RegistrationService;
import com.fightclub.core.service.ValidationService;
import com.fightclub.out.registration.RegistrationUserInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RegisterUserUseCase registerUserUseCase() {

        ValidationService validationService = new ValidationService();
        PasswordHashingService passwordHashingService = new PasswordHashingService();
        SaveUserPort saveUserPort = new RegistrationUserInMemory();

        return new RegistrationService(validationService, passwordHashingService, saveUserPort);
    }
}
