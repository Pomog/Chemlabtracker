package java2.fitness_app.users;

import java2.fitness_app.users.console_ui.*;
import java2.fitness_app.users.core.database.Database;
import java2.fitness_app.users.core.database.InMemoryDatabaseImpl;
import java2.fitness_app.users.core.services.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext(){
        beans.put(Database.class, new InMemoryDatabaseImpl());

        beans.put(AddUserRequestValidator.class, new AddUserRequestValidator());
        beans.put(LoginUserRequestValidator.class, new LoginUserRequestValidator());
        beans.put(RemoveUserRequestValidator.class, new RemoveUserRequestValidator());

        beans.put(AddUserService.class, new AddUserService());

        beans.put(LoginUserService.class, new LoginUserService());

        beans.put(RemoveUserService.class, new RemoveUserService());

        beans.put(GetAllUsersService.class, new GetAllUsersService());

        beans.put(AddUserUIAction.class, new AddUserUIAction());
        beans.put(LoginUserUIAction.class, new LoginUserUIAction());
        beans.put(RemoveUserUIAction.class, new RemoveUserUIAction());
        beans.put(GetAllUsersUIAction.class, new GetAllUsersUIAction());
        beans.put(ExitUIAction.class, new ExitUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}