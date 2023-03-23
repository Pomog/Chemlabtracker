package console_ui;

import console_ui.actions.UIAction;
import console_ui.actions.admin.ChangeUserDataUIAction;
import console_ui.actions.customer.*;
import console_ui.actions.guest.SignUpUIAction;
import console_ui.actions.manager.AddItemToShopUIAction;
import console_ui.actions.manager.ChangeItemDataUIAction;
import console_ui.actions.shared.ExitUIAction;
import console_ui.actions.shared.SignInUIAction;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.actions.admin.ChangeUserDataService;
import core.services.actions.customer.*;
import core.services.actions.guest.SignUpService;
import core.services.actions.manager.AddItemToShopService;
import core.services.actions.manager.ChangeItemDataService;
import core.services.actions.shared.ExitService;
import core.services.actions.shared.SignInService;
import core.services.validators.customer.AddItemToCartValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final Database database;
    private final User user;
    private final UserCommunication userCommunication;
    private final List<UIAction> uiActionsList;

    public UIActionsList(Database database, User user, UserCommunication userCommunication) {
        this.database = database;
        this.user = user;
        this.userCommunication = userCommunication;
        this.uiActionsList = createUIActionsList();
    }

    public List<UIAction> getUIActionsListForUserRole() {
        //TODO pass id maybe ?
        Optional<User> currentUser = database.accessUserDatabase().findById(user.getId());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList() {
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(new ListShopItemsService(database), userCommunication));
        uiActions.add(new AddItemToCartUIAction(new AddItemToCartService(database, new AddItemToCartValidator(database), user), userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(new RemoveItemFromCartService(database, user), userCommunication));
        uiActions.add(new ListCartItemsUIAction(new ListCartItemsService(database, user), userCommunication));
        uiActions.add(new BuyUIAction(new BuyService(database, user), userCommunication));
        uiActions.add(new AddItemToShopUIAction(new AddItemToShopService(database), userCommunication));
        uiActions.add(new ChangeItemDataUIAction(new ChangeItemDataService(database), userCommunication));
        uiActions.add(new ChangeUserDataUIAction(new ChangeUserDataService(database), userCommunication));
        uiActions.add(new SignInUIAction(new SignInService(database, user), userCommunication));
        uiActions.add(new SignUpUIAction(new SignUpService(user), userCommunication));
        uiActions.add(new ExitUIAction(new ExitService(), userCommunication));
        return uiActions;
    }

}
