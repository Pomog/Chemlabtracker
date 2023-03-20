import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import database.Database;
import domain.user.CurrentUser;
import domain.user.UserRole;
import services.fake.FakeDatabaseInitializer;

public class Shop {

    public static void main(String[] args) {

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        UserCommunication userCommunication = new UserCommunication();

        CurrentUser currentUser = new CurrentUser(database.accessUserDatabase().findByRole(UserRole.GUEST).get().getId());

        UIActionsList uiActionsList = new UIActionsList(database, currentUser, userCommunication);
        UIMenu uiMenu = new UIMenu(uiActionsList, userCommunication);

        uiMenu.startUI();

    }

}
