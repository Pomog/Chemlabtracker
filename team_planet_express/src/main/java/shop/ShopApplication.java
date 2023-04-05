package shop;

import shop.console_ui.UIActionsList;
import shop.console_ui.UIMenu;
import shop.console_ui.UserCommunication;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

public class ShopApplication {

    public static final String BLANK = "";

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ApplicationContext();

        new FakeDatabaseInitializer(applicationContext.getBean(Database.class)).initialize();

        UserService userService = applicationContext.getBean(UserService.class);
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

        //TODO should it be in ApplicationContext ?
        UIActionsList uiActionList = new UIActionsList(applicationContext);
        DatabaseAccessValidator databaseAccessValidator = applicationContext.getBean(DatabaseAccessValidator.class);
        UIMenu uiMenu = new UIMenu(uiActionList, databaseAccessValidator, applicationContext.getBean(UserCommunication.class));
        uiMenu.startUI();

    }

}
