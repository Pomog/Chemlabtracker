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
import console_ui.item_list.ordering.OrderingUIElement;
import console_ui.item_list.paging.PagingUIElement;
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
import core.services.cart.CartService;
import core.services.item.ordering.OrderingService;
import core.services.item.paging.PagingService;
import core.services.user.UserService;
import core.services.validators.actions.customer.AddItemToCartValidator;
import core.services.validators.actions.customer.BuyValidator;
import core.services.validators.actions.customer.ListCartItemValidator;
import core.services.validators.actions.customer.RemoveItemFromCartValidator;
import core.services.validators.actions.guest.SignUpValidator;
import core.services.validators.actions.manager.AddItemToShopValidator;
import core.services.validators.actions.manager.ChangeItemDataValidator;
import core.services.validators.actions.shared.SearchItemValidator;
import core.services.validators.actions.shared.SignInValidator;
import core.services.validators.actions.shared.SignOutValidator;
import core.services.validators.cart.CartValidator;
import core.services.validators.universal.system.DatabaseAccessValidator;
import core.services.validators.universal.system.MutableLongUserIdValidator;
import core.services.validators.universal.user_input.InputStringValidator;
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

    public MutableLong getCurrentUserId() {
        return currentUserId;
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList() {
        MutableLongUserIdValidator mutableLongUserIdValidator = new MutableLongUserIdValidator();
        InputStringValidator inputStringValidator = new InputStringValidator();
        CartValidator cartValidator = new CartValidator(database);
        DatabaseAccessValidator databaseAccessValidator = new DatabaseAccessValidator(database);
        SearchItemValidator searchItemValidator = new SearchItemValidator(inputStringValidator);
        AddItemToCartValidator addItemToCartValidator = new AddItemToCartValidator(database, mutableLongUserIdValidator, cartValidator, inputStringValidator, databaseAccessValidator);
        RemoveItemFromCartValidator removeItemFromCartValidator = new RemoveItemFromCartValidator(database, mutableLongUserIdValidator, cartValidator, inputStringValidator, databaseAccessValidator);
        ListCartItemValidator listCartItemValidator = new ListCartItemValidator(mutableLongUserIdValidator, cartValidator);
        BuyValidator buyValidator = new BuyValidator(database, mutableLongUserIdValidator, cartValidator, databaseAccessValidator);
        AddItemToShopValidator addItemToShopValidator = new AddItemToShopValidator(database, inputStringValidator);
        ChangeItemDataValidator changeItemDataValidator = new ChangeItemDataValidator(database, inputStringValidator, databaseAccessValidator);
        SignInValidator signInValidator = new SignInValidator(database, mutableLongUserIdValidator, inputStringValidator, databaseAccessValidator);
        SignUpValidator signUpValidator = new SignUpValidator(database, mutableLongUserIdValidator, inputStringValidator);
        SignOutValidator signOutValidator = new SignOutValidator(mutableLongUserIdValidator);

        UserService userService = new UserService(database);
        OrderingService orderingService = new OrderingService();
        PagingService pagingService = new PagingService();
        CartService cartService = new CartService(database, databaseAccessValidator);
        ListShopItemsService listShopItemsService = new ListShopItemsService(database);
        SearchItemService searchItemService = new SearchItemService(database, searchItemValidator, orderingService, pagingService);
        AddItemToCartService addItemToCartService = new AddItemToCartService(database, addItemToCartValidator, databaseAccessValidator);
        RemoveItemFromCartService removeItemFromCartService = new RemoveItemFromCartService(database, removeItemFromCartValidator, databaseAccessValidator);
        ListCartItemsService listCartItemsService = new ListCartItemsService(database, listCartItemValidator, databaseAccessValidator, cartService);
        BuyService buyService = new BuyService(database, buyValidator, databaseAccessValidator);
        AddItemToShopService addItemToShopService = new AddItemToShopService(database, addItemToShopValidator);
        ChangeItemDataService changeItemDataService = new ChangeItemDataService(database, changeItemDataValidator);
        ChangeUserDataService changeUserDataService = new ChangeUserDataService(database);
        SignInService signInService = new SignInService(signInValidator, databaseAccessValidator);
        SignUpService signUpService = new SignUpService(signUpValidator, userService);
        SignOutService signOutService = new SignOutService(signOutValidator, userService);
        ExitService exitService = new ExitService();

        OrderingUIElement orderingUIElement = new OrderingUIElement(userCommunication);
        PagingUIElement pagingUIElement = new PagingUIElement(userCommunication);
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(new ListShopItemsUIAction(listShopItemsService, userCommunication));
        uiActions.add(new SearchItemUIAction(searchItemService, orderingUIElement, pagingUIElement, userCommunication));
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

}
