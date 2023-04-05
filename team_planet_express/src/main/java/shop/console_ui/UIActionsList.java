package shop.console_ui;

import shop.ApplicationContext;
import shop.console_ui.actions.UIAction;
import shop.console_ui.actions.admin.ChangeUserDataUIAction;
import shop.console_ui.actions.customer.*;
import shop.console_ui.actions.guest.SignUpUIAction;
import shop.console_ui.actions.manager.AddItemToShopUIAction;
import shop.console_ui.actions.manager.ChangeItemDataUIAction;
import shop.console_ui.actions.shared.ExitUIAction;
import shop.console_ui.actions.shared.SearchItemUIAction;
import shop.console_ui.actions.shared.SignInUIAction;
import shop.console_ui.actions.shared.SignOutUIAction;
import shop.core.database.Database;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.support.CurrentUserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UIActionsList {

    private final ApplicationContext applicationContext;
    private final List<UIAction> uiActionsList;

    public UIActionsList(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.uiActionsList = createUIActionsList(applicationContext);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }

    public List<UIAction> getUIActionsListForUserRole() {
        Database database = applicationContext.getBean(Database.class);
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        Optional<User> currentUser = database.accessUserDatabase().findById(currentUserId.getValue());
        UserRole filterRole = currentUser.isEmpty() ? UserRole.GUEST : currentUser.get().getUserRole();
        return uiActionsList.stream()
                .filter(uiAction -> filterRole.checkPermission(uiAction.getAccessNumber()))
                .collect(Collectors.toList());
    }

    private List<UIAction> createUIActionsList(ApplicationContext applicationContext) {
        List<UIAction> uiActions = new ArrayList<>();
        uiActions.add(applicationContext.getBean(ListShopItemsUIAction.class));
        uiActions.add(applicationContext.getBean(SearchItemUIAction.class));
        uiActions.add(applicationContext.getBean(AddItemToCartUIAction.class));
        uiActions.add(applicationContext.getBean(RemoveItemFromCartUIAction.class));
        uiActions.add(applicationContext.getBean(ListCartItemsUIAction.class));
        uiActions.add(applicationContext.getBean(BuyUIAction.class));
        uiActions.add(applicationContext.getBean(AddItemToShopUIAction.class));
        uiActions.add(applicationContext.getBean(ChangeItemDataUIAction.class));
        uiActions.add(applicationContext.getBean(ChangeUserDataUIAction.class));
        uiActions.add(applicationContext.getBean(SignInUIAction.class));
        uiActions.add(applicationContext.getBean(SignUpUIAction.class));
        uiActions.add(applicationContext.getBean(SignOutUIAction.class));
        uiActions.add(applicationContext.getBean(ExitUIAction.class));
        return uiActions;
    }

}
