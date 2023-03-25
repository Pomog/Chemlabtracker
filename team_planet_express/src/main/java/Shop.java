import console_ui.UIActionsList;
import console_ui.UIMenu;
import console_ui.UserCommunication;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.fake.FakeDatabaseInitializer;
import core.support.MutableLong;

import java.util.Optional;

public class Shop {

    public static void main(String[] args) {

        Database database = new Database();
        new FakeDatabaseInitializer(database).initialize();

        User currentUser;
        //TODO yeet aka for quicker tests
        UserRole currentRole = UserRole.GUEST;
//        UserRole currentRole = UserRole.CUSTOMER;
//        UserRole currentRole = UserRole.MANAGER;
//        UserRole currentRole = UserRole.ADMIN;
//        UserRole currentRole = UserRole.ALLUSERS;
        Optional<User> user = database.accessUserDatabase().findByRole(currentRole);
        currentUser = user.orElseGet(() ->
                database.accessUserDatabase().save(new User(currentRole.getDefaultName(), "", "", currentRole)));
        MutableLong currentUserId = new MutableLong(currentUser.getId());

        UserCommunication userCommunication = new UserCommunication();

        UIActionsList uiActionList = new UIActionsList(database, currentUserId, userCommunication);
        UIMenu uiMenu = new UIMenu(uiActionList, userCommunication);

        uiMenu.startUI();

    }

}
