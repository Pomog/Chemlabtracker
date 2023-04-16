package com.fightclub.core.service;

import com.fightclub.core.domain.User;
import com.fightclub.core.domain.command.RegisterCommand;
import com.fightclub.core.domain.command.RegisterResult;
import com.fightclub.core.outbound.SaveUserPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private ValidationService validationService;
    @Mock
    private PasswordHashingService passwordHashingService;
    @Mock
    private SaveUserPort saveUserPort;
    @InjectMocks
    private RegistrationService victim;

    @Test
    public void test() {

        RegisterCommand registerCommand = new RegisterCommand("Vasja", "111", "111", "vasja@");
        when(passwordHashingService.hashPassword("111"))
                .thenReturn("123");
        User vasja = new User(null, "Vasja", "123", "vasja@", List.of());
        when(saveUserPort.save(vasja))
                .thenReturn(12334L);
        RegisterResult registerResult = victim.register(registerCommand);
        assertEquals(Long.valueOf(12334L), registerResult.getUserId());

    }
}