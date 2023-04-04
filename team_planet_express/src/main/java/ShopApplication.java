import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.fake.FakeDatabaseInitializer;
import core.services.user.UserRecord;
import core.services.user.UserService;
import core.services.validators.universal.system.DatabaseAccessValidator;
import core.support.CurrentUserId;

public class ShopApplication {

    public static final String BLANK = "";

    public static void main(String[] args) {

        //TODO appContext should do all of those "new" at the beginning
        //TODO we got duplicates of them

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        UserService userService = new UserService(database);
        UserRecord userRecord = new UserRecord(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userRecord));
        CurrentUserId currentUserId = new CurrentUserId(currentUser.getId());

        UserCommunication userCommunication = new UserCommunication();

        UIActionsList uiActionList = new UIActionsList(database, currentUserId, userCommunication);
        DatabaseAccessValidator databaseAccessValidator = new DatabaseAccessValidator(database);
        UIMenu uiMenu = new UIMenu(uiActionList, databaseAccessValidator, userCommunication);

        uiMenu.startUI();

    }

}
