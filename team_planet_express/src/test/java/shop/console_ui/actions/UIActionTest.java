package shop.console_ui.actions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UIActionTest {

    private static final String ACTION_NAME = "action name";
    private static final int ACCESS_NUMBER = 1;

    private final UIAction uiAction = new UIAction(ACTION_NAME, ACCESS_NUMBER) {
        @Override
        public void execute() {
        }
    };

    @Test
    void shouldReturnActionName() {
        assertEquals(ACTION_NAME, uiAction.getActionName());
    }

    @Test
    void shouldReturnAccessNumber() {
        assertEquals(ACCESS_NUMBER, uiAction.getAccessNumber());
    }

}
