package shop;

import shop.console_ui.UIMenu;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserRecord;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.ApplicationContext;
import shop.dependency_injection.DIApplicationContextBuilder;

public class ShopApplication {

    private static final String BLANK = "";

    public static void main(String[] args) {

        ApplicationContext applicationContext = new DIApplicationContextBuilder().build("shop");

        new FakeDatabaseInitializer(applicationContext.getBean(Database.class)).initialize();

        UserService userService = applicationContext.getBean(UserService.class);
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

        UIMenu uiMenu = applicationContext.getBean(UIMenu.class);
        uiMenu.startUI();

    }

}
