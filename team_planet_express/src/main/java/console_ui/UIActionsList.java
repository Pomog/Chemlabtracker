package console_ui;

import console_ui.actions.*;
import database.Database;
import database.UserDatabase;
import domain.user.CurrentUser;
import domain.user.User;
import domain.user.UserRole;
import services.actions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final UserDatabase userDatabase;
    private final CurrentUser currentUser;
    private final List<UIAction> uiActionsList;

    public UIActionsList(Database database, CurrentUser currentUser, UserCommunication userCommunication) {
        this.userDatabase = database.accessUserDatabase();
        this.currentUser = currentUser;
        this.uiActionsList = List.of(
                (new ListShopItemsUIAction(new ListShopItemsService(database), userCommunication)),
                (new AddItemToCartUIAction(new AddItemToCartService(database, currentUser.getUserId()), userCommunication)),
                (new RemoveItemFromCartUIAction(new RemoveItemFromCartService(database, currentUser.getUserId()), userCommunication)),
                (new ListCartItemsUIAction(new ListCartItemsService(database, currentUser.getUserId()), userCommunication)),
                (new BuyUIAction(new BuyService(database, currentUser.getUserId()), userCommunication)),
                (new LoginUIAction(new LoginService(database, currentUser), userCommunication)),
                (new ExitUIAction(new ExitService(), userCommunication)));
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Optional<User> user = userDatabase.findById(currentUser.getUserId());
        UserRole filterRole = user.isEmpty() ? UserRole.GUEST : user.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

}
