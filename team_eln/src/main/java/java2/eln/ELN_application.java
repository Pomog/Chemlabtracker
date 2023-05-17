package java2.eln;

import java2.eln.config.ELNconfiguration;
import java2.eln.console_ui.ProgramMenu;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ELN_application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = getApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        while (true) {
            programMenu.printProgramMenu();
            int menuNumber = programMenu.getMenuNumberFromUser();
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }

    @NotNull
    private static ApplicationContext getApplicationContext() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ELNconfiguration.class);
        return applicationContext;
    }

}

