package shop;

import shop.console_ui.UIActionsList;
import shop.console_ui.UIMenu;
import shop.console_ui.UserCommunication;
import shop.console_ui.actions.admin.ChangeUserDataUIAction;
import shop.console_ui.actions.customer.*;
import shop.console_ui.actions.guest.SignUpUIAction;
import shop.console_ui.actions.manager.AddItemToShopUIAction;
import shop.console_ui.actions.manager.ChangeItemDataUIAction;
import shop.console_ui.actions.shared.ExitUIAction;
import shop.console_ui.actions.shared.SearchItemUIAction;
import shop.console_ui.actions.shared.SignInUIAction;
import shop.console_ui.actions.shared.SignOutUIAction;
import shop.console_ui.item_list.ordering.OrderingUIElement;
import shop.console_ui.item_list.paging.PagingUIElement;
import shop.core.database.Database;
import shop.core.services.actions.admin.ChangeUserDataService;
import shop.core.services.actions.customer.*;
import shop.core.services.actions.guest.SignUpService;
import shop.core.services.actions.manager.AddItemToShopService;
import shop.core.services.actions.manager.ChangeItemDataService;
import shop.core.services.actions.shared.ExitService;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.services.cart.CartService;
import shop.core.services.item_list.ordering.OrderingService;
import shop.core.services.item_list.paging.PagingService;
import shop.core.services.user.UserService;
import shop.core.services.validators.actions.customer.AddItemToCartValidator;
import shop.core.services.validators.actions.customer.BuyValidator;
import shop.core.services.validators.actions.customer.ListCartItemValidator;
import shop.core.services.validators.actions.customer.RemoveItemFromCartValidator;
import shop.core.services.validators.actions.guest.SignUpValidator;
import shop.core.services.validators.actions.manager.AddItemToShopValidator;
import shop.core.services.validators.actions.manager.ChangeItemDataValidator;
import shop.core.services.validators.actions.shared.SearchItemValidator;
import shop.core.services.validators.actions.shared.SignInValidator;
import shop.core.services.validators.actions.shared.SignOutValidator;
import shop.core.services.validators.cart.CartValidator;
import shop.core.services.validators.item_list.OrderingRuleValidator;
import shop.core.services.validators.item_list.PagingRuleValidator;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.support.CurrentUserId;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new Database());
        beans.put(CurrentUserId.class, new CurrentUserId());

        beans.put(UserCommunication.class, new UserCommunication());

        initialiseValidators();
        initialiseServices();
        initialiseUIActions();

        beans.put(UIActionsList.class, new UIActionsList(this));
        beans.put(UIMenu.class, new UIMenu(
                getBean(UIActionsList.class),
                getBean(DatabaseAccessValidator.class),
                getBean(UserCommunication.class)
        ));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

    private void initialiseValidators() {
        beans.put(CurrentUserIdValidator.class, new CurrentUserIdValidator());
        beans.put(InputStringValidator.class, new InputStringValidator());
        beans.put(OrderingRuleValidator.class, new OrderingRuleValidator());
        beans.put(PagingRuleValidator.class, new PagingRuleValidator(
                getBean(InputStringValidator.class)
        ));
        beans.put(DatabaseAccessValidator.class, new DatabaseAccessValidator(
                getBean(Database.class)
        ));
        beans.put(CartValidator.class, new CartValidator(
                getBean(Database.class)
        ));
        beans.put(SearchItemValidator.class, new SearchItemValidator(
                getBean(InputStringValidator.class),
                getBean(OrderingRuleValidator.class),
                getBean(PagingRuleValidator.class)
        ));
        beans.put(AddItemToCartValidator.class, new AddItemToCartValidator(
                getBean(Database.class),
                getBean(CurrentUserIdValidator.class),
                getBean(CartValidator.class),
                getBean(InputStringValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(RemoveItemFromCartValidator.class, new RemoveItemFromCartValidator(
                getBean(Database.class),
                getBean(CurrentUserIdValidator.class),
                getBean(CartValidator.class),
                getBean(InputStringValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(ListCartItemValidator.class, new ListCartItemValidator(
                getBean(CurrentUserIdValidator.class),
                getBean(CartValidator.class)
        ));
        beans.put(BuyValidator.class, new BuyValidator(
                getBean(Database.class),
                getBean(CurrentUserIdValidator.class),
                getBean(CartValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(AddItemToShopValidator.class, new AddItemToShopValidator(
                getBean(Database.class),
                getBean(InputStringValidator.class)
        ));
        beans.put(ChangeItemDataValidator.class, new ChangeItemDataValidator(
                getBean(Database.class),
                getBean(InputStringValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(SignInValidator.class, new SignInValidator(
                getBean(Database.class),
                getBean(CurrentUserIdValidator.class),
                getBean(InputStringValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(SignUpValidator.class, new SignUpValidator(
                getBean(Database.class),
                getBean(CurrentUserIdValidator.class),
                getBean(InputStringValidator.class)
        ));
        beans.put(SignOutValidator.class, new SignOutValidator(
                getBean(CurrentUserIdValidator.class)
        ));
    }

    private void initialiseServices() {
        beans.put(UserService.class, new UserService(
                getBean(Database.class)
        ));
        beans.put(OrderingService.class, new OrderingService());
        beans.put(PagingService.class, new PagingService());
        beans.put(CartService.class, new CartService(
                getBean(Database.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(ListShopItemsService.class, new ListShopItemsService(
                getBean(Database.class)
        ));
        beans.put(SearchItemService.class, new SearchItemService(
                getBean(Database.class),
                getBean(SearchItemValidator.class),
                getBean(OrderingService.class),
                getBean(PagingService.class)
        ));
        beans.put(AddItemToCartService.class, new AddItemToCartService(
                getBean(Database.class),
                getBean(AddItemToCartValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(RemoveItemFromCartService.class, new RemoveItemFromCartService(
                getBean(Database.class),
                getBean(RemoveItemFromCartValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(ListCartItemsService.class, new ListCartItemsService(
                getBean(Database.class),
                getBean(ListCartItemValidator.class),
                getBean(DatabaseAccessValidator.class),
                getBean(CartService.class)
        ));
        beans.put(BuyService.class, new BuyService(
                getBean(Database.class),
                getBean(BuyValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(AddItemToShopService.class, new AddItemToShopService(
                getBean(Database.class),
                getBean(AddItemToShopValidator.class)
        ));
        beans.put(ChangeItemDataService.class, new ChangeItemDataService(
                getBean(Database.class),
                getBean(ChangeItemDataValidator.class)
        ));
        beans.put(ChangeUserDataService.class, new ChangeUserDataService(
                getBean(Database.class)
        ));
        beans.put(SignInService.class, new SignInService(
                getBean(SignInValidator.class),
                getBean(DatabaseAccessValidator.class)
        ));
        beans.put(SignUpService.class, new SignUpService(
                getBean(SignUpValidator.class),
                getBean(UserService.class)
        ));
        beans.put(SignOutService.class, new SignOutService(
                getBean(SignOutValidator.class),
                getBean(UserService.class)
        ));
        beans.put(ExitService.class, new ExitService());
    }

    private void initialiseUIActions() {
        beans.put(OrderingUIElement.class, new OrderingUIElement(
                getBean(UserCommunication.class)
        ));
        beans.put(PagingUIElement.class, new PagingUIElement(
                getBean(UserCommunication.class)
        ));
        beans.put(ListShopItemsUIAction.class, new ListShopItemsUIAction(
                getBean(ListShopItemsService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SearchItemUIAction.class, new SearchItemUIAction(
                getBean(SearchItemService.class),
                getBean(OrderingUIElement.class),
                getBean(PagingUIElement.class),
                getBean(UserCommunication.class)
        ));
        beans.put(AddItemToCartUIAction.class, new AddItemToCartUIAction(
                getBean(AddItemToCartService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(RemoveItemFromCartUIAction.class, new RemoveItemFromCartUIAction(
                getBean(RemoveItemFromCartService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ListCartItemsUIAction.class, new ListCartItemsUIAction(
                getBean(ListCartItemsService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(BuyUIAction.class, new BuyUIAction(
                getBean(BuyService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(AddItemToShopUIAction.class, new AddItemToShopUIAction(
                getBean(AddItemToShopService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ChangeItemDataUIAction.class, new ChangeItemDataUIAction(
                getBean(ChangeItemDataService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ChangeUserDataUIAction.class, new ChangeUserDataUIAction(
                getBean(ChangeUserDataService.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignInUIAction.class, new SignInUIAction(
                getBean(SignInService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignUpUIAction.class, new SignUpUIAction(
                getBean(SignUpService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(SignOutUIAction.class, new SignOutUIAction(
                getBean(SignOutService.class),
                getBean(CurrentUserId.class),
                getBean(UserCommunication.class)
        ));
        beans.put(ExitUIAction.class, new ExitUIAction(
                getBean(ExitService.class),
                getBean(UserCommunication.class)
        ));
    }

}
