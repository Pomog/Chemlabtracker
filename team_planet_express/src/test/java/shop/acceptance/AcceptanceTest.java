package shop.acceptance;

import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.requests.customer.AddItemToCartRequest;
import shop.core.requests.guest.SignUpRequest;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.customer.AddItemToCartResponse;
import shop.core.responses.guest.SignUpResponse;
import shop.core.responses.manager.AddItemToShopResponse;
import shop.core.responses.manager.ChangeItemDataResponse;
import shop.core.services.actions.customer.AddItemToCartService;
import shop.core.services.actions.guest.SignUpService;
import shop.core.services.actions.manager.AddItemToShopService;
import shop.core.services.actions.manager.ChangeItemDataService;
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

    protected SignUpResponse executeSignUp(String name, String loginName, String password) {
        SignUpRequest signUpRequest = new SignUpRequest(getCurrentUserId(), name, loginName, password);
        return getSignUpService().execute(signUpRequest);
    }

    protected Database getDatabase() {
        return applicationContext.getBean(Database.class);
    }

    private CurrentUserId getCurrentUserId() {
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

    private SignUpService getSignUpService() {
        return applicationContext.getBean(SignUpService.class);
    }

}
