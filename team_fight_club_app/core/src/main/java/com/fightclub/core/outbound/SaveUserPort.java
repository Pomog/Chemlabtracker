package com.fightclub.core.outbound;

import com.fightclub.core.domain.User;

public interface SaveUserPort {

    Long save(User user);
}
