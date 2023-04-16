package com.fightclub.core.inbound;

import com.fightclub.core.domain.command.RegisterCommand;
import com.fightclub.core.domain.command.RegisterResult;

public interface RegisterUserUseCase {

    RegisterResult register(RegisterCommand command);

}

