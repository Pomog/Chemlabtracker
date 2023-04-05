package shop.console_ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shop.ApplicationContext;
import shop.core.database.Database;
import shop.core.services.fake.FakeDatabaseInitializer;
import shop.core.support.CurrentUserId;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO put this out of its misery, because it is some sort of an abominable integration test now
class UIActionsListTest {

    private static final UIActionsList uiActionsList = new UIActionsList(new ApplicationContext());

    @BeforeAll
    static void setupDatabase() {
        new FakeDatabaseInitializer(uiActionsList.getApplicationContext().getBean(Database.class)).initialize();
    }

    @Test
    void shouldReturn8ActionsForNoId() {
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn8ActionsForGuest() {
        CurrentUserId currentUserId = uiActionsList.getApplicationContext().getBean(CurrentUserId.class);
        currentUserId.setValue(1L);
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn7ActionsForCustomer() {
        CurrentUserId currentUserId = uiActionsList.getApplicationContext().getBean(CurrentUserId.class);
        currentUserId.setValue(2L);
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn4ActionsForManager() {
        CurrentUserId currentUserId = uiActionsList.getApplicationContext().getBean(CurrentUserId.class);
        currentUserId.setValue(3L);
        assertEquals(5, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn3ActionsForAdmin() {
        CurrentUserId currentUserId = uiActionsList.getApplicationContext().getBean(CurrentUserId.class);
        currentUserId.setValue(4L);
        assertEquals(4, uiActionsList.getUIActionsListForUserRole().size());
    }

}
