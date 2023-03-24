import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import core.database.Database;
import core.domain.user.UserRole;
import core.services.fake.FakeDatabaseInitializer;
import core.support.MutableLong;

public class Shop {

    public static void main(String[] args) {

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        //TODO check optional, create if empty
        /* shortcuts for tests */
        MutableLong currentUserId = new MutableLong(database.accessUserDatabase().findByRole(UserRole.GUEST).get().getId());
//        MutableLong currentUserId = new MutableLong(database.accessUserDatabase().findByRole(UserRole.CUSTOMER).get().getId());
//        MutableLong currentUserId = new MutableLong(database.accessUserDatabase().findByRole(UserRole.MANAGER).get().getId());
//        MutableLong currentUserId = new MutableLong(database.accessUserDatabase().findByRole(UserRole.ADMIN).get().getId());

        UserCommunication userCommunication = new UserCommunication();

        UIMenu uiMenu = new UIMenu(new UIActionsList(database, currentUserId, userCommunication), userCommunication);

        uiMenu.startUI();

    }

}
