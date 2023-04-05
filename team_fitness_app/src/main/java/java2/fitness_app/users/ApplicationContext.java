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
        beans.put(LoginUserValidator.class, new LoginUserValidator());
        beans.put(RemoveUserValidator.class, new RemoveUserValidator());

        beans.put(AddUserService.class, new AddUserService(
                getBean(Database.class),
                getBean(AddUserRequestValidator.class)));

        beans.put(LoginUserService.class, new LoginUserService(
                getBean(Database.class),
                getBean(LoginUserValidator.class)));

        beans.put(RemoveUserService.class, new RemoveUserService(
                getBean(Database.class),
                getBean(RemoveUserValidator.class)));

        beans.put(GetAllUsersService.class, new GetAllUsersService(getBean(Database.class)));

        beans.put(AddUserUIAction.class, new AddUserUIAction(getBean(AddUserService.class)));
        beans.put(LoginUserUIAction.class, new LoginUserUIAction(getBean(LoginUserService.class)));
        beans.put(RemoveUserUIAction.class, new RemoveUserUIAction(getBean(RemoveUserService.class)));
        beans.put(GetAllUsersUIAction.class, new GetAllUsersUIAction(getBean(GetAllUsersService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}