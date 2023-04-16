package com.fightclub.out.registration;

import com.fightclub.core.domain.User;
import com.fightclub.core.outbound.SaveUserPort;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationUserInMemory implements SaveUserPort {


    private Map<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public Long save(User user) {

        Long id = generateID();
        user.setId(id);
        users.put(id, user);

        return id;
    }

    private Long generateID() {

        return (long) users.size();
    }
}
