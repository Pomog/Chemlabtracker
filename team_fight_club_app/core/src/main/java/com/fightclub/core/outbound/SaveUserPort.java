package com.fightclub.core.outbound;

import com.fightclub.core.domain.User;

public interface SaveUserPort {

    User save(User user);
}
