package console_ui;

import console_ui.actions.UIAction;
import console_ui.actions.admin.ChangeUserDataUIAction;
import console_ui.actions.common.ShopExitUIAction;
import console_ui.actions.manager.AddItemToShopUIAction;
import console_ui.actions.manager.ChangeItemDataUIAction;
import console_ui.actions.shop.*;
import console_ui.actions.welcome_screen.ShopEntranceUIAction;
import console_ui.actions.welcome_screen.SignInUIAction;
import console_ui.actions.welcome_screen.SignUpUIAction;
import database.Database;
import domain.user.User;
import domain.user.UserRole;
import services.actions.admin.ChangeUserDataService;
import services.actions.common.ShopExitService;
import services.actions.manager.AddItemToShopService;
import services.actions.manager.ChangeItemDataService;
import services.actions.shop.*;
import services.actions.welcome_screen.ShopEntranceService;
import services.actions.welcome_screen.SignInService;
import services.actions.welcome_screen.SignUpService;

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
        Optional<User> user = database.accessUserDatabase().findById(this.user.getId());
        UserRole filterRole = user.isEmpty() ? UserRole.GUEST : user.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList() {
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ShopEntranceUIAction(new ShopEntranceService(user), userCommunication));
        uiActions.add(new SignInUIAction(new SignInService(database, user), userCommunication));
        uiActions.add(new SignUpUIAction(new SignUpService(user), userCommunication));
        uiActions.add(new ShopExitUIAction(new ShopExitService(), userCommunication));
        uiActions.add(new ListShopItemsUIAction(new ListShopItemsService(database), userCommunication));
        uiActions.add(new AddItemToCartUIAction(new AddItemToCartService(database, user.getId()), userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(new RemoveItemFromCartService(database, user.getId()), userCommunication));
        uiActions.add(new ListCartItemsUIAction(new ListCartItemsService(database, user.getId()), userCommunication));
        uiActions.add(new BuyUIAction(new BuyService(database, user.getId()), userCommunication));
        uiActions.add(new AddItemToShopUIAction(new AddItemToShopService(database), userCommunication));
        uiActions.add(new ChangeItemDataUIAction(new ChangeItemDataService(database), userCommunication));
        uiActions.add(new ChangeUserDataUIAction(new ChangeUserDataService(database), userCommunication));
        //uiActions.add(new SubMenuExitUIAction());//TODO yeetable?
        return uiActions;
    }

}
