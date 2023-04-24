package shop.acceptance;

import shop.core.database.Database;
import shop.core.domain.item.Item;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;
import shop.core.services.fake.FakeUserGenerator;
import shop.core.services.fake.fake_item_generator.HardcodedItemGeneratorImpl;
import shop.core.services.user.UserCreationData;
import shop.core.services.user.UserService;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.ApplicationContext;
import shop.dependency_injection.DIApplicationContextBuilder;

import java.util.List;

public class ApplicationContextSetup {

    private static final String BLANK = "";

    public ApplicationContext setupApplicationContext() {
        ApplicationContext applicationContext = new DIApplicationContextBuilder().build("shop");
        createFakeItems(applicationContext);
        createFakeUsers(applicationContext);
        setupDefaultUser(applicationContext);
        return applicationContext;
    }

    private void createFakeItems(ApplicationContext applicationContext) {
        List<Item> fakeItems = new HardcodedItemGeneratorImpl().createItems();
        Database database = applicationContext.getBean(Database.class);
        for (Item item : fakeItems) {
            database.accessItemDatabase().save(item);
        }
    }

    private void createFakeUsers(ApplicationContext applicationContext) {
        List<User> fakeUsers = new FakeUserGenerator().createUsers();
        Database database = applicationContext.getBean(Database.class);
        for (User user : fakeUsers) {
            database.accessUserDatabase().save(user);
        }
    }

    private void setupDefaultUser(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        UserCreationData userCreationData = new UserCreationData(UserRole.GUEST.getDefaultName(), BLANK, BLANK, UserRole.GUEST);
        User currentUser = userService.findGuestWithOpenCart().orElseGet(
                () -> userService.createUser(userCreationData));
        CurrentUserId currentUserId = applicationContext.getBean(CurrentUserId.class);
        currentUserId.setValue(currentUser.getId());
    }

}
