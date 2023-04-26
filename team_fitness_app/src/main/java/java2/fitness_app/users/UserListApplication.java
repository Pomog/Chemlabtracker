package java2.fitness_app.users;


import org.springframework.context.ApplicationContext;
import java2.fitness_app.config.UserListConfiguration;
import java2.fitness_app.users.console_ui.ProgramMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UserListApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        while (true) {
            programMenu.print();
            int menuNumber = programMenu.getMenuNumberFromUser();
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(UserListConfiguration.class);
    }
}