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
import core.support.MutableLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final Database database;
    private final MutableLong currentUserId;
    private final UserCommunication userCommunication;
    private final List<UIAction> uiActionsList;

    public UIActionsList(Database database, MutableLong currentUserId, UserCommunication userCommunication) {
        this.database = database;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
        this.uiActionsList = createUIActionsList();
    }

    public List<UIAction> getUIActionsListForUserRole() {
        //TODO this should not be necessary
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList() {
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(new ListShopItemsService(database), userCommunication));
        uiActions.add(new AddItemToCartUIAction(new AddItemToCartService(database, new AddItemToCartValidator(database), currentUserId), userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(new RemoveItemFromCartService(database, currentUserId), userCommunication));
        uiActions.add(new ListCartItemsUIAction(new ListCartItemsService(database, currentUserId), userCommunication));
        uiActions.add(new BuyUIAction(new BuyService(database, currentUserId), userCommunication));
        uiActions.add(new AddItemToShopUIAction(new AddItemToShopService(database), userCommunication));
        uiActions.add(new ChangeItemDataUIAction(new ChangeItemDataService(database), userCommunication));
        uiActions.add(new ChangeUserDataUIAction(new ChangeUserDataService(database), userCommunication));
        uiActions.add(new SignInUIAction(new SignInService(database, currentUserId), userCommunication));
        uiActions.add(new SignUpUIAction(new SignUpService(currentUserId), userCommunication));
        uiActions.add(new ExitUIAction(new ExitService(), userCommunication));
        return uiActions;
    }

}
