package console_ui;

import console_ui.actions.UIAction;
import console_ui.actions.admin.ChangeUserDataUIAction;
import console_ui.actions.customer.*;
import console_ui.actions.guest.SignUpUIAction;
import console_ui.actions.manager.AddItemToShopUIAction;
import console_ui.actions.manager.ChangeItemDataUIAction;
import console_ui.actions.shared.ExitUIAction;
import console_ui.actions.shared.SearchItemUIAction;
import console_ui.actions.shared.SignInUIAction;
import console_ui.actions.shared.SignOutUIAction;
import core.database.Database;
import core.domain.user.User;
import core.domain.user.UserRole;
import core.services.actions.admin.ChangeUserDataService;
import core.services.actions.customer.*;
import core.services.actions.guest.SignUpService;
import core.services.actions.manager.AddItemToShopService;
import core.services.actions.manager.ChangeItemDataService;
import core.services.actions.shared.ExitService;
import core.services.actions.shared.SearchItemService;
import core.services.actions.shared.SignInService;
import core.services.actions.shared.SignOutService;
import core.services.cart.CartValidator;
import core.services.validators.customer.AddItemToCartValidator;
import core.services.validators.customer.BuyValidator;
import core.services.validators.customer.ListCartItemValidator;
import core.services.validators.customer.RemoveItemFromCartValidator;
import core.services.validators.guest.SignUpValidator;
import core.services.validators.manager.AddItemToShopValidator;
import core.services.validators.manager.ChangeItemDataValidator;
import core.services.validators.shared.SearchItemValidator;
import core.services.validators.shared.SignInValidator;
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
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    public String getCurrentUserName() {
        return database.accessUserDatabase().findById(currentUserId.getValue()).get().getName();
    }

    private List<UIAction> createUIActionsList() {
        CartValidator cartValidator = new CartValidator(database);
        AddItemToCartValidator addItemToCartValidator = new AddItemToCartValidator(database, cartValidator);
        RemoveItemFromCartValidator removeItemFromCartValidator = new RemoveItemFromCartValidator(database, cartValidator);
        ListCartItemValidator listCartItemValidator = new ListCartItemValidator(cartValidator);
        BuyValidator buyValidator = new BuyValidator(database, cartValidator);
        AddItemToShopValidator addItemToShopValidator = new AddItemToShopValidator(database);
        ChangeItemDataValidator changeItemDataValidator = new ChangeItemDataValidator(database);
        SignInValidator signInValidator = new SignInValidator(database);
        SignUpValidator signUpValidator = new SignUpValidator(database);

        ListShopItemsService listShopItemsService = new ListShopItemsService(database);
        SearchItemService searchItemService = new SearchItemService(database, new SearchItemValidator());
        AddItemToCartService addItemToCartService = new AddItemToCartService(database, addItemToCartValidator, currentUserId);
        RemoveItemFromCartService removeItemFromCartService = new RemoveItemFromCartService(database, removeItemFromCartValidator, currentUserId);
        ListCartItemsService listCartItemsService = new ListCartItemsService(database, listCartItemValidator, currentUserId);
        //ListCartItemsService listCartItemsService = new ListCartItemsService(database, listCartItemValidator, currentUserId);
        BuyService buyService = new BuyService(database, buyValidator, currentUserId);
        AddItemToShopService addItemToShopService = new AddItemToShopService(database, addItemToShopValidator);
        ChangeItemDataService changeItemDataService = new ChangeItemDataService(database, changeItemDataValidator);
        ChangeUserDataService changeUserDataService = new ChangeUserDataService(database);
        SignInService signInService = new SignInService(database, signInValidator, currentUserId);
        SignUpService signUpService = new SignUpService(database, signUpValidator, currentUserId);
        SignOutService signOutService = new SignOutService(database, currentUserId);
        ExitService exitService = new ExitService();

        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(listShopItemsService, userCommunication));
        uiActions.add(new SearchItemUIAction(searchItemService, userCommunication));
        uiActions.add(new AddItemToCartUIAction(addItemToCartService, userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(removeItemFromCartService, userCommunication));
        uiActions.add(new ListCartItemsUIAction(listCartItemsService, userCommunication));
        uiActions.add(new BuyUIAction(buyService, userCommunication));
        uiActions.add(new AddItemToShopUIAction(addItemToShopService, userCommunication));
        uiActions.add(new ChangeItemDataUIAction(changeItemDataService, userCommunication));
        uiActions.add(new ChangeUserDataUIAction(changeUserDataService, userCommunication));
        uiActions.add(new SignInUIAction(signInService, userCommunication));
        uiActions.add(new SignUpUIAction(signUpService, userCommunication));
        uiActions.add(new SignOutUIAction(signOutService, userCommunication));
        uiActions.add(new ExitUIAction(exitService, userCommunication));

        return uiActions;
    }

}
