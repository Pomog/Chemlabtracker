package shop.acceptance;

import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.requests.shared.SignInRequest;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.guest.SignUpResponse;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.responses.shared.SignInResponse;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.services.actions.manager.AddItemToShopService;
import shop.core.services.actions.manager.ChangeItemDataService;
import shop.core.services.actions.shared.SignInService;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;

public class AcceptanceTest {

    private final ApplicationContextSetup applicationContextSetup = new ApplicationContextSetup();
    private final ApplicationContext applicationContext = applicationContextSetup.setupApplicationContext();

    protected AcceptanceTest() {
    }

    protected AddItemToCartResponse executeAddItemToCart(String itemName, String orderedQuantity) {
        Item orderedItem = getDatabase().accessItemDatabase().findByName(itemName).orElseThrow();
        AddItemToCartRequest request = new AddItemToCartRequest(getCurrentUserId(), orderedItem.getName(), orderedQuantity);
        return getAddItemToCartService().execute(request);
    }

    protected AddItemToShopResponse executeAddItemToShop(String itemName, String price, String availableQuantity) {
        AddItemToShopRequest request = new AddItemToShopRequest(itemName, price, availableQuantity);
        return getAddItemToShopService().execute(request);
    }

    protected ChangeItemDataResponse executeChangeItemData(String itemId, String newName, String newPrice, String newQuantity) {
        ChangeItemDataRequest request = new ChangeItemDataRequest(itemId, newName, newPrice, newQuantity);
        return getChangeItemDataService().execute(request);
    }

    protected SignInResponse executeSignIn(String loginName, String password) {
        SignInRequest request = new SignInRequest(getCurrentUserId(), loginName, password);
        return getSignInService().execute(request);
    }

    protected SignUpResponse executeSignUp(String name, String loginName, String password) {
        SignUpRequest request = new SignUpRequest(getCurrentUserId(), name, loginName, password);
        return getSignUpService().execute(request);
    }

    protected SignOutResponse executeSignOut() {
        SignOutRequest request = new SignOutRequest(getCurrentUserId());
        return getSignOutService().execute(request);
    }

    protected void addCustomer(String name, String loginName, String password) {
        User customer = new User(name, loginName, password, UserRole.CUSTOMER);
        getDatabase().accessUserDatabase().save(customer);
    }

    protected Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    protected CurrentUserId getCurrentUserId() {
        return applicationContext.getBean(CurrentUserId.class);
    }

    private AddItemToCartService getAddItemToCartService() {
        return applicationContext.getBean(AddItemToCartService.class);
    }

    private AddItemToShopService getAddItemToShopService() {
        return applicationContext.getBean(AddItemToShopService.class);
    }

    private ChangeItemDataService getChangeItemDataService() {
        return applicationContext.getBean(ChangeItemDataService.class);
    }

    private SignInService getSignInService() {
        return applicationContext.getBean(SignInService.class);
    }

    private SignUpService getSignUpService() {
        return applicationContext.getBean(SignUpService.class);
    }

    private SignOutService getSignOutService() {
        return applicationContext.getBean(SignOutService.class);
    }

}
