package shop;

import shop.console_ui.UIMenu;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;

public class ShopApplication {

    public static final String BLANK = "";

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ApplicationContext();

        new FakeDatabaseInitializer(applicationContext.getBean(Database.class)).initialize();

        UserService userService = applicationContext.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());

        UIMenu uiMenu = applicationContext.getBean(UIMenu.class);
        uiMenu.startUI();

    }

}
