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
import core.services.exception.ServiceMissingDataException;
import core.services.validators.actions.customer.AddItemToCartValidator;
import core.services.validators.actions.customer.BuyValidator;
import core.services.validators.actions.customer.ListCartItemValidator;
import core.services.validators.actions.customer.RemoveItemFromCartValidator;
import core.services.validators.actions.guest.SignUpValidator;
import core.services.validators.actions.manager.AddItemToShopValidator;
import core.services.validators.actions.manager.ChangeItemDataValidator;
import core.services.validators.actions.shared.SearchItemValidator;
import core.services.validators.actions.shared.SignInValidator;
import core.services.validators.cart.CartValidator;
import core.services.validators.shared.PresenceValidator;
import core.services.validators.shared.number.NumberValidator;
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
        return getUserById(currentUserId.getValue()).getName();
    }

    private List<UIAction> createUIActionsList() {
        PresenceValidator presenceValidator = new PresenceValidator();
        NumberValidator numberValidator = new NumberValidator();
        CartValidator cartValidator = new CartValidator(database);
        SearchItemValidator searchItemValidator = new SearchItemValidator();
        AddItemToCartValidator addItemToCartValidator = new AddItemToCartValidator(database, cartValidator);
        RemoveItemFromCartValidator removeItemFromCartValidator = new RemoveItemFromCartValidator(database, cartValidator);
        ListCartItemValidator listCartItemValidator = new ListCartItemValidator(cartValidator);
        BuyValidator buyValidator = new BuyValidator(database, cartValidator);
        AddItemToShopValidator addItemToShopValidator = new AddItemToShopValidator(database, presenceValidator, numberValidator);
        ChangeItemDataValidator changeItemDataValidator = new ChangeItemDataValidator(database);
        SignInValidator signInValidator = new SignInValidator(database);
        SignUpValidator signUpValidator = new SignUpValidator(database);

        ListShopItemsService listShopItemsService = new ListShopItemsService(database);
        SearchItemService searchItemService = new SearchItemService(database, searchItemValidator);
        AddItemToCartService addItemToCartService = new AddItemToCartService(database, addItemToCartValidator);
        RemoveItemFromCartService removeItemFromCartService = new RemoveItemFromCartService(database, removeItemFromCartValidator);
        ListCartItemsService listCartItemsService = new ListCartItemsService(database, listCartItemValidator);
        BuyService buyService = new BuyService(database, buyValidator);
        AddItemToShopService addItemToShopService = new AddItemToShopService(database, addItemToShopValidator);
        ChangeItemDataService changeItemDataService = new ChangeItemDataService(database, changeItemDataValidator);
        ChangeUserDataService changeUserDataService = new ChangeUserDataService(database);
        SignInService signInService = new SignInService(database, signInValidator);
        SignUpService signUpService = new SignUpService(database, signUpValidator);
        SignOutService signOutService = new SignOutService(database);
        ExitService exitService = new ExitService();

        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(listShopItemsService, userCommunication));
        uiActions.add(new SearchItemUIAction(searchItemService, userCommunication));
        uiActions.add(new AddItemToCartUIAction(addItemToCartService, currentUserId, userCommunication));
        uiActions.add(new RemoveItemFromCartUIAction(removeItemFromCartService, currentUserId, userCommunication));
        uiActions.add(new ListCartItemsUIAction(listCartItemsService, currentUserId, userCommunication));
        uiActions.add(new BuyUIAction(buyService, currentUserId, userCommunication));
        uiActions.add(new AddItemToShopUIAction(addItemToShopService, userCommunication));
        uiActions.add(new ChangeItemDataUIAction(changeItemDataService, userCommunication));
        uiActions.add(new ChangeUserDataUIAction(changeUserDataService, userCommunication));
        uiActions.add(new SignInUIAction(signInService, currentUserId, userCommunication));
        uiActions.add(new SignUpUIAction(signUpService, currentUserId, userCommunication));
        uiActions.add(new SignOutUIAction(signOutService, currentUserId, userCommunication));
        uiActions.add(new ExitUIAction(exitService, userCommunication));

        return uiActions;
    }

    //TODO yeet, duplicate
    private User getUserById(Long userId) {
        return database.accessUserDatabase().findById(userId)
                .orElseThrow(ServiceMissingDataException::new);
    }

}
