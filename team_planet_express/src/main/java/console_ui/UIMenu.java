package console_ui;

import user_input.UserCommunication;

import java.util.Map;

public class UIMenu {

    private static final String MENU_HEADER = "\r\nProgram menu:";
    private static final String PROMPT_TOPIC_ACTION = "an action number: ";
    private static final String ERROR_INVALID_ACTION_NUMBER = "Error: Please enter one of the menu numbers.";

    private final Map<Integer, UIAction> uiActionsMap;
    private final UserCommunication userCommunication;

    public UIMenu(Map<Integer, UIAction> uiActionsMap, UserCommunication userCommunication) {
        this.uiActionsMap = uiActionsMap;
        this.userCommunication = userCommunication;
    }

    public void startUI() {
        while (true) {
            userCommunication.informUser(MENU_HEADER);
            for (Integer key : uiActionsMap.keySet()) {
                userCommunication.informUser(key + ". " + uiActionsMap.get(key).getActionName());
            }
            userCommunication.requestInput(PROMPT_TOPIC_ACTION);
            try {
                Integer userChoice = userCommunication.getMenuActionNumber();
                if (uiActionsMap.containsKey(userChoice)) {
                    uiActionsMap.get(userChoice).execute();
                } else {
                    userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
                }
            } catch (NumberFormatException e) {
                userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
            }
        }
    }

}
