import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.fake.FakeDatabaseInitializer;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.support.MutableLong;

public class Shop {

    public static final String BLANK = "";

    public static void main(String[] args) {

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        UserService userService = new UserService(database);
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        MutableLong currentUserId = new MutableLong(currentUser.getId());

        UserCommunication userCommunication = new UserCommunication();

        UIActionsList uiActionList = new UIActionsList(database, currentUserId, userCommunication);
        UIMenu uiMenu = new UIMenu(uiActionList, userCommunication);

        uiMenu.startUI();

    }

}
