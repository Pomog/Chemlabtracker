package shop.acceptance.console_ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.acceptance.ApplicationContextSetup;
import shop.console_ui.UIActionsList;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO put this out of its misery, because it is some sort of an abominable integration test now
@ExtendWith(MockitoExtension.class)
class UIActionsListTest {

    static private UIActionsList uiActionsList;
    static private CurrentUserId currentUserId;

    @BeforeAll
    static void setupAppContext() {
        ApplicationContext applicationContext = new ApplicationContextSetup().setupApplicationContext();
        uiActionsList = applicationContext.getBean(UIActionsList.class);
        currentUserId = applicationContext.getBean(CurrentUserId.class);
    }

    @Test
    void shouldReturn8ActionsForNoId() {
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn8ActionsForGuest() {
        currentUserId.setValue(1L);
        assertEquals(9, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn7ActionsForCustomer() {
        currentUserId.setValue(2L);
        assertEquals(8, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn4ActionsForManager() {
        currentUserId.setValue(3L);
        assertEquals(5, uiActionsList.getUIActionsListForUserRole().size());
    }

    @Test
    void shouldReturn3ActionsForAdmin() {
        currentUserId.setValue(4L);
        assertEquals(4, uiActionsList.getUIActionsListForUserRole().size());
    }

}
