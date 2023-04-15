package com.fightclub.core.outbound;

import com.fightclub.core.domain.User;

public interface ReadUserPort {

    User readById(Long id);
}
