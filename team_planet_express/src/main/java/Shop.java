import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import database.Database;
import domain.user.User;
import domain.user.UserRole;
import services.fake.FakeDatabaseInitializer;

public class Shop {

    public static void main(String[] args) {

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        User user = new User(UserRole.GUEST);

        UserCommunication userCommunication = new UserCommunication();

        UIMenu uiMenu = new UIMenu(new UIActionsList(database, user, userCommunication), userCommunication);

        uiMenu.startUI();

    }

}
